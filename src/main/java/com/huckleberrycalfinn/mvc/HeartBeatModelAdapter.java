package com.huckleberrycalfinn.mvc;

public class HeartBeatModelAdapter implements BeatModelInterface {
  private HeartModelInterface heartModel;

  public HeartBeatModelAdapter(HeartModelInterface heartModel){
	this.heartModel = heartModel;
  }

  @Override
  public void initialize() {
	System.out.println("heart model initializes during construction");
  }

  @Override
  public void on() {
	System.out.println("heart model always running");
  }

  @Override
  public void off() {
	System.out.println("can't stop heart model");
  }

  @Override
  public void setBPM(int bpm) {
	System.out.println("heart beats randomly, out of your control");
  }

  @Override
  public int getBPM() {
	return this.heartModel.getHeartRat();
  }

  @Override
  public void registerObserver(BeatObserver o) {
  	this.heartModel.registerObserver(o);
  }

  @Override
  public void registerObserver(BPMUpdateObserver o) {
  	this.heartModel.registerObserver(o);
  }

  @Override
  public void removeObserver(BeatObserver o) {
  	this.heartModel.removeObserver(o);
  }

  @Override
  public void removeObserver(BPMUpdateObserver o) {
  	this.heartModel.removeObserver(o);
  }

  @Override
  public void notifyBeatObservers() {
  	this.heartModel.notifyBeatObservers();
  }

  @Override
  public void notifyBPMUpdateObservers() {
	this.heartModel.notifyBPMUpdateObservers();
  }
}
