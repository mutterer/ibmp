package src.org.micromanager.plugin.serialPortHandling;

import mmcorej.CMMCore;
import org.micromanager.api.ScriptInterface;

import java.util.Observable;
import java.util.Observer;


public class SerialReciever implements Observer {


	private CMMCore core_;
	private ScriptInterface gui_;
	private SerialReader serialReader;
	   
	   
	public SerialReciever(ScriptInterface app){
		gui_ = app;
	    core_ = app.getMMCore();
	}

	@Override
	public void update(Observable object, Object arg1) {
		
		
	}
}