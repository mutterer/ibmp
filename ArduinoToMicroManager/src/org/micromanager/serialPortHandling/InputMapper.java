package src.org.micromanager.serialPortHandling;

import java.util.HashMap;

public class InputMapper {
	
	private HashMap<Integer,String[]> map;
	InputMapperWindow window;
	
	public InputMapper(){
		map = new HashMap<Integer,String[]>();
		window = new InputMapperWindow(map);
	}
	
	public HashMap<Integer, String[]> returnMappings(){
		//TODO this method
		return null;
	}
	
	
	
	
}
