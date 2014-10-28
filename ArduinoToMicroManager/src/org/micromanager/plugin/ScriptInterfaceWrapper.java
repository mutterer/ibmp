package src.org.micromanager.plugin;

import java.util.ArrayList;

import mmcorej.PropertyType;
import mmcorej.StrVector;


public class ScriptInterfaceWrapper {

	private static org.micromanager.api.ScriptInterface gui_;
	private static mmcorej.CMMCore core_;
	
	public static void initialize(org.micromanager.api.ScriptInterface gui, mmcorej.CMMCore core){
		gui_ = gui;
		core_ = core;
	}
	
	public static String[] getDevicePropertyNames(String label) throws Exception{
		StrVector vec = core_.getDevicePropertyNames(label);
		return strVectorToStrArray(vec);
	}
	
	public static void snapImage() throws Exception{
		core_.snapImage();
		core_.startContinuousSequenceAcquisition(intervalMs);
	}
	
	public static String getPropertyValue(String label, String propName) throws Exception{
		return core_.getProperty(label, propName);
	}
	
	public static String[] getDeviceNumberPropertyNames(String label) throws Exception{
		String[] names = getDevicePropertyNames(label);
		ArrayList<String> namesList = new ArrayList<String>();
		for(int i = 0; i < names.length; i++){
			if(propertyTypeIsANumber(label, names[i])){
				namesList.add(names[i]);
			}
		}
		String[] returnArray = new String[namesList.size()];
		for(int i = 0; i< returnArray.length; i++){
			returnArray[i] = namesList.get(i);
		}
		return returnArray;
	}
	
	public static String[] getDeviceNames() throws Exception{
		//fixme : Alles ist leer... ...core methoden sind buggy
		/*
		String[] namesAll = strVectorToStrArray(core_.getDeviceAdapterNames());
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
		StrVector vec = core_.getLoadedDevices();
		return strVectorToStrArray(vec);
	}
	
	private static String[] strVectorToStrArray(StrVector vec){
		String[] vecArray = new String[(int)vec.size()];
		for(int i = 0; i< vec.size(); i++){
			vecArray[i] = vec.get(i);
		}
		return vecArray;
	}
	
	public static boolean propertyTypeIsANumber(String label, String propName){
		//PropertyType properType;
		try{
			Double.parseDouble(core_.getProperty(label, propName));
			return true;
		}
		catch(Exception e){
			return false;
		}/*
		try {
			properType = core_.getPropertyType(label, propName);
			String propertyType = properType.toString();
			boolean numberTypeBool = (propertyType.toLowerCase().equals("float")|| propertyType.toLowerCase().equals("integer"));
			return numberTypeBool && (propertyMaxValue(label, propName) != propertyMinValue(label, propName));
		} catch (Exception e) {
			return false;
		}*/
		
	}
	
	public static String[] getGroupChannelNames(String group){
		return strVectorToStrArray(core_.getAvailableConfigs(group));
	}
	
	public static String[] getGroupNames(){
		return strVectorToStrArray(core_.getAvailableConfigGroups());
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
