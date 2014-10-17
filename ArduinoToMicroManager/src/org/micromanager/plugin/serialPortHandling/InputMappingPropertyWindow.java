package src.org.micromanager.plugin.serialPortHandling;

import src.org.micromanager.plugin.*;

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
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputMappingPropertyWindow extends JFrame {

	private JPanel contentPane;

	ArrayList<String> devices;
	ArrayList<ArrayList<String>> deviceProperties;
	JComboBox propertyName;
	JComboBox deviceName;
	String[] deviceNameArray = {"Error"};
	private JButton btnOk;

	public InputMappingPropertyWindow() {
		
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
		

		/*old version
		 * devicePropertiesArray = new String[deviceProperties.size()][];
		for(int i = 0; i < deviceProperties.size(); i++){
			for(int j = 0; j< deviceProperties.get(i).size(); j++){
				devicePropertiesArray[i][j] = deviceProperties.get(i).get(j);
			}
		}*/
		
		propertyName = new JComboBox(new String[]{});
		
		try {
			deviceNameArray = ScriptInterfaceWrapper.getDeviceAdapterNames();
		} catch (Exception e) {
			e.printStackTrace();
		}
		deviceName = new JComboBox(deviceNameArray);
		deviceName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				//propertyName = new JComboBox(devicePropertiesArray[itemEvent.getStateChange()]);
				try {
					propertyName = new JComboBox(ScriptInterfaceWrapper.getDevicePropertyNames(deviceNameArray[itemEvent.getStateChange()]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(deviceName, BorderLayout.WEST);
		contentPane.add(propertyName,BorderLayout.CENTER);
		
		btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				//TODO Make this somehow return
			}
		});
		contentPane.add(btnOk, BorderLayout.EAST);
	}

}
