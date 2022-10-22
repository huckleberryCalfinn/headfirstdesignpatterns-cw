package com.huckleberrycalfinn.mvc;

public class BeatController implements BeatControllerInterface {
  private BeatModelInterface beatModel;
  private DJView view;

  public BeatController(BeatModelInterface beatModel){
    this.beatModel = beatModel;
    this.view = new DJView(beatModel, this);
    this.beatModel.initialize();
    this.view.createView();
    this.view.createControls();
    this.view.disableStopMenuItem();
    this.view.enableStartMenuItem();
  }

  @Override
  public void start() {
    this.beatModel.on();
    this.view.disableStartMenuItem();
    this.view.enableStopMenuItem();
  }

  @Override
  public void stop() {
    this.beatModel.off();
    this.view.disableStopMenuItem();
    this.view.enableStartMenuItem();

  }

  @Override
  public void setBPM(int bpm) {
    this.beatModel.setBPM(bpm);
  }

  @Override
  public void increaseBPM() {
    this.beatModel.setBPM(this.beatModel.getBPM() + 1);
  }

  @Override
  public void decreaseBPM() {
    this.beatModel.setBPM(this.beatModel.getBPM() - 1);
  }
}
