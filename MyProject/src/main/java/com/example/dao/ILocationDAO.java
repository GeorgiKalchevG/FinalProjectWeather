package com.example.dao;

import java.util.ArrayList;
import java.util.TreeMap;

import com.example.model.DayForcast;
import com.example.model.Forcast;
import com.example.model.HourForcast;
import com.example.model.Location;
import com.google.gson.JsonObject;

public interface ILocationDAO {
	
	ArrayList<Location> collectMajorCitys(String ip);
	ArrayList<DayForcast> getThreeDaysFromWUnderground(String country,String city,String language);
	ArrayList<HourForcast> getDayFromWUnderground(String country, String city,String language);
	String getCityNameByIp(String ip);
	String plannerResponse(String from, String to, String city, String country);
}
