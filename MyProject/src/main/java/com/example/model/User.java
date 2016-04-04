package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int userId;
	private String userName;
	private String password;
	private String language;
	private String unit;
	private String icon;
	private List<String> locations = new ArrayList<>();
	
	
	public User(){}
	
	public User(String userName, String password, String language, String unit, String icon){
		this.userName = userName;
		this.password = password;
		this.language = language;
		this.unit = unit;
		this.icon = icon;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> favourites) {
		this.locations = favourites;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
}


