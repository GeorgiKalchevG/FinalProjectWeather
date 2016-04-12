package com.weather.dao;

import java.util.ArrayList;

import com.weather.model.DayForcast;
import com.weather.model.Event;
import com.weather.model.HourForcast;

import com.weather.model.User;


public interface ILocationDAO {
	
	
	ArrayList<ArrayList<DayForcast>> getFiveDaysFromWUnderground(String country,String city,String language,User user,String locID);
	ArrayList<HourForcast> getDayFromWUnderground(String country, String city,String language,User user,String locID);
	String getCityNameByIp(String ip);
	String plannerResponse(String from, String to, String city, String country, String locID);
	ArrayList<Event> getEventsFromFacebookForcast(String responseFromFB);
	String getCityAndCountyByCoordinates(String latitude, String longitude);
}
