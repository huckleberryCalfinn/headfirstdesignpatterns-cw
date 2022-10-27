package com.huckleberrycalfinn.mvcbeatapp;

public interface BeatModelObservableInterface {
  void addObserver(BeatModelObserverInterface o);
  void removeObserver(BeatModelObserverInterface o);
  void notifyObservers();
}
