///////////////////////////////////////////////////////////////////////////////
//FILE:          NewImageFlipperControls.java
//PROJECT:       Micro-Manager
//SUBSYSTEM:     mmstudio
//-----------------------------------------------------------------------------
//
// AUTHOR:       Arthur Edelstein, Nico Stuurman
//
// COPYRIGHT:    University of California, San Francisco, 2011, 2012, 2013
//
// LICENSE:      This file is distributed under the BSD license.
//               License text is included with the source distribution.
//
//               This file is distributed in the hope that it will be useful,
//               but WITHOUT ANY WARRANTY; without even the implied warranty
//               of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
//
//               IN NO EVENT SHALL THE COPYRIGHT OWNER OR
//               CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
//               INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES.


package org.micromanager.newimageflipper;

import ij.ImagePlus;
import ij.process.ByteProcessor;
import java.util.prefs.Preferences;
import javax.swing.ImageIcon;
import mmcorej.StrVector;
import mmcorej.TaggedImage;
import org.json.JSONObject;
import org.micromanager.MMStudio;
import org.micromanager.api.DataProcessor;
import org.micromanager.utils.ImageUtils;
import org.micromanager.utils.MDUtils;
import org.micromanager.utils.ReportingUtils;

/**
 *
 * @author arthur
 */
public class NewImageFlipperControls extends javax.swing.JFrame {

   private final NewImageFlippingProcessor processor_;
   private String selectedCamera_;
   private final String FRAMEXPOS = "NewImageFlipperXPos";
   private final String FRAMEYPOS = "NewImageFlipperYPos";
   private final String R0 = "0" + "\u00B0";
   private final String R90 = "90" + "\u00B0";
   private final String R180 = "180" + "\u00B0";
   private final String R270 = "270" + "\u00B0";
   private final String[] RS = {R0, R90, R180, R270};
   private final String ROTATEBOX = "RotateBox";
   private final String MIRRORCHECKBOX = "MirrorCheckBox";
   private final String ROTATE = "_Rotation";
   private final String MIRROR = "_Mirror";
   private final String SELECTEDCAMERA = "SelectedCamera";
   private Preferences prefs_;
   private int frameXPos_ = 300;
   private int frameYPos_ = 300;   

   /** 
    * Creates form NewImageFlipperControls 
    */
   public NewImageFlipperControls(NewImageFlippingProcessor processor) {
      processor_ = processor;

      prefs_ = Preferences.userNodeForPackage(this.getClass());

      frameXPos_ = prefs_.getInt(FRAMEXPOS, frameXPos_);
      frameYPos_ = prefs_.getInt(FRAMEYPOS, frameYPos_);

      initComponents();
      
      selectedCamera_ = prefs_.get(SELECTEDCAMERA, 
              MMStudio.getInstance().getCore().getCameraDevice());
      
      mirrorCheckBox_.setSelected(prefs_.getBoolean(selectedCamera_ + MIRROR, false));
            
      rotateComboBox_.removeAllItems();
      for (String item: RS)
         rotateComboBox_.addItem(item);
      rotateComboBox_.setSelectedItem(prefs_.get(selectedCamera_ + ROTATE, R0));

      setLocation(frameXPos_, frameYPos_);
       
      updateCameras();
      
      setBackground(MMStudio.getInstance().getBackgroundColor());

      // Update the processor with our current settings.
      processor_.setRotation(getRotate());
      processor_.setIsMirrored(getMirror());
      processor_.setCamera((String) cameraComboBox_.getSelectedItem());
   }

   public DataProcessor<TaggedImage> getProcessor() {
      return processor_;
   }

   public void safePrefs() {
      prefs_.putInt(FRAMEXPOS, this.getX());
      prefs_.putInt(FRAMEYPOS, this.getY());
   }

