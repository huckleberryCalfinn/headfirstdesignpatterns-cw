package com.huckleberrycalfinn.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Menu;
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
  private BeatModelInterface beatModel;
  private BeatControllerInterface controller;
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

  public DJView(BeatModelInterface beatModel, BeatControllerInterface controller){
    this.beatModel = beatModel;
    this.controller = controller;
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

  public void createControls(){
    this.bpmTextField = new JTextField(2);
    this.bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
    JPanel bpmTextInputPanel = new JPanel(new GridLayout(1,2));
    bpmTextInputPanel.add(this.bpmLabel);
    bpmTextInputPanel.add(this.bpmTextField);

    this.setBPMButton = new JButton("Set");
    this.setBPMButton.addActionListener(this);
    this.decreaseBPMButton = new JButton("<<");
    this.decreaseBPMButton.addActionListener(this);
    this.increaseBPMButton = new JButton(">>");
    this.increaseBPMButton.addActionListener(this);
    JPanel buttonPanel = new JPanel(new GridLayout(1,2));
    buttonPanel.add(decreaseBPMButton);
    buttonPanel.add(increaseBPMButton);

    JPanel innerControlPanel = new JPanel(new GridLayout(3,1));
    innerControlPanel.add(bpmTextInputPanel);
    innerControlPanel.add(this.setBPMButton);
    innerControlPanel.add(buttonPanel);

    this.controlPanel = new JPanel(new GridLayout(1, 2));
    this.controlPanel.add(innerControlPanel);

    this.menuBar = new JMenuBar();
    this.menu = new JMenu("DJ Control");
    this.startMenuItem = new JMenuItem("Start");
    this.startMenuItem.addActionListener(event -> {
      this.controller.start();
    });
    this.stopMenuItem = new JMenuItem("Stop");
    this.stopMenuItem.addActionListener(event -> {
      this.controller.stop();
    });
    JMenuItem exit = new JMenuItem("Quit");
    exit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        System.exit(0);
      }
    });
    this.menu.add(startMenuItem);
    this.menu.add(stopMenuItem);
    this.menu.add(exit);
    this.menuBar.add(menu);

    this.controlFrame = new JFrame("Control");
    this.controlFrame.setJMenuBar(this.menuBar);
    this.controlFrame.getContentPane().add(this.controlPanel);
    this.controlFrame.pack();
    this.controlFrame.setVisible(true);

  }

  public void enableStopMenuItem() {
    stopMenuItem.setEnabled(true);
  }

  public void disableStopMenuItem() {
    stopMenuItem.setEnabled(false);
  }

  public void enableStartMenuItem() {
    startMenuItem.setEnabled(true);
  }

  public void disableStartMenuItem() {
    startMenuItem.setEnabled(false);
  }

  @Override
  public void updateBeat() {
    if (this.beatBar != null){
      this.beatBar.setValue(100);
    }
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
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.setBPMButton){
      int bpm;
      String bpmText = this.bpmTextField.getText();
      if (bpmText == null || bpmText == ""){
        bpm = 90;
      } else {
        bpm = Integer.parseInt(bpmText);
      }
      this.controller.setBPM(bpm);
    } else if (e.getSource() == this.increaseBPMButton){
      this.controller.increaseBPM();
    } else if (e.getSource() == this.decreaseBPMButton){
      this.controller.decreaseBPM();
    }
  }
}
