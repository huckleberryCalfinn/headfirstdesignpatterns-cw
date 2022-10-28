package com.huckleberrycalfinn.mvcbeatapp;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;

public class ClapBeatModel implements BeatModelInterface, Runnable{
  private Thread thread;
  private int bpm;
  private ArrayList<BeatObserver> beatObservers;
  private ArrayList<BPMObserver> bpmObservers;
  private Clip clap;
  private boolean running;

  public ClapBeatModel(){
    this.running = false;
    this.bpm = 80;
    this.beatObservers = new ArrayList<>();
    this.bpmObservers = new ArrayList<>();
    this.thread = new Thread(this);
  }

  public void initialize(){
    try {
      File clapFile = new File("clap.wav");
      this.clap = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
      this.clap.open(AudioSystem.getAudioInputStream(clapFile));
    } catch (Exception ex){
      ex.printStackTrace();
    }
  }


  @Override
  public int getBPM() {
    return this.bpm;
  }

  @Override
  public void setBPM(int bpm) {
    this.bpm = bpm;
    this.notifyBPMObservers();
  }

  @Override
  public boolean isRunning(){
    return this.running;
  }

  @Override
  public void start() {
    this.running = true;
    this.thread.start();
    this.notifyBPMObservers();
  }

  @Override
  public void stop() {
    this.stopBeat();
    this.notifyBPMObservers();
  }

  @Override
  public void run() {
    try {
      while(this.isRunning()){
        this.playBeat();
        this.notifyBeatObservers();
        Thread.sleep(60000 / this.bpm);
      }
    } catch(Exception ex){
      ex.printStackTrace();
    }
  }

  @Override
  public void addObserver(BeatObserver o) {
    this.beatObservers.add(o);
  }

  @Override
  public void removeObserver(BeatObserver o) {
    this.beatObservers.remove(o);
  }

  @Override
  public void notifyBeatObservers() {
    Iterator<BeatObserver> beatIterator = this.beatObservers.iterator();
    while (beatIterator.hasNext()){
      beatIterator.next().update();
    }
  }

  @Override
  public void addObserver(BPMObserver o) {
    this.bpmObservers.add(o);
  }

  @Override
  public void removeObserver(BPMObserver o) {
    this.bpmObservers.remove(o);
  }

  @Override
  public void notifyBPMObservers() {
    for (BPMObserver bpmObserver : this.bpmObservers){
      bpmObserver.update();
    }
  }

  public void playBeat(){
    this.clap.setFramePosition(0);
    this.clap.start();
  }

  public void stopBeat(){
    this.clap.setFramePosition(0);
    this.running = false;
  }
}
