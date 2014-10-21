package src.org.micromanager.plugin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.ScrollPane;

import javax.swing.*;

public class ArdWindow extends JFrame {

	private JPanel contentPane;
	private MicroManagerPlugin mm;
	private JTextArea textField;
	private JScrollPane scroller;
	private static TextAreaPrintStream ps;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ArdWindow(MicroManagerPlugin inputMM) {
		mm = inputMM;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{167, 89, 0};
		gbl_panel.rowHeights = new int[]{23, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mm.setProperty("Camera", "CCDTemperature", "-40");
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		textField = new JTextArea();
		textField.setRows(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridheight = 4;
		gbc_textField.gridwidth = 2;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 2;
		scroller = new JScrollPane(textField);
		
		panel.add(scroller, gbc_textField);
		textField.setColumns(10);
		
		ps = new TextAreaPrintStream(textField,System.out);
	}
	
	public static void print(String input){
		ps.print(input);
	}
	public static void println(String input){
		ps.print(input + "\n");
	}

}
