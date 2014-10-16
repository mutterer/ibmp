package src.org.micromanager.plugin.serialPortHandling;

import java.util.ArrayList;
import java.util.Arrays;

import mmcorej.CMMCore;

import org.micromanager.api.ScriptInterface;

public class InputMapper {
	private CMMCore core_;
	private ScriptInterface gui_;
	private InputMappingButtonsWindow buttonsWindow;
	private InputMappingPropertyWindow propertyWindow;
	
	public static void main(String[] args) {
		InputMapper iM = new InputMapper(null);
		
	}
	
	public InputMapper(ScriptInterface app){
		try{
			gui_ = app;
			core_ = gui_.getMMCore();
		}
		catch(NullPointerException nPe){
			nPe.printStackTrace();
		}
		buttonsWindow = new InputMappingButtonsWindow();
		
		
		/*Making an Array of String Arrays which look like this:
		 0:DeviceName ; 1:deviceProperty0; 2:deviceProperty1; ...
		*/
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
		}
	}
	
	
}
