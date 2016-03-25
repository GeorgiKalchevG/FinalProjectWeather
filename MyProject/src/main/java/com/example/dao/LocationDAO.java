package com.example.dao;

import java.util.ArrayList;
import java.util.TreeMap;

import org.springframework.web.client.RestTemplate;

import com.example.model.Forcast;
import com.example.model.Location;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LocationDAO implements ILocationDAO{
	 String apiKey = "9885a830e31d144089368b0a44b2f9f7";
	 
	@Override
	public JsonObject getDataByIP(String ip) {

		String ipToGeotag = "http://ip-api.com/json/";
		RestTemplate restTemplate = new RestTemplate();
		String data = restTemplate.getForObject(ipToGeotag+ip, String.class);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+data);
		String cityName = new JsonParser().parse(data).getAsJsonObject().get("city").getAsString();
		JsonObject weatherData =getDataByCityName( cityName);
		System.out.println(weatherData.toString());
		return weatherData;
	}

	@Override
	public ArrayList<Location> collectMajorCitys(String ip) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeMap<Integer,Forcast> getLocationData(JsonObject obj) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+obj.toString());
		TreeMap<Integer,Forcast> threeDays = new TreeMap<Integer,Forcast>();
		JsonObject currently = obj.get("currently").getAsJsonObject();
		long time = currently.get("time").getAsLong();
		String summary = currently.get("summary").getAsString();
		double wind1 = currently.get("windSpeed").getAsDouble();
		String wind = wind1+"";
		String rain1 = currently.get("humidity").getAsString();
		String rain = rain1+"";
		threeDays.put(1, new Forcast(time,summary,wind,rain));
		JsonObject daily = obj.get("daily").getAsJsonObject();
		JsonArray data = daily.get("data").getAsJsonArray();
		for(int i =0 ; i < 3;i++){
			time = ((JsonObject) data.get(i)).get("time").getAsLong();
			summary = ((JsonObject) data.get(i)).get("summary").getAsString();
			wind = ((JsonObject) data.get(i)).get("windSpeed").getAsString();
			rain = ((JsonObject) data.get(i)).get("humidity").getAsString();
			threeDays.put(i, new Forcast(time,summary,wind,rain));
		}
		//JsonObject coord = obj.get("coord").getAsJsonObject();
		//JsonArray weather = obj.get("weather").getAsJsonArray();
		//JsonObject main = obj.get("main").getAsJsonObject();
//		JsonObject wind = obj.get("wind").getAsJsonObject();
		//JsonObject clouds = obj.get("clouds").getAsJsonObject();
		//JsonObject sys = obj.get("sys").getAsJsonObject();
		
		//Location location = new Location();
		//for(int i = 0;i<weather.size();i++){
		//	
		//	location.setForcast(weather.get(i).getAsJsonObject().get("main").getAsString());
		//	location.setForcastDesc(weather.get(i).getAsJsonObject().get("description").getAsString());
		//	location.setImage(weather.get(i).getAsJsonObject().get("icon").getAsString());
		//}
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+obj.get("name").getAsString());
//		location.setCity(obj.get("name").getAsString());
//		location.setCloudPercent(clouds.get("all").getAsDouble());
//		location.setCountry(sys.get("country").getAsString());
//		
//		location.setHumidity(main.get("humidity").getAsDouble());
//		location.setLat(coord.get( "lat").getAsDouble());
//		location.setLon(coord.get("lon").getAsDouble());
//		location.setPressure(main.get("pressure").getAsDouble());
//		location.setSunRise(sys.get("sunrise").getAsLong());
//		location.setSunSet(sys.get("sunset").getAsLong());
//		location.setTemperature(main.get("temp").getAsDouble());
//		location.setTimeOfLastMeasurement(obj.get("dt").getAsLong());
		//location.setWindSpeed(wind.get("speed").getAsDouble());
		//location.setDownwind(wind.get("deg").getAsDouble());
		
		
		return threeDays;
	}

	@Override
	public JsonObject getDataByCityName(String cityName) {
		RestTemplate restTemplate = new RestTemplate();
		String weatherApiUrl = "https://api.forecast.io/forecast/034143e2c674af082f9336e044c19312/42.637877,23.366836?units=si&exclude=minutely,hourly,alerts,flags";
		JsonObject weatherData = new JsonParser().parse(restTemplate.getForObject(weatherApiUrl, String.class)).getAsJsonObject();
		System.out.println(weatherData.toString());
		return weatherData;
	}
	
	

}
//http://api.wunderground.com/api/ba6800955f5db321/forecast10day/q/CA/San_Francisco.json