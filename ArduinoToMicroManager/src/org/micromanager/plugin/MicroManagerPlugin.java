package src.org.micromanager.plugin;

import mmcorej.CMMCore;
import org.micromanager.api.MMPlugin;
import org.micromanager.api.ScriptInterface;
import src.org.micromanager.plugin.serialPortHandling.*;

public class MicroManagerPlugin implements MMPlugin {
   public static String menuName = "ArduinoRemote";
   public static String tooltipDescription = "A plugin to use MicroManager with an Arduino Box";
   private CMMCore core;
   private ScriptInterface gui;
   private pluginWindow window;
   private Connecter serialReciever;
   private SerialReader serialReader;

   public void setApp(ScriptInterface app) {
	  ScriptInterfaceWrapper.setApp(app);
	  gui = app;
	  core = app.getMMCore();
      serialReciever = new Connecter(gui);
      serialReader = new SerialReader();
      serialReader.addObserver(serialReciever);
      if (window == null)
          window = new pluginWindow(gui);
       window.setVisible(true);
   }
   /* this is just there so that we know how to write things
   public void doIt(){
	   try {
			core.defineConfig("Temp", "-4", "Camera", "CCDTemperature", "0");
		    core.setConfig("Temp","-4");
		    gui.refreshGUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
   }*/

   public void setProperty(String label, String propertyName, String value){
	   //TODO this Method
   }
   
   public void setConfig(String label, String value){
	 //TODO this Method
   }
   
   public void goUpConfig(String label){
	 //TODO this Method
   }
   
   public void goDownConfig(String label){
	 //TODO this Method
   }
   
   public void stepProperty(String label, String propertyName, int amount){
	//TODO this Method   
   }
   
   public void dispose() {
   }

   public void show() {
   }

   public void configurationChanged() {
   }

   public String getInfo () {
      return "Example plugin";
   }

   public String getDescription() {
      return tooltipDescription;
   }
   
   public String getVersion() {
      return "1.0";
   }
   
   public String getCopyright() {
      return "University of California, 2012";
   }
}
