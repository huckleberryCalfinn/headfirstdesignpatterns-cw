package com.huckleberrycalfinn.mvc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;

public class BeatModel implements BeatModelInterface, Runnable {
  private int bpm = 90;
  private Thread thread;
  boolean stop = false;
  private Clip clip;
  private List<BeatObserver> beatObservers;
  private List<BPMUpdateObserver> bpmUpdateObservers;

  public BeatModel(){
	this.beatObservers = new ArrayList<BeatObserver>();
	this.bpmUpdateObservers = new ArrayList<BPMUpdateObserver>();
  }

  @Override
  public void initialize() {
	try {
	  File resource = new File("clap.wav");
	  this.clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
	  this.clip.open(AudioSystem.getAudioInputStream(resource));
	} catch(Exception ex){
		ex.printStackTrace();
	}
  }

  @Override
  public void on() {
  	this.bpm = 90;
	this.thread = new Thread(this);
	this.stop = false;
	this.thread.start();
  }

  @Override
  public void run() {
	while (!this.stop){
	  this.playBeat();
	  this.notifyBeatObservers();
	  try {
		Thread.sleep(60000 / this.bpm);
	  } catch (Exception ex){
		ex.printStackTrace();
	  }
	}
  }

  @Override
  public void off() {
	this.stopBeat();
	this.stop = true;
  }

  @Override
  public void setBPM(int bpm) {
	this.bpm = bpm;
	this.notifyBPMUpdateObservers();
  }

  @Override
  public int getBPM() {
	return this.bpm;
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
	for (BPMUpdateObserver o : this.bpmUpdateObservers){
	  o.updateBPM();
	}
  }


  public void playBeat(){
	this.clip.setFramePosition(0);
	this.clip.start();
  }

  public void stopBeat(){
	this.clip.setFramePosition(0);
	this.clip.stop();
  }
}
