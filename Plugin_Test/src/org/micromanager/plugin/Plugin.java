package src.org.micromanager.plugin;

import mmcorej.CMMCore;
import org.micromanager.api.MMPlugin;
import org.micromanager.api.ScriptInterface;

public class Plugin implements MMPlugin {
   public static String menuName = "Plugin-Test";
   public static String tooltipDescription = "Trying things";
   private CMMCore core_;
   private ScriptInterface gui_;
   private ExampleFrame myFrame_;

   public void setApp(ScriptInterface app) {
      gui_ = app;                                        
      core_ = app.getMMCore();
      if (myFrame_ == null)
          myFrame_ = new ExampleFrame(gui_);
       myFrame_.setVisible(true);
       
       // Used to change the background layout of the form.  Does not work on Windows
       gui_.addMMBackgroundListener(myFrame_);
      
      
   }
   
   public void doIt(){
	   try {
			core_.defineConfig("Temp", "-4", "Camera", "CCDTemperature", "0");
		    core_.setConfig("Temp","-4");
		    gui_.refreshGUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
