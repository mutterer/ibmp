import javax.management.monitor.CounterMonitorMBean;

import mmcorej.CMMCore;



import org.micromanager.api.MMPlugin;
import org.micromanager.api.ScriptInterface;

public class SerialManager implements MMPlugin {
	
	private ScriptInterface gui_;
	private CMMCore core_;

	@Override
	public String getCopyright() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setApp(ScriptInterface gui) {
		Thread readerThread = new Thread() {
			public void run(){
			}
		};
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	
}