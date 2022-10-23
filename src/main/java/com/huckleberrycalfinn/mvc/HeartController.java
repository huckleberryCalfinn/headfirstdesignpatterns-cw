package com.huckleberrycalfinn.mvc;

public class HeartController implements BeatControllerInterface {
  private BeatModelInterface heartModel;
  private DJView djView;

  public HeartController(BeatModelInterface heartModel){
	this.heartModel = heartModel;
	this.djView = new DJView(heartModel, this);
	this.djView.createView();
	this.djView.createControls();
	this.djView.disableStopMenuItem();
	this.djView.disableStartMenuItem();
  }

  @Override
  public void start() {
	this.heartModel.on();
  }

  @Override
  public void stop() {
  	this.heartModel.off();
  }

  @Override
  public void setBPM(int bpm) {
  	this.heartModel.setBPM(bpm);
  }

  @Override
  public void increaseBPM() {
	this.heartModel.setBPM(
			this.heartModel.getBPM() + 1
	);
  }

  @Override
  public void decreaseBPM() {
  	this.heartModel.setBPM(this.heartModel.getBPM() - 1);
  }
}
