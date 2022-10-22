package com.huckleberrycalfinn.mvc;

public interface BeatModelInterface {
  void initialize();
  void on();
  void off();
  void setBPM(int bpm);
  int getBPM();
  void registerObserver(BeatObserver o);
  void registerObserver(BPMUpdateObserver o);
  void removeObserver(BeatObserver o);
  void removeObserver(BPMUpdateObserver o);
  void notifyBeatObservers();
  void notifyBPMUpdateObservers();
}
