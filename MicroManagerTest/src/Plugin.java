import mmcorej.CMMCore;


public class Plugin {
	CMMCore core = new CMMCore();
	
	
	public static void main(String[] args) {
		Plugin plugin = new Plugin();
	}
	
	public Plugin(){
		try {
			core.setExposure(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
