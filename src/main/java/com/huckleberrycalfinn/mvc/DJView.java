package com.huckleberrycalfinn.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DJView implements ActionListener, BeatObserver, BPMUpdateObserver {
  private BeatModel beatModel;
  JFrame viewFrame;
  JPanel viewPanel;
  BeatBar beatBar;
  JLabel bpmOutputLabel;
  JFrame controlFrame;
  JPanel controlPanel;
  JLabel bpmLabel;
  JTextField bpmTextField;
  JButton setBPMButton;
  JButton increaseBPMButton;
  JButton decreaseBPMButton;
  JMenuBar menuBar;
  JMenu menu;
  JMenuItem startMenuItem;
  JMenuItem stopMenuItem;

  public DJView(BeatModel beatModel){
    this.beatModel = beatModel;
    this.beatModel.registerObserver((BeatObserver)this);
    this.beatModel.registerObserver((BPMUpdateObserver)this);
  }

  public void createView(){
    this.viewPanel = new JPanel(new GridLayout(2,1));
    this.viewFrame = new JFrame("View");
    this.viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.viewFrame.setSize(new Dimension(100, 80));
    this.bpmOutputLabel = new JLabel("offline", SwingConstants.CENTER);
    this.beatBar = new BeatBar();
    this.beatBar.setValue(0);
    JPanel bpmPanel = new JPanel(new GridLayout(2, 1));
    bpmPanel.add(this.beatBar);
    bpmPanel.add(this.bpmOutputLabel);
    this.viewPanel.add(bpmPanel);
    this.viewFrame.getContentPane().add(this.viewPanel, BorderLayout.CENTER);
    this.viewFrame.pack();
    viewFrame.setVisible(true);
  }

  @Override
  public void updateBeat() {
    //update components to show beat occurred
  }

  @Override
  public void updateBPM(){
    if (this.beatModel != null){
      int bpm = this.beatModel.getBPM();
      if (bpm == 0){
        if (this.bpmOutputLabel != null){
          this.bpmOutputLabel.setText("offline");
        }
      } else {
        if (this.bpmOutputLabel != null ){
          this.bpmOutputLabel.setText("Current BPM: " + bpm);
        }
      }
      //update components according to new bpm
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // add self to button components
    // the buttons will then call this method
    // when pressed.
    // call e.getSource() to get the button object
  }
}
