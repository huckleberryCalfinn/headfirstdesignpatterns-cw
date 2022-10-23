package com.huckleberrycalfinn.mvc;

public interface HeartModelInterface {
  int getHeartRat();
  void registerObserver(BeatObserver o);
  void registerObserver(BPMUpdateObserver o);
  void removeObserver(BeatObserver o);
  void removeObserver(BPMUpdateObserver o);
  void notifyBeatObservers();
  void notifyBPMUpdateObservers();
}
