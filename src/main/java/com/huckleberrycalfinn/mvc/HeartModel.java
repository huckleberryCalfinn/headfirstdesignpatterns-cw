package com.huckleberrycalfinn.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeartModel implements HeartModelInterface, Runnable{
  private int time;
  private List<BeatObserver> beatObservers;
  private List<BPMUpdateObserver> bpmUpdateObservers;
  private Thread thread;
  Random random;

  public HeartModel(){
	this.time = 1000;
	this.random = new Random(System.currentTimeMillis());
	this.beatObservers = new ArrayList<BeatObserver>();
	this.bpmUpdateObservers = new ArrayList<BPMUpdateObserver>();
	this.thread = new  Thread(this);
	this.thread.start();
  }

  @Override
  public int getHeartRat() {
	return 60000 / this.time;
  }

  @Override
  public void registerObserver(BeatObserver o) {
  	this.beatObservers.add(o);
  }

  @Override
  public void registerObserver(BPMUpdateObserver o) {
  	this.bpmUpdateObservers.add(o);
  }

  @Override
  public void removeObserver(BeatObserver o) {
  	this.beatObservers.remove(o);
  }

  @Override
  public void removeObserver(BPMUpdateObserver o) {
  	this.bpmUpdateObservers.remove(o);
  }

  @Override
  public void notifyBeatObservers() {
	for (BeatObserver o : this.beatObservers){
	  o.updateBeat();
	}
  }

  @Override
  public void notifyBPMUpdateObservers() {
  	for (BPMUpdateObserver  o : this.bpmUpdateObservers){
		o.updateBPM();
	}
  }

  @Override
  public void run() {
	int lastRate = -1;
	for (;;){
	  int timeChange = this.random.nextInt(10);
	  if (this.random.nextInt(2) == 1){
		timeChange = 0 - timeChange;
	  }
	  int newRate = 60000 / (this.time + timeChange);
	  if (newRate < 120 && newRate > 50){
		this.time = this.time + timeChange;
		this.notifyBPMUpdateObservers();
	  }
	  this.notifyBeatObservers();
	  try {
		this.thread.sleep(this.time);
	  } catch (Exception ex){
		ex.printStackTrace();
	  }
	}
  }
}
