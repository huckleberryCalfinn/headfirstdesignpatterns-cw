package com.huckleberrycalfinn.mvcbeatapp;

public interface BeatModelObservableInterface {
  void addObserver(BeatObserver o);
  void removeObserver(BeatObserver o);
  void notifyBeatObservers();
  void addObserver(BPMObserver o);
  void removeObserver(BPMObserver o);
  void notifyBPMObservers();
}
