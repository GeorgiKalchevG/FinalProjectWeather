package com.example.model;

public class Event {
	private String start_time;
	private String name;
	private String city;
	private String country;
	private CurrentForcast weather;
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public CurrentForcast getWeather() {
		return weather;
	}
	public void setWeather(CurrentForcast weather) {
		this.weather = weather;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
