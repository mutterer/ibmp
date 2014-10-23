package src.org.micromanager.plugin;

import java.util.ArrayList;

import mmcorej.PropertyType;


public class ScriptInterfaceWrapper {

	private static org.micromanager.api.ScriptInterface gui_;
	private static mmcorej.CMMCore core_;
	
	public static void initialize(org.micromanager.api.ScriptInterface gui, mmcorej.CMMCore core){
		gui_ = gui;
		core_ = core;
	}
	
	public static String[] getDevicePropertyNames(String label) throws Exception{
		return core_.getDevicePropertyNames(label).toArray();
	}
	
	public static String[] getDeviceNames() throws Exception{
		//fixme : Alles ist leer... ...core methoden sind buggy
		/*String[] namesAll = core_.getDeviceAdapterNames().toArray();
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0; i < namesAll.length; i++){
			try{
				core_.getDevicePropertyNames(namesAll[i]);
				names.add(namesAll[i]);
				ArdWindow.println("name geaddet in scriptinterfacewrapper");
			}
			catch(Exception e){
				ArdWindow.println("Error bei scriptinterfacewrapper: ");
				ArdWindow.print(e.getMessage());
			}
		}
		return (String[]) names.toArray();*/
		return core_.getDeviceAdapterNames().toArray();
	}
	
	public static boolean propertyTypeIsANumber(String label, String propName){
		PropertyType properType;
		try {
			properType = core_.getPropertyType(label, propName);
			String propertyType = properType.toString();
			return propertyType.toLowerCase().equals("float")|| propertyType.toLowerCase().equals("integer");
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public static double propertyMaxValue(String label, String propName) throws Exception{
		return core_.getPropertyUpperLimit(label, propName);
	}
	
	public static double propertyMinValue(String label, String propName) throws Exception{
		return core_.getPropertyLowerLimit(label, propName);
	}
	
	public static boolean isAProperty(String label, String propName){
		try{
			core_.getProperty(label, propName);
			return true;
		}
		catch(Exception e){
			new ErrorPopup(e.getMessage()).setVisible(true);;
			return false;
		}
	}
}
