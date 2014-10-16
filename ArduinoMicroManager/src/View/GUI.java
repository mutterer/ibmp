package View;

import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.beans.Visibility;
import javax.swing.JLabel;
import java.awt.Font;

public class GUI {

	private JFrame frame;
	private JLabel statusLabel;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		statusLabel = new JLabel("Light");
		statusLabel.setFont(new Font("Square721 BT", Font.BOLD, 60));
		frame.getContentPane().add(statusLabel, BorderLayout.CENTER);
		try {
			this.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printMsg(String msg){
		this.statusLabel.setText(msg);
	}

}
