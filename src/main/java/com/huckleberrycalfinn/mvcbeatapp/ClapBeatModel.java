package com.huckleberrycalfinn.mvcbeatapp;

import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;

public class ClapBeatModel implements BeatModelObservableInterface, Runnable{
  private Thread thread;
  private int bpm;
  private ArrayList<BeatModelObserverInterface> beatObservers;
  private ArrayList<BeatModelObserverInterface> updateBPMObservers;
  private Clip clap;

  public ClapBeatModel(){
    this.bpm = 80;
    this.beatObservers = new ArrayList<>();
    this.updateBPMObservers = new ArrayList<>();
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
  public void addObserver(BeatModelObserverInterface o) {
  }

  @Override
  public void removeObserver(BeatModelObserverInterface o) {
  }

  @Override
  public void notifyObservers() {
  }

  @Override
  public void run() {

  }
}
