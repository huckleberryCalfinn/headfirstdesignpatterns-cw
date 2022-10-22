package com.huckleberrycalfinn.observer0;

import java.util.ArrayList;
import java.util.List;

public class SimpleObserverApp {
	public static void main(String[] args){
	  WeatherDataSubject weatherDataCenter = new WeatherDataSubject();
	  WeatherDisplay temperatureDisplay = new WeatherDisplay();
	  temperatureDisplay.subscribe(weatherDataCenter);
	  weatherDataCenter.setTemperature(85);
	  weatherDataCenter.setTemperature(87.5);
	  weatherDataCenter.setTemperature(90);
	}
}

interface Observer {
  public void update();
}

interface Subject {
  public void addObserver(Observer o);
  public void removeObserver(Observer o);
  public void notifyObservers();
}

class WeatherDataSubject implements Subject {
  private List<Observer> observers;
  private double temperature;

  public WeatherDataSubject(){
	this.observers = new ArrayList<Observer>();
  }

  public void addObserver(Observer o){
	this.observers.add(o);
  }
  public void removeObserver(Observer o){
	this.observers.remove(o);
  }

  public void notifyObservers(){
	for (Observer o : this.observers){
	  o.update();
	}
  }

  public void setTemperature(double temperature){
	this.temperature = temperature;
	this.notifyObservers();
  }

  public double getTemperature(){
	return this.temperature;
  }
}

class WeatherDisplay implements Observer {
  private WeatherDataSubject weatherDataSubject;

  public WeatherDisplay(){};

  public void subscribe(WeatherDataSubject weatherDataSubject){
	weatherDataSubject.addObserver(this);
	this.weatherDataSubject = weatherDataSubject;
  }
  public void update(){
	System.out.println("WEATHER_DISPLAY:_TEMP=" + this.weatherDataSubject.getTemperature());
  }
}
