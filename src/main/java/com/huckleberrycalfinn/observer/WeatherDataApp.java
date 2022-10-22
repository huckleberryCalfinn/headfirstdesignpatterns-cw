package com.huckleberrycalfinn.observer;
import java.util.ArrayList;
import java.util.List;


public class WeatherDataApp {
	public static void main(String[] args){
	  WeatherData weatherData = new WeatherData.WeatherDataBuilder()
										.temp(100)
										.humidity(.20)
										.pressure(10)
										.build();
	  WeatherDisplayCurrentComponent weatherDisplayCurrentComponent = new WeatherDisplayCurrentComponent(weatherData);
	  WeatherDisplayStatisticsComponent weatherDisplayStatisticsComponent =
			  new WeatherDisplayStatisticsComponent(weatherData);
	  weatherData.registerObserver(weatherDisplayCurrentComponent);
	  weatherData.registerObserver(weatherDisplayStatisticsComponent);
	  weatherData.notifyObservers();
	  weatherData.setTemp(98);
	  weatherData.setPressure(20);
	  weatherData.setHumidity(.14);
	  weatherData.setTemp(95);
	  weatherData.setTemp(92);
	  weatherData.setTemp(80);
	}
}


class WeatherDisplayStatisticsComponent implements Observer {
  private WeatherData weatherData;
  private List<Double> tempList;
  private List<Double> humidityList;
  private List<Double> pressureList;

  public WeatherDisplayStatisticsComponent(WeatherData weatherData){
	this.weatherData = weatherData;
  	this.tempList = new ArrayList<>();
	this.pressureList = new ArrayList<>();
	this.humidityList = new ArrayList<>();
  }

  public void update(){
	this.tempList.add(this.weatherData.getTemp());
	this.humidityList.add(this.weatherData.getHumidity());
	this.pressureList.add(this.weatherData.getPressure());
	this.display();
  }

  public double getAverageTemp(){
	double sumTemps = 0;
	for (double temp : this.tempList){
	  sumTemps += temp;
	}
	return sumTemps / this.tempList.size();
  }

  public double getHighTemp(){
	return this.tempList.stream()
			.max((t0, t1) -> t0.compareTo(t1))
			.get();
  }

  public double getLowTemp(){
	return this.tempList.stream()
			.min((t0, t1)-> t0.compareTo(t1))
			.get();
  }

  public void display(){
	System.out.println(
			"Weather Temp Stats:" +
					"\n\t Avg: " + this.getAverageTemp() +
					"\n\t High: " + this.getHighTemp() +
					"\n\t Low: " + this.getLowTemp()
	);
  }
}


class WeatherDisplayCurrentComponent implements Observer {
  private WeatherData weatherData;

  public WeatherDisplayCurrentComponent(WeatherData weatherData){
	this.weatherData = weatherData;
  }

  public void update(){
	System.out.println(
			"CURRENT_WEATHER" +
					"\n\tTEMP: " + this.weatherData.getTemp() +
					"\n\tPRESSURE: " + this.weatherData.getPressure() +
					"\n\tHUMIDITY: " + this.weatherData.getHumidity()
	);
  }
}

class WeatherData implements Subject {
  private double temp;
  private double pressure;
  private double humidity;
  private List<Observer> observers;

  public WeatherData(double temp, double pressure, double humidity){
	this.temp = temp;
	this.pressure = pressure;
	this.humidity = humidity;
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

  public double getPressure(){
	return this.pressure;
  }
  public void setPressure(double pressure){
	this.pressure = pressure;
	this.notifyObservers();
  }

  public double getHumidity(){
	return this.humidity;
  }
  public void setHumidity(double humidity){
	this.humidity = humidity;
	this.notifyObservers();
  }

  static class WeatherDataBuilder {
	private double temp;
	private double pressure;
	private double humidity;

	public WeatherDataBuilder temp(double temp){
	  this.temp = temp;
	  return this;
	}

	public WeatherDataBuilder pressure(double pressure){
	  this.pressure = pressure;
	  return this;
	}

	public WeatherDataBuilder humidity(double humidity){
	  this.humidity = humidity;
	  return this;
	}

	public WeatherData build(){
		return new WeatherData(this.temp, this.pressure, this.humidity);
	}
  }
}
