package com.huckleberrycalfinn.mvcbeatapp;

public interface BeatModelInterface extends BeatModelObservableInterface {
  int getBPM();
  void setBPM(int bpm);
  void start();
  void stop();
  boolean isRunning();
}
