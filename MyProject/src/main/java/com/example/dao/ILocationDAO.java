package com.example.dao;

import java.util.ArrayList;
import java.util.TreeMap;

import com.example.model.ChanceOfTypeOfWeather;
import com.example.model.DayForcast;
import com.example.model.Event;
import com.example.model.Forcast;
import com.example.model.HourForcast;
import com.example.model.Location;
import com.example.model.User;
import com.google.gson.JsonObject;

public interface ILocationDAO {
	
	ArrayList<Location> collectMajorCitys(String ip);
	ArrayList<ArrayList<DayForcast>> getFiveDaysFromWUnderground(String country,String city,String language,User user);
	ArrayList<HourForcast> getDayFromWUnderground(String country, String city,String language,User user);
	String getCityNameByIp(String ip);
	String plannerResponse(String from, String to, String city, String country);
	ArrayList<Event> getEventsFromFacebookForcast(String responseFromFB);
}
