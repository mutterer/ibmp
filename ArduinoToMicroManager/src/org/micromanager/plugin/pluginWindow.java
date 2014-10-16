
/**
 * ExampleFrame.java
 * 
 * 
 * This is merely a skeleton showing how to start writing a Micro-Manager plugin.
 * This specific example uses the NetBeans GUI builder.  You can safely take out 
 * those parts (and remove the .form file) and build your own interface.
 *
 * 
 * It should not be added to the Micro-Manager distribution (as it is quite 
 * limited in functionality).
 * 
 * 
 * Nico Stuurman, copyright UCSF, 2012
 *  
 * LICENSE:      This file is distributed under the BSD license.
 *               License text is included with the source distribution.
 *
 *               This file is distributed in the hope that it will be useful,
 *               but WITHOUT ANY WARRANTY; without even the implied warranty
 *               of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 *               IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 *               CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *               INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES.
 */

package src.org.micromanager.plugin;

import mmcorej.CMMCore;

import java.text.NumberFormat;
import java.util.prefs.Preferences;

import org.micromanager.api.ScriptInterface;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class pluginWindow extends javax.swing.JFrame {
   private final ScriptInterface gui_;
   private final CMMCore core_;
   private Preferences prefs_;
   
   private NumberFormat nf_;

   private int frameXPos_ = 100;
   private int frameYPos_ = 100;

   private static final String FRAMEXPOS = "FRAMEXPOS";
   private static final String FRAMEYPOS = "FRAMEYPOS";


    public pluginWindow(ScriptInterface gui) {
       gui_ = gui;
       core_ = gui_.getMMCore();
       nf_ = NumberFormat.getInstance();
       prefs_ = Preferences.userNodeForPackage(this.getClass());

       initComponents();

       setLocation(frameXPos_, frameYPos_);

       setBackground(gui_.getBackgroundColor());
       
       

    }
    
    

   private void initComponents() {

      jTextField5 = new javax.swing.JTextField();
      jLabel1 = new javax.swing.JLabel();
      
      zeroButton = new javax.swing.JButton();
      zeroButton.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent arg0) {
      		try {
      			core_.defineConfig("Temp", "-4", "Camera", "CCDTemperature", "0");
      		    core_.setConfig("Temp","-4");
      		    gui_.refreshGUI();
      		} catch (Exception e) {
      			e.printStackTrace();
      		}
      	}
      });

      setTitle("Plugin");
      setLocationByPlatform(true);
      setResizable(false);
      addWindowListener(new java.awt.event.WindowAdapter() {
         public void windowClosing(java.awt.event.WindowEvent evt) {
            onWindowClosing(evt);
         }
      });

      jLabel1.setText("Temp is being manipulated");

      zeroButton.setText("Make it 0");
      
      JButton fourButton = new JButton("Make it 4");
      fourButton.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		try {
      			core_.defineConfig("Temp", "4", "Camera", "CCDTemperature", "4");
      		    core_.setConfig("Temp","4");
      		    gui_.refreshGUI();
      		} catch (Exception e2) {
      			e2.printStackTrace();
      		}
      	}
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      layout.setHorizontalGroup(
      	layout.createParallelGroup(Alignment.LEADING)
      		.addGroup(layout.createSequentialGroup()
      			.addContainerGap(395, Short.MAX_VALUE)
      			.addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE))
      		.addGroup(layout.createSequentialGroup()
      			.addGap(108)
      			.addComponent(jLabel1)
      			.addContainerGap(161, Short.MAX_VALUE))
      		.addGroup(layout.createSequentialGroup()
      			.addGap(40)
      			.addComponent(zeroButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
      			.addGap(101)
      			.addComponent(fourButton)
      			.addContainerGap(104, Short.MAX_VALUE))
      );
      layout.setVerticalGroup(
      	layout.createParallelGroup(Alignment.LEADING)
      		.addGroup(layout.createSequentialGroup()
      			.addGroup(layout.createParallelGroup(Alignment.LEADING)
      				.addGroup(layout.createSequentialGroup()
      					.addComponent(jLabel1)
      					.addGap(43)
      					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
      						.addComponent(zeroButton)
      						.addComponent(fourButton)))
      				.addGroup(layout.createSequentialGroup()
      					.addGap(125)
      					.addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
      			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
      getContentPane().setLayout(layout);

      pack();
   }
    private void onWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_onWindowClosing
       prefs_.putInt(FRAMEXPOS, (int) getLocation().getX());
       prefs_.putInt(FRAMEYPOS, (int) getLocation().getY());

    }

 
   private javax.swing.JButton zeroButton;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JTextField jTextField5;
}
