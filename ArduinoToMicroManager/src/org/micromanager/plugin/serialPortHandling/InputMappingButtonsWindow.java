package src.org.micromanager.plugin.serialPortHandling;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;


/**
 * 
 * Show Buttons in this window on which one can click
 * once clicked, a new window appears asking to which property one wants to map this
 * 
 * Problem: How do I know to what I can map to what?
 *
 */

class InputMappingButtonsWindow extends JFrame {

	private JPanel contentPane;


	public InputMappingButtonsWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 245, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
