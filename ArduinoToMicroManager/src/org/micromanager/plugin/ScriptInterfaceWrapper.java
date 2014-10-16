package src.org.micromanager.plugin;

import mmcorej.*;

public class ScriptInterfaceWrapper {

	private static org.micromanager.api.ScriptInterface gui_;
	private static mmcorej.CMMCore core_;
	
	public static void setApp(org.micromanager.api.ScriptInterface gui){
		gui_ = gui;
		core_ = gui_.getMMCore();
	}
	
	public static StrVector getDevicePropertyNames(String label) throws Exception{
		return core_.getDevicePropertyNames(label);
	}
	
	public static StrVector getDeviceAdapterNames() throws Exception{
		return core_.getDeviceAdapterNames();
	}
	
}
