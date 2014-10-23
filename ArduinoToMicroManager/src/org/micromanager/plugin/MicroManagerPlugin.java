package src.org.micromanager.plugin;

import mmcorej.CMMCore;
import org.micromanager.api.MMPlugin;
import org.micromanager.api.ScriptInterface;

import src.org.micromanager.serialPortHandling.*;

public class MicroManagerPlugin implements MMPlugin {
	public static String menuName = "ArduinoRemote";
	public static String tooltipDescription = "A plugin to use MicroManager with an Arduino Box";
	private CMMCore core_;
	private ScriptInterface gui_;
	private ArdWindow window;
	private Connecter serialReciever;
	private SerialReader serialReader;

	public void setApp(ScriptInterface app) {
		gui_ = app;
		core_ = app.getMMCore();
		ScriptInterfaceWrapper.initialize(gui_,core_);
		if (window == null)
			window = new ArdWindow(this);
		window.setVisible(true);
		serialReciever = new Connecter(this);
		ArdWindow.println("2.Connecter Initialized");
		serialReader = new SerialReader();
		serialReader.initialize();
		ArdWindow.println("4.SerialReader Initialized");
		serialReader.addObserver(serialReciever);
		ArdWindow.println("5.Done");
		try {
			ArdWindow.println(core_.getDevicePropertyNames("Arduino").toArray()[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this is just there so that we know how to write things public void
	 * doIt(){ try { core.defineConfig("Temp", "-4", "Camera", "CCDTemperature",
	 * "0"); core.setConfig("Temp","-4"); gui.refreshGUI(); } catch (Exception
	 * e) { e.printStackTrace(); } }
	 */

	public void setProperty(String label, String propName, String propValue) {

		try {
			core_.setProperty(label, propName, propValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gui_.refreshGUI();
	}

	public void setConfig(String label, String value) {

		try {
			core_.setConfig(label, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gui_.refreshGUI();
	}

	public void goUpConfig(String label) {
		String[] configs = core_.getAvailableConfigs(label).toArray();
		String currentConfig = "";
		try {
			currentConfig = core_.getCurrentConfig(label);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int configIndex = -3;
		for (int i = 0; i < configs.length; i++) {
			if (configs[i].equals(currentConfig)) {
				configIndex = i;
				break;
			}
		}
		//If out of bounds
		configIndex++;
		if (configIndex == configs.length) {
			configIndex = 0;
		}
		try {
			core_.setConfig(label, configs[configIndex]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gui_.refreshGUI();
	}

	public void goDownConfig(String label) {
		String[] configs = core_.getAvailableConfigs(label).toArray();
		String currentConfig = "";
		try {
			currentConfig = core_.getCurrentConfig(label);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int configIndex = -3;
		for (int i = 0; i < configs.length; i++) {
			if (configs[i].equals(currentConfig)) {
				configIndex = i;
				break;
			}
		}
		//If out of bounds
		configIndex--;
		if (configIndex == -1) {
			configIndex = configs.length - 1;
		}

		try {
			core_.setConfig(label, configs[configIndex]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gui_.refreshGUI();
	}

	public void stepProperty(String label, String propName, int amount) {
		//TODO Randbehandlung Wenn man �ber Rand stept
		try {
			core_.setProperty(
					label,
					propName,
					Integer.parseInt(core_.getProperty(label, propName)
							+ amount));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		gui_.refreshGUI();
	}

	public void dispose() {
	}

	public void show() {
	}

	public void configurationChanged() {
	}

	public String getInfo() {
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
