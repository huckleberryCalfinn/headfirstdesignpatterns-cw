package com.huckleberrycalfinn.mvc;

public interface BeatControllerInterface {
  void start();
  void stop();
  void setBPM(int bpm);
  void increaseBPM();
  void decreaseBPM();
}
