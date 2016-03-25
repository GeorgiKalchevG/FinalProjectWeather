package com.example.model;

public class Location {
	
	private double lon;
	private double lat;
	private String forcast;
	private String forcastDesc;
	private double temperature;
	private double pressure;
	private double humidity;

	private String image;
	private double windSpeed;
	private double downwind;
	private double cloudPercent;
	private long timeOfLastMeasurement;
	private long sunRise;
	private long sunSet;
	private String country;
	private String city;
	
	public double getDownwind() {
		return downwind;
	}
	public void setDownwind(double downwind) {
		if(downwind!=0)
		this.downwind = downwind;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public String getForcast() {
		return forcast;
	}
	public void setForcast(String forcast) {
		this.forcast = forcast;
	}
	public String getForcastDesc() {
		return forcastDesc;
	}
	public void setForcastDesc(String forcastDesc) {
		this.forcastDesc = forcastDesc;
		
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
		
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
		
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
		
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = "http://openweathermap.org/img/w/"+image+".png";
		
	}
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		if(windSpeed!=0)
		this.windSpeed = windSpeed;
	}
	public double getCloudPercent() {
		return cloudPercent;
	}
	public void setCloudPercent(double cloudPercent) {
		this.cloudPercent = cloudPercent;
	}
	public long getTimeOfLastMeasurement() {
		return timeOfLastMeasurement;
	}
	public void setTimeOfLastMeasurement(long timeOfLastMeasurement) {
		this.timeOfLastMeasurement = timeOfLastMeasurement;
	}
	public long getSunRise() {
		return sunRise;
	}
	public void setSunRise(long sunRise) {
		this.sunRise = sunRise;
	}
	public long getSunSet() {
		return sunSet;
	}
	public void setSunSet(long sunSet) {
		this.sunSet = sunSet;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
}
