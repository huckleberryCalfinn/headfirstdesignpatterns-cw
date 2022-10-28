package com.huckleberrycalfinn.mvcbeatapp;

import com.huckleberrycalfinn.mvc.BeatBar;
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

public class BeatView implements BeatObserver, BPMObserver {
  BeatControllerInterface beatController;
  BeatModelInterface beatModel;
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

  public BeatView(BeatControllerInterface beatController, BeatModelInterface beatModel){
	this.beatController = beatController;
  	this.beatModel = beatModel;
	  this.beatModel.addObserver((BeatObserver) this);
	this.beatModel.addObserver((BPMObserver) this);
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

  public void createControls() {
	this.bpmTextField = new JTextField(2);
	this.bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
	JPanel bpmTextInputPanel = new JPanel(new GridLayout(1, 2));
	bpmTextInputPanel.add(this.bpmLabel);
	bpmTextInputPanel.add(this.bpmTextField);
	this.setBPMButton = new JButton("Set");
	this.setBPMButton.addActionListener(new ActionListener(){
	  public void actionPerformed(ActionEvent evt){
		beatController.setBPM(Integer.parseInt(bpmTextField.getText()));
	  }
	});
	this.decreaseBPMButton = new JButton("<<");
	this.decreaseBPMButton.addActionListener(evt -> {
	  this.beatController.setBPM(
			  this.beatModel.getBPM() - 1
	  );
	});
	this.increaseBPMButton = new JButton(">>");
	this.increaseBPMButton.addActionListener(evt->{
	  this.beatController.setBPM(this.beatModel.getBPM() + 1);
	});
	JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
	buttonPanel.add(decreaseBPMButton);
	buttonPanel.add(increaseBPMButton);
	JPanel innerControlPanel = new JPanel(new GridLayout(3, 1));
	innerControlPanel.add(bpmTextInputPanel);
	innerControlPanel.add(this.setBPMButton);
	innerControlPanel.add(buttonPanel);
	this.controlPanel = new JPanel(new GridLayout(1, 2));
	this.controlPanel.add(innerControlPanel);
	this.menuBar = new JMenuBar();
	this.menu = new JMenu("DJ Control");
	this.startMenuItem = new JMenuItem("Start");
	this.stopMenuItem = new JMenuItem("Stop");
	JMenuItem exit = new JMenuItem("Quit");
	this.menu.add(startMenuItem);
	this.menu.add(stopMenuItem);
	this.menu.add(exit);
	this.menuBar.add(menu);
	this.controlFrame = new JFrame("Control");
	this.controlFrame.setJMenuBar(this.menuBar);
	this.controlFrame.getContentPane().add(this.controlPanel);
	this.controlFrame.pack();
	this.controlFrame.setVisible(true);
	this.startMenuItem.addActionListener(new ActionListener(){
	  public void actionPerformed(ActionEvent evt){
		beatController.start();
	  }
	});
	this.stopMenuItem.addActionListener(evt -> {
	  this.beatController.stop();
	});
  }

  @Override
  public void updateBPM() {
	if (this.beatModel.isRunning()){
	  this.bpmOutputLabel.setText(String.valueOf(this.beatModel.getBPM()));
	}
  }

  @Override
  public void updateBeat() {
	if (this.beatBar != null){
	  this.beatBar.setValue(100);
	}
  }
}
