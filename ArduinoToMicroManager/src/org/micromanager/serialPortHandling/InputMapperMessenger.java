package src.org.micromanager.serialPortHandling;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import src.org.micromanager.plugin.ArdWindow;

public class InputMapperMessenger extends Observable {

	HashMap<Integer,String[]> map;
	
	public InputMapperMessenger(HashMap<Integer, String[]> mapInput){
		map = mapInput;
		notifyObservers(map);
	}
	
	public void changed(){
		setChanged();
	}
	
	public void setMap(HashMap<Integer,String[]>mapInput){
		map = mapInput;
		setChanged();
	}
	
	public void addNewObserver(Observer obs){
		ArdWindow.println("Adding Observer in InputMapperMessenger");
		addObserver(obs);
	}
}
