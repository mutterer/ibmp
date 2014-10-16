package src.org.micromanager.plugin.serialPortHandling;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;

import src.org.micromanager.plugin.ScriptInterfaceWrapper;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class InputMappingPropertyWindow extends JFrame {

	private JPanel contentPane;

	ArrayList<String> devices;
	ArrayList<ArrayList<String>> deviceProperties;
	JComboBox propertyName;
	JComboBox deviceName;
	String[][] devicePropertiesArray;

	public InputMappingPropertyWindow(ArrayList<ArrayList<String>> deviceProps) {
		
		//Splitup String
		/*devices = new ArrayList<String>();
		deviceProperties = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < deviceProps.size(); i++){
			devices.add(deviceProps.get(i).get(0));
			deviceProps.get(i).remove(0);
		}*/
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		

		devicePropertiesArray = new String[deviceProperties.size()][];
		for(int i = 0; i < deviceProperties.size(); i++){
			for(int j = 0; j< deviceProperties.get(i).size(); j++){
				devicePropertiesArray[i][j] = deviceProperties.get(i).get(j);
			}
		}
		if(propertyName == null)
			propertyName = new JComboBox();
		splitPane.setRightComponent(propertyName);
		String[] deviceNameArray = {"Error"};
		try {
			deviceNameArray = ScriptInterfaceWrapper.getDeviceAdapterNames().toArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		deviceName = new JComboBox(deviceNameArray);
		deviceName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				
				propertyName = new JComboBox(devicePropertiesArray[itemEvent.getStateChange()]);
			}
		});
		splitPane.setLeftComponent(deviceName);
	}

}
