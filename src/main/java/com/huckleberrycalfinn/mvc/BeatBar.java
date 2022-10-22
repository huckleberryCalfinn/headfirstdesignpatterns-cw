package com.huckleberrycalfinn.mvc;

import javax.swing.JProgressBar;

public class BeatBar extends JProgressBar implements Runnable {
  private static final long serialVersionUID = 2L;
  JProgressBar progressBar;
  Thread thread;

  public BeatBar(){
	this.thread = new Thread(this);
	this.setMaximum(100);
	this.thread.start();
  }
  @Override
  public void run() {
	for (;;){
	  int value = this.getValue();
	  value = (int)(value * .75);
	  this.setValue(value);
	  this.repaint();
	  try {
		Thread.sleep(50);
	  } catch (Exception ex){
		ex.printStackTrace();
	  }
	}
  }
}
