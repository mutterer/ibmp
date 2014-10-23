package src.org.micromanager.serialPortHandling;

import src.org.micromanager.plugin.*;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;


public class Connecter implements Observer {

	private MicroManagerPlugin microManager;
	private InputMapper mapper;
	
	public static final int FUNCTION = 0;
	public static final int CERTAINCHANNEL = 1;
	public static final int CHANNELPLUS = 2;
	public static final int CHANNELMINUS = 3;
	public static final int CERTAINPROP = 4;
	public static final int PROPSTEP = 5;
	public static final int PROPDYNAMIC = 6;
	
	final int SMALLSTEPS = 16;
	final int MEDSTEPS = SMALLSTEPS +1;
	final int BIGSTEPS = MEDSTEPS +1;
	final int STEPCOMPENSATIONVALUE = SMALLSTEPS-3;
	
	int stepSize = MEDSTEPS;
	   
	   
	public Connecter(MicroManagerPlugin mm){

		microManager = mm;
	    
		ArdWindow.println("Connecter Constructor 36");
	    mapper = new InputMapper();
	    mapper.setVisible(true);
	    
		//FIXME
		ArdWindow.println("1.Initializing Connecter");
	}

    /**
     * Map works like this:
     * Integer is the Number of the input device: 0 - 13 -> 0 - 9 
     * -- 13:LED BigSteps , 12: LED MedSteps , 11:LED SmallSteps, 10:StepsBtn
     * A0 - A5 -> 10 - 15
     * 
     * String Array: 
     * 0: 0Function / 1CertainChannel / 2Channel+ / 3Channel - / 4CertainProp / 5PropStep / 6PropDynamic
     * Function: 1 Function
     * CertainChannel: 1 GroupName 2 ChannelName
     * Channel +/-: 1 GroupName
     * CertainProp: 1 DeviceName(label) 2 PropertyName 3 Value
     * PropUp/Down: 1 DeviceName(label) 2 PropertyName 3 SmallValue 4 MedValue 5 BigValue
     * PropDynamic: 1 DeviceName(label) 2 PropertyName 3 MinValue 4 MaxValue
     * 
     * Values SenDed:
     * ButtonMapValue*1000 + (Value if Analog) value geHt vOn 0 - MESSGAGECAP in Constants
     */
	public void update(Observable object, Object signalObject) {
		HashMap<Integer,String[]> mappings = mapper.returnMappings();
		//FIXME
		ArdWindow.println("..Recieving");
		try{
			ArdWindow.println(mappings.get(10)[0]+" , "+mappings.get(10)[1]);
		}
		catch(Exception e){
			ArdWindow.println(e.getMessage());
		}
		
		//if you get a signal convert it with the hashmap
		int commandInt = -1;
		int buttonNR = -1;
		int signal;
		try{
		signal = Integer.parseInt((String)signalObject);
		buttonNR = (int)Math.floor(signal /1000);
		ArdWindow.println("btnnr: "+ buttonNR);
		}
		catch(Exception e2){
			e2.printStackTrace();
			ArdWindow.println("Signal was not a number in [Connecter.update()]");
			//make Signal a Number if it isn't
			signal =10001;
		}
		try{
			//Retrieve Function of Button from HashMap
			commandInt = Integer.parseInt(mappings.get(buttonNR)[0]);
		}
		catch(Exception e){
			e.printStackTrace();
			ArdWindow.println("Didnt manage to parse String to Int in [Connecter.update()]");
			ArdWindow.println("Maybe you haven't configured this input");
		}
		String[] args = mappings.get(buttonNR);
		switch(commandInt){
			case -1:
				System.out.println("-1");
				break;
			case FUNCTION:
				//TODO This Case
				break;
			case CERTAINCHANNEL:
				microManager.setConfig(args[1], args[2]);
				break;
			case CHANNELPLUS:
				microManager.goUpConfig(args[1]);
				break;
			case CHANNELMINUS:
				microManager.goDownConfig(args[1]);
				break;
			case CERTAINPROP:
				microManager.setProperty(args[1], args[2], args[3]);
				break;
			case PROPSTEP:
				microManager.stepProperty(args[1], args[2], stepSize);
				break;
			case PROPDYNAMIC:
				//input Value
				int valueSignal = signal -1000* (int)(Math.floor(signal/1000));
				//border handling
				if(valueSignal <= Constants.CAPROUNDAMOUNT)
					valueSignal = 0;
				else if(valueSignal >= Constants.MESSAGECAP-Constants.CAPROUNDAMOUNT)
					valueSignal = Constants.MESSAGECAP;
				double factor = Integer.parseInt(args[4]) - Integer.parseInt(args[3]);
				factor /= Constants.MESSAGECAP;
				//output Value
				double valueMM = (valueSignal*factor)+ Integer.parseInt(args[3]);
				microManager.setProperty(args[1], args[2], ""+valueMM);
				break;
			case BIGSTEPS:
				stepSize = BIGSTEPS - STEPCOMPENSATIONVALUE;
				break;
			case MEDSTEPS:
				stepSize = MEDSTEPS - STEPCOMPENSATIONVALUE;
				break;
			case SMALLSTEPS:
				stepSize = SMALLSTEPS - STEPCOMPENSATIONVALUE;
				break;
		}

	}
}
