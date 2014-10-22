package src.org.micromanager.serialPortHandling;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import src.org.micromanager.plugin.ErrorPopup;
import src.org.micromanager.plugin.ScriptInterfaceWrapper;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JTextArea;

public class InputMapperWindow extends JFrame {

	private JPanel contentPane;
	private JComboBox[] deviceBoxAnal;
	private JComboBox[] propertyBoxAnal;
	private JPanel panel;
	JTextField[] deviceFieldAnal = new JTextField[6];
	JTextField[] propertyFieldAnal = new JTextField[6];
	JTextField[] minValueFieldAnal = new JTextField[6];
	JTextField[] maxValueFieldAnal = new JTextField[6];
	JButton[] okBtnAnal = new JButton[6];
	HashMap<Integer,String[]> map;
	
	private static final int FIRSTBLOCKSTART = 2;
	private static final int SECONDBLOCKSTART = 10;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputMapperWindow frame = new InputMapperWindow(new HashMap<Integer,String[]>());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InputMapperWindow(HashMap<Integer,String[]> mapInput) {
		map = mapInput;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[][82.00,grow][grow]", "[grow][24.00][24.00][24.00][24.00][24.00][24.00][24.00][24.00][24.00][24.00][24.00][24.00][24.00][24.00][24.00][24.00][24.00]"));
		
		JLabel lblDeviceName = new JLabel("Device-Name");
		panel.add(lblDeviceName, "cell 1 1");
		
		JLabel lblPropName = new JLabel("Property-Name");
		panel.add(lblPropName, "cell 2 1");
		
		JLabel lblMinVal = new JLabel("Minimum Value");
		panel.add(lblMinVal, "cell 3 1");
		
		JLabel lblMaxVal = new JLabel("Maximum Value");
		panel.add(lblMaxVal, "cell 4 1");
		
		JLabel lblA5 = new JLabel("A5");
		panel.add(lblA5, "cell 0 "+FIRSTBLOCKSTART+",alignx trailing");
		
		JLabel lblA4 = new JLabel("A4");
		panel.add(lblA4, "cell 0 "+(FIRSTBLOCKSTART+1)+",alignx trailing");
		
		JLabel lblA3 = new JLabel("A3");
		panel.add(lblA3, "cell 0 "+(FIRSTBLOCKSTART+2)+",alignx trailing");
		
		JLabel lblA2 = new JLabel("A2");
		panel.add(lblA2, "cell 0 "+(FIRSTBLOCKSTART+3)+",alignx trailing");
		
		JLabel lblA1 = new JLabel("A1");
		panel.add(lblA1, "cell 0 "+(FIRSTBLOCKSTART+4)+",alignx trailing");
		
		JLabel lblA0 = new JLabel("A0");
		panel.add(lblA0, "cell 0 "+(FIRSTBLOCKSTART+5)+",alignx trailing");
		
		/* Doesnt Work because of buggy Core_ functions in scriptinterfacewrapper
		String[] deviceNames;
		try {
			deviceNames = ScriptInterfaceWrapper.getDeviceNames();
		} catch (Exception e) {
			deviceNames = new String[]{};
		}
		deviceBoxAnal = new JComboBox[6];
		propertyBoxAnal = new JComboBox[6];
		for(int i = 0; i< deviceBoxAnal.length; i++){
			propertyBoxAnal[i] = new JComboBox();
			deviceBoxAnal[i] = new JComboBox(deviceNames);
			int rownumber = (i*-1)+deviceBoxAnal.length+1;
			deviceBoxAnal[i].addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent item) {
					JComboBox box = (JComboBox)item.getSource();
					String device = (String)box.getSelectedItem();
					String[] props;
					try {
						props = ScriptInterfaceWrapper.getDevicePropertyNames(device);
					} catch (Exception e) {
						props = new String[]{};
					}
					int index = 0;
					for(int j = 0; j < deviceBoxAnal.length; j++){
						if(box.equals(deviceBoxAnal[j])){
							index = j;
						}
					}
					//todo filter non number properties out of props
					int rownumber = (index*-1)+propertyBoxAnal.length+1;
					panel.remove(propertyBoxAnal[index]);
					propertyBoxAnal[index] = new JComboBox(props);
					panel.add(propertyBoxAnal[index], "cell 2 "+rownumber+",growx");
					panel.repaint();
				}
			});
			panel.add(deviceBoxAnal[i], "cell 1 "+rownumber+",growx");
			panel.add(propertyBoxAnal[i], "cell 2 "+rownumber+",growx");
		}*/
		
		for(int i = 0; i< deviceFieldAnal.length; i++){
			deviceFieldAnal[i] = new JTextField();
			propertyFieldAnal[i] = new JTextField();
			minValueFieldAnal[i] = new JTextField();
			maxValueFieldAnal[i] = new JTextField();
			okBtnAnal[i] = new JButton("OK");
			
			deviceFieldAnal[i].addInputMethodListener(new InputMethodListener() {
				public void caretPositionChanged(InputMethodEvent arg0) {
				}
				public void inputMethodTextChanged(InputMethodEvent arg0) {
					int index = arrayGetIndex(deviceFieldAnal,arg0.getSource());
					if(!okBtnAnal[index].isEnabled()){
						okBtnAnal[index].setEnabled(true);
					}
				}
			});
			propertyFieldAnal[i].addInputMethodListener(new InputMethodListener() {
				public void caretPositionChanged(InputMethodEvent arg0) {
				}
				public void inputMethodTextChanged(InputMethodEvent arg0) {
					int index = arrayGetIndex(propertyFieldAnal,arg0.getSource());
					if(!okBtnAnal[index].isEnabled()){
						okBtnAnal[index].setEnabled(true);
					}
				}
			});
			minValueFieldAnal[i].addInputMethodListener(new InputMethodListener() {
				public void caretPositionChanged(InputMethodEvent arg0) {
				}
				public void inputMethodTextChanged(InputMethodEvent arg0) {
					int index = arrayGetIndex(minValueFieldAnal,arg0.getSource());
					if(!okBtnAnal[index].isEnabled()){
						okBtnAnal[index].setEnabled(true);
					}
				}
			});
			maxValueFieldAnal[i].addInputMethodListener(new InputMethodListener() {
				public void caretPositionChanged(InputMethodEvent arg0) {
				}
				public void inputMethodTextChanged(InputMethodEvent arg0) {
					int index = arrayGetIndex(maxValueFieldAnal,arg0.getSource());
					if(!okBtnAnal[index].isEnabled()){
						okBtnAnal[index].setEnabled(true);
					}
				}
			});
			okBtnAnal[i].addMouseListener(new MouseAdapter() {
				@Override 
				public void mouseClicked(MouseEvent arg0){
					int index = arrayGetIndex(okBtnAnal, arg0.getSource());
					
					/**
					 * Exception handling below
					 */
					String device = deviceFieldAnal[index].getText().toLowerCase();
					String prop = propertyFieldAnal[index].getText().toLowerCase();
					String minV = minValueFieldAnal[index].getText().toLowerCase();
					String maxV = maxValueFieldAnal[index].getText().toLowerCase();
					boolean isAProperty = ScriptInterfaceWrapper.isAProperty(device, prop);
					boolean isAValidProperty = ScriptInterfaceWrapper.propertyTypeIsANumber(device, prop);
					boolean minVGood = true;
					boolean maxVGood = true;
					boolean maxMinNumbers = true;
					double minVal = -1;
					double maxVal = -1;
					try {
						minVGood = Integer.parseInt(minV) >= ScriptInterfaceWrapper.propertyMinValue(device, prop);
						maxVGood = Integer.parseInt(maxV) <= ScriptInterfaceWrapper.propertyMaxValue(device, prop);
						minVal = ScriptInterfaceWrapper.propertyMinValue(device, prop);
						maxVal = ScriptInterfaceWrapper.propertyMaxValue(device, prop);
						minVal = (int)Math.floor(minVal*100);
						minVal /= 100;
						maxVal = (int)Math.floor(maxVal*100);
						maxVal /= 100;
					} catch (NumberFormatException e) {
						maxMinNumbers = false;
						e.printStackTrace();
					} catch (Exception e) {
						minVGood = true;
						maxVGood = true;
					}
					if(!(isAProperty && isAValidProperty && minVGood && maxVGood && maxMinNumbers)){
						String message = "Please change this: "+"\n";
						message += isAProperty?"":"-Your property does not exist "+"\n";
						message += isAValidProperty?"":"-Your property is not valid for an analog input "+"\n";
						message += minVGood?"":"This minimal value cannot be lower than " + minVal + "\n";
						message += maxVGood?"":"This maximum value cannot be higher that " + maxVal + "\n";
						message += maxMinNumbers?"":"Please enter numbers for the maximum and minimum value "+"\n";
						message += "\n"+" Thank You!";
						ErrorPopup error = new ErrorPopup(message);
						error.setVisible(true);
					}
					/**
					 * Exception handling done
					 */
					else{
						map.put(index+10, new String[]{"6",device,prop,minV,maxV});
						okBtnAnal[index].setEnabled(false);
					}
				}
			});
			int rownumber = (i*-1)+okBtnAnal.length+1;
			panel.add(deviceFieldAnal[i], "cell 1 "+rownumber+",growx");
			panel.add(propertyFieldAnal[i], "cell 2 "+rownumber+",growx");
			panel.add(minValueFieldAnal[i], "cell 3 "+rownumber+",growx");
			panel.add(maxValueFieldAnal[i], "cell 4 "+rownumber+",growx");
			panel.add(okBtnAnal[i], "cell 5 "+rownumber+",growx");
		}
		
		JLabel[] pinLbl = new JLabel[10];
		for(int i = 0; i <pinLbl.length; i++){
			pinLbl[i] = new JLabel("Pin" + i);
			int rownumber = (i*-1) + pinLbl.length +SECONDBLOCKSTART;
			panel.add(pinLbl[i], "cell 0 "+ rownumber);
		}
		//TODO was is das?
		for(int i = 8 ; i<= 17; i++){
		}
	}
	
	private int arrayGetIndex(Object[] array, Object object){
		for(int i = 0; i< array.length; i++){
			if( array[i].equals(object)){
				return i;
			}
		}
		return -1;
	}
}