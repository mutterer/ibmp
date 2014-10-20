package src.org.micromanager.serialPortHandling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import mmcorej.CMMCore;

import org.micromanager.api.ScriptInterface;

public class InputMapper {
	private CMMCore core_;
	private ScriptInterface gui_;
	private InputMappingButtonsWindow buttonsWindow;
	private InputMappingPropertyWindow propertyWindow;
	
	
	public InputMapper(){
		/*OldCode
		 * try{
			gui_ = app;
			core_ = gui_.getMMCore();
		}
		catch(NullPointerException nPe){
			nPe.printStackTrace();
		}*/
		buttonsWindow = new InputMappingButtonsWindow();
		propertyWindow = new InputMappingPropertyWindow();
		
		
		/*Making an Array of String Arrays which look like this:
		 0:DeviceName ; 1:deviceProperty0; 2:deviceProperty1; ...
		*/
		/*Code for doing things without Static Core
		try {
			//String[] devices = core_.getDeviceAdapterNames().toArray();
			//String[][] deviceProps = new String[devices.length][];
			ArrayList<String> devices =new ArrayList<String>(Arrays.asList(core_.getDeviceAdapterNames().toArray()));
			ArrayList<ArrayList<String>> deviceProps = new ArrayList<ArrayList<String>>();
			for(int i = 0; i < devices.size(); i++){
				deviceProps.add(new ArrayList<String>(Arrays.asList(core_.getDevicePropertyNames(devices.get(i)).toArray())));
				deviceProps.get(i).add(0, devices.get(i));
			}
			propertyWindow = new InputMappingPropertyWindow(deviceProps);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	public HashMap<Integer, String[]> returnMappings(){
		//TODO this method
		return null;
	}
	
	
	
	
}
