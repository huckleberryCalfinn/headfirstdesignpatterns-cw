package com.huckleberrycalfinn.mvcbeatapp;

public interface BeatModelInterface extends BeatModelObservableInterface {
  int getBPM();
  void setBPM();
  void start();
  void stop();
}
