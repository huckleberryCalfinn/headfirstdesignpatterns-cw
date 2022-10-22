package com.huckleberrycalfinn.observer.again;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataApp {
  public static void main(String[] args){
	WeatherData weatherData = new WeatherData();
	CurrentTemperatureDisplay currentTemperatureDisplay = new CurrentTemperatureDisplay(weatherData);
	TemperatureStatisticsDisplay temperatureStatisticsDisplay = new TemperatureStatisticsDisplay(weatherData);
	double[] temps = {60, 70, 80, 90, 100};
	for (double temp : temps){
	  weatherData.setTemp(temp);
	}
  }

}


class WeatherData implements Subject {
  private double temp;
  private List<Observer> observers;

  public WeatherData() {
	this.observers = new ArrayList<Observer>();
  }

  public void registerObserver(Observer o){
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

  public double getTemp(){
	return this.temp;
  }
  public void setTemp(double temp){
	this.temp = temp;
	this.notifyObservers();
  }
}

class TemperatureStatisticsDisplay implements Observer {
  private WeatherData weatherData;
  private List<Double> temps;

  public TemperatureStatisticsDisplay(WeatherData weatherData){
	this.weatherData = weatherData;
	this.weatherData.registerObserver(this);
	this.temps = new ArrayList<Double>();
  }

  public double getAverageTemp(){
	return this.temps.stream().reduce((t0, t1)-> t0 + t1).get() / this.temps.size();
  }

  public double getMaxTemp(){
	return this.temps.stream().max((t0, t1)->t0.compareTo(t1)).get();
  }

  public double getMinTemp(){
	return this.temps.stream().min((t0, t1)->t0.compareTo(t1)).get();
  }


  public void update(){
	this.temps.add(this.weatherData.getTemp());
	System.out.println(
			"DISPLAY_TEMPERATURE_STATISTICS:" +
					"\n\tAVERAGE_TEMP: " + this.getAverageTemp() +
					"\n\tMAX_TEMP: " + this.getMaxTemp() +
					"\n\tMIN_TEMP: " + this.getMinTemp()
	);
  }
}

class CurrentTemperatureDisplay implements Observer {
  private WeatherData weatherData;

  public CurrentTemperatureDisplay(WeatherData weatherData){
	this.weatherData = weatherData;
	this.weatherData.registerObserver(this);
  }

  public void update(){
	double currentTemp = weatherData.getTemp();
	System.out.println(
			"DISPLAY_CURRENT_TEMPERATURE:" +
					"\n\t" + currentTemp
	);
  }
}


interface Subject {
  public void registerObserver(Observer o);
  public void removeObserver(Observer o);
  public void notifyObservers();
}

interface Observer {
  public void update();
}
