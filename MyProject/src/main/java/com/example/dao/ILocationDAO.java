package com.example.dao;

import java.util.ArrayList;
import java.util.TreeMap;

import com.example.model.DayForcast;
import com.example.model.Forcast;
import com.example.model.Location;
import com.google.gson.JsonObject;

public interface ILocationDAO {
	JsonObject getDataByIP(String ip);
	ArrayList<Location> collectMajorCitys(String ip);
	JsonObject getDataByCityName(String cityName);
	TreeMap<Integer,Forcast> getLocationData(JsonObject obj);
	ArrayList<DayForcast> getThreeDaysFromWUnderground(String search);
	String getCityNameByIp(String ip);
}
