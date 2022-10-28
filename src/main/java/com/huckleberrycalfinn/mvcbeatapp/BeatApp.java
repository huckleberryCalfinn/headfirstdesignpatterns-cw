package com.huckleberrycalfinn.mvcbeatapp;

public class BeatApp {
  public static void main(String[] args){
//    ClapBeatModel clapBeatModel = new ClapBeatModel();
//    clapBeatModel.initialize();
//    clapBeatModel.start();
//    BeatApp.sleep(5000);
//    clapBeatModel.setBPM(120);
//    BeatApp.sleep(3000);
//    clapBeatModel.stop();
    ClapBeatModel clapBeatModel = new ClapBeatModel();
    ClapBeatController clapBeatController = new ClapBeatController(clapBeatModel);
  }

  public static void sleep(int millis){
    try {
      Thread.sleep(millis);
    } catch(Exception ex){
      ex.printStackTrace();
    }
  }
}
