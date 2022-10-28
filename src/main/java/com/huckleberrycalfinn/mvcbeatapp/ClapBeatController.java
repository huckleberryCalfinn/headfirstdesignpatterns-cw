package com.huckleberrycalfinn.mvcbeatapp;

public class ClapBeatController implements BeatControllerInterface{
  private BeatModelInterface beatModel;
  private BeatView beatView;

  public ClapBeatController(BeatModelInterface beatModel){
    this.beatModel = beatModel;
    this.beatModel.initialize();
    this.beatView = new BeatView(this, this.beatModel);
    this.beatView.createView();
    this.beatView.createControls();
  }
  @Override
  public void setBPM(int bpm) {
    this.beatModel.setBPM(bpm);
  }

  @Override
  public void start() {
    this.beatModel.start();
    this.beatView.startMenuItem.setEnabled(false);
    this.beatView.stopMenuItem.setEnabled(true);
  }

  @Override
  public void stop() {
    this.beatModel.stop();
    this.beatView.startMenuItem.setEnabled(true);
    this.beatView.stopMenuItem.setEnabled(false);
  }

}
