package com.weather.model;

import java.util.ArrayList;
import java.util.HashMap;



public class ChanceOfTypeOfWeather {

	private ArrayList<String> typeOfWeather;
	private String cloudCover;
	private String  title;
	private  ArrayList<HashMap<String,String>> tempHigh; 
	private  ArrayList<HashMap<String,String>> tempLow; 
	private  ArrayList<HashMap<String,String>> precip; 
	private  ArrayList<HashMap<String,String>> dewpointHigh; 
	private  ArrayList<HashMap<String,String>> dewpointLow;
	public ChanceOfTypeOfWeather(ArrayList<String> typeOfWeather, String cloudCover, String title,
			ArrayList<HashMap<String, String>> tempHigh, ArrayList<HashMap<String, String>> tempLow,
			ArrayList<HashMap<String, String>> precip, ArrayList<HashMap<String, String>> dewpointHigh,
			ArrayList<HashMap<String, String>> dewpointLow) {
		super();
		this.typeOfWeather = typeOfWeather;
		this.cloudCover = cloudCover;
		this.title = title;
		this.tempHigh = tempHigh;
		this.tempLow = tempLow;
		this.precip = precip;
		this.dewpointHigh = dewpointHigh;
		this.dewpointLow = dewpointLow;
	}
	public ArrayList<String> getTypeOfWeather() {
		return typeOfWeather;
	}
	public String getCloudCover() {
		return cloudCover;
	}
	public String getTitle() {
		return title;
	}
	public ArrayList<HashMap<String, String>> getTempHigh() {
		return tempHigh;
	}
	public ArrayList<HashMap<String, String>> getTempLow() {
		return tempLow;
	}
	public ArrayList<HashMap<String, String>> getPrecip() {
		return precip;
	}
	public ArrayList<HashMap<String, String>> getDewpointHigh() {
		return dewpointHigh;
	}
	public ArrayList<HashMap<String, String>> getDewpointLow() {
		return dewpointLow;
	} 
	
	
	
	
}
