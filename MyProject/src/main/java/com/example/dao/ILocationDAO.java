package com.example.dao;

import java.util.ArrayList;



import com.example.model.DayForcast;
import com.example.model.Event;

import com.example.model.HourForcast;
import com.example.model.Location;
import com.example.model.User;


public interface ILocationDAO {
	
	ArrayList<Location> collectMajorCitys(String ip);
	ArrayList<ArrayList<DayForcast>> getFiveDaysFromWUnderground(String country,String city,String language,User user,String locID);
	ArrayList<HourForcast> getDayFromWUnderground(String country, String city,String language,User user,String locID);
	String getCityNameByIp(String ip);
	String plannerResponse(String from, String to, String city, String country);
	ArrayList<Event> getEventsFromFacebookForcast(String responseFromFB);
	String getCityAndCountyByCoordinates(String latitude, String longitude);
}
