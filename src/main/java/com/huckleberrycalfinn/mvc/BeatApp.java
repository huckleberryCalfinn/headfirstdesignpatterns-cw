package com.huckleberrycalfinn.mvc;

public class BeatApp {
  public static void main(String[] args){
//    BeatModel beatModel = new BeatModel();
//    BeatController beatController = new BeatController(beatModel);
//    beatModel.initialize();
//    beatModel.on();
//    DJView djView = new DJView(beatModel, beatController);
//    djView.createView();
//    djView.createControls();
//    beatModel.setBPM(120);
//    try {
//      Thread.sleep(5000);
//    } catch (Exception ex){
//      ex.printStackTrace();
//    }
//    beatModel.setBPM(160);
    BeatController drumBeatController = new BeatController(new BeatModel());
    BeatModelInterface heartModel = new HeartBeatModelAdapter(new HeartModel());

    BeatControllerInterface heartBeatController = new BeatController(heartModel);
    BeatControllerInterface heartBeatWithHeartController = new HeartController(heartModel);
  }
}
