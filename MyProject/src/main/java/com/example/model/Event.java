package com.example.model;

import java.io.Serializable;

public class Event implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3005944159514971583L;
	private String start_time;
	private String cover_url;
	private String description;
	private String nameOfPlace;
	private String nameOfEvent;
	private String city;
	private String country;
	private String latitude;
	private String longitude;
	private String street;
	private String ticket_uri;
	private CurrentForcast weather;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTicket_uri() {
		return ticket_uri;
	}
	public void setTicket_uri(String ticket_uri) {
		this.ticket_uri = ticket_uri;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
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
	
	public String getNameOfPlace() {
		return nameOfPlace;
	}
	public void setNameOfPlace(String nameOfPlace) {
		this.nameOfPlace = nameOfPlace;
	}
	public String getNameOfEvent() {
		return nameOfEvent;
	}
	public void setNameOfEvent(String nameOfEvent) {
		this.nameOfEvent = nameOfEvent;
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
	public String getCover_url() {
		return cover_url;
	}
	public void setCover_url(String cover_url) {
		this.cover_url = cover_url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