   /**
    * updates the content of the camera selection drop down box
    * 
    * Shows all available cameras and sets the currently selected camera
    * as the selected item in the drop down box
    */
   final public void updateCameras() {
      cameraComboBox_.removeAllItems();
      try {
         StrVector cameras = MMStudio.getInstance().getCore().getAllowedPropertyValues("Core", "Camera");
         for (String camera : cameras) {
            cameraComboBox_.addItem(camera);
         }
      } catch (Exception ex) {
         ReportingUtils.logError(ex);
      }
      cameraComboBox_.setSelectedItem(selectedCamera_);
   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      mirrorCheckBox_ = new javax.swing.JCheckBox();
      exampleImageSource_ = new javax.swing.JLabel();
      exampleImageTarget_ = new javax.swing.JLabel();
      cameraComboBox_ = new javax.swing.JComboBox();
      rotateComboBox_ = new javax.swing.JComboBox();
      jLabel1 = new javax.swing.JLabel();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      setTitle("Image Flipper");
      setBounds(new java.awt.Rectangle(300, 300, 150, 150));
      setMinimumSize(new java.awt.Dimension(200, 200));
      setResizable(false);

      mirrorCheckBox_.setText("Mirror");
      mirrorCheckBox_.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            mirrorCheckBox_ActionPerformed(evt);
         }
      });

      exampleImageSource_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/micromanager/icons/R.png"))); // NOI18N

      exampleImageTarget_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/micromanager/icons/R.png"))); // NOI18N

      cameraComboBox_.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            cameraComboBox_ActionPerformed(evt);
         }
      });

      rotateComboBox_.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "90", "180", "270" }));
      rotateComboBox_.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            rotateComboBox_ActionPerformed(evt);
         }
      });

      jLabel1.setText("Rotate");

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(cameraComboBox_, javax.swing.GroupLayout.Alignment.LEADING, 0, 153, Short.MAX_VALUE)
                     .addGroup(layout.createSequentialGroup()
                        .addComponent(exampleImageSource_, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(exampleImageTarget_, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                     .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rotateComboBox_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                  .addGap(38, 38, 38))
               .addGroup(layout.createSequentialGroup()
                  .addComponent(mirrorCheckBox_)
                  .addContainerGap(121, Short.MAX_VALUE))))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(11, 11, 11)
            .addComponent(cameraComboBox_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(exampleImageTarget_, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
               .addComponent(exampleImageSource_, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(mirrorCheckBox_)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(rotateComboBox_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(jLabel1))
            .addGap(25, 25, 25))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

    private void mirrorCheckBox_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mirrorCheckBox_ActionPerformed
       processExample();
       String camera = (String) cameraComboBox_.getSelectedItem();
       if (camera != null) {
          prefs_.putBoolean(camera + MIRROR, mirrorCheckBox_.isSelected());
          if (processor_ != null) {
             processor_.setIsMirrored(getMirror());
          }
       }
    }//GEN-LAST:event_mirrorCheckBox_ActionPerformed

   private void rotateComboBox_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotateComboBox_ActionPerformed
      processExample();
      String camera = (String) cameraComboBox_.getSelectedItem();
      if (camera != null && rotateComboBox_.getSelectedItem() != null) {
         prefs_.put(camera + ROTATE, (String) rotateComboBox_.getSelectedItem());
         if (processor_ != null) {
            processor_.setRotation(getRotate());
         }
      }
   }//GEN-LAST:event_rotateComboBox_ActionPerformed

   private void cameraComboBox_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cameraComboBox_ActionPerformed
      String camera = (String) cameraComboBox_.getSelectedItem();
      if (camera != null) {
         if (processor_ != null) {
            processor_.setCamera(camera);
         }
         mirrorCheckBox_.setSelected(prefs_.getBoolean(camera + MIRROR, false));
         rotateComboBox_.setSelectedItem(prefs_.get(camera + ROTATE, R0));
         prefs_.put(SELECTEDCAMERA, camera);
      }
   }//GEN-LAST:event_cameraComboBox_ActionPerformed

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JComboBox cameraComboBox_;
   private javax.swing.JLabel exampleImageSource_;
   private javax.swing.JLabel exampleImageTarget_;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JCheckBox mirrorCheckBox_;
   private javax.swing.JComboBox rotateComboBox_;
   // End of variables declaration//GEN-END:variables

   public boolean getMirror() {
      return mirrorCheckBox_.isSelected();
   }

   /**
    * Indicates users choice for rotation:
    * 0 - 0 degrees
    * 1 - 90 degrees
    * 2 - 180 degrees
    * 3 - 270 degrees
    * degrees are anti-clockwise
    * 
    * @return coded rotation
    */
   public NewImageFlippingProcessor.Rotation getRotate() {
      if (R90.equals((String) rotateComboBox_.getSelectedItem())) {
         return NewImageFlippingProcessor.Rotation.R90;
      }
      if (R180.equals((String) rotateComboBox_.getSelectedItem())) {
         return NewImageFlippingProcessor.Rotation.R180;
      }
      if (R270.equals((String) rotateComboBox_.getSelectedItem())) {
         return NewImageFlippingProcessor.Rotation.R270;
      }
      return NewImageFlippingProcessor.Rotation.R0;
   }

   public String getCamera() {
      return (String) cameraComboBox_.getSelectedItem();
   }

   private void processExample() {

      ImageIcon exampleIcon = (ImageIcon) exampleImageSource_.getIcon();

      ByteProcessor proc = new ByteProcessor(exampleIcon.getImage());


      try {
         JSONObject newTags = new JSONObject();
         MDUtils.setWidth(newTags, proc.getWidth());
         MDUtils.setHeight(newTags, proc.getHeight());
         MDUtils.setPixelType(newTags, ImagePlus.GRAY8);
         TaggedImage result = NewImageFlippingProcessor.proccessTaggedImage(
                 new TaggedImage(proc.getPixels(), newTags), getMirror(), getRotate() );
         exampleImageTarget_.setIcon(
                 new ImageIcon(ImageUtils.makeProcessor(result).createImage()));
      } catch (Exception ex) {
         ReportingUtils.logError(ex);
      }
   }
}