package com.example.dao;

import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.catalina.connector.Request;
import org.springframework.web.client.RestTemplate;

import com.example.model.DayForcast;
import com.example.model.Forcast;
import com.example.model.HourForcast;
import com.example.model.Location;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LocationDAO implements ILocationDAO{
	 String apiKey = "9885a830e31d144089368b0a44b2f9f7";
	 
	
	 @Override
	public String getCityNameByIp(String ip) {
		String ipToGeotag = "http://api.db-ip.com/addrinfo?addr="+ip+"&api_key=2847ed47c9cf4242bb2e09a10aeb3c313c5ebb06";
		RestTemplate restTemplate = new RestTemplate();
		String data = restTemplate.getForObject(ipToGeotag, String.class);
		String cityName = new JsonParser().parse(data).getAsJsonObject().get("city").getAsString();
		String countryName = new JsonParser().parse(data).getAsJsonObject().get("country").getAsString();
		return countryName+"/"+cityName;
	}

	@Override
	public ArrayList<Location> collectMajorCitys(String ip) {
		// TODO Auto-generated method stub
		return null;
	}


	

	@Override
	public ArrayList<HourForcast> getDayFromWUnderground(String country, String city,String language) {
		System.out.println("2          "+city.replace(' ','_')+"/"+country.replace(' ','_')+".json");
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<HourForcast> DayForcast = new ArrayList<>();

		String wundergroungUrl = "http://api.wunderground.com/api/ba6800955f5db321/hourly/lang:"+language+"/q/"+country.replace(' ','_')+"/"+city.replace(' ','_')+".json";
		JsonObject weatherData = new JsonParser().parse(restTemplate.getForObject(wundergroungUrl, String.class)).getAsJsonObject();
		System.out.println(weatherData.toString());
		JsonArray array = weatherData.get("hourly_forecast").getAsJsonArray();
		for(int i =0;i<24;i++){
			DayForcast.add(createHour(array.get(i).getAsJsonObject()));
		}
		return DayForcast;
	}
	private HourForcast createHour(JsonObject jsonElement){
		HourForcast forcast = new HourForcast();
		forcast.setHour(Integer.parseInt(jsonElement.get("FCTTIME").getAsJsonObject().get("hour").getAsString()));
		forcast.setYear(Integer.parseInt(jsonElement.get("FCTTIME").getAsJsonObject().get("year").getAsString()));
		forcast.setMonth(Integer.parseInt(jsonElement.get("FCTTIME").getAsJsonObject().get("mon").getAsString()));
		forcast.setDay(Integer.parseInt(jsonElement.get("FCTTIME").getAsJsonObject().get("mday").getAsString()));
		forcast.setWeekday(jsonElement.get("FCTTIME").getAsJsonObject().get("weekday_name").getAsString());
		forcast.setTempC(Integer.parseInt(jsonElement.get("temp").getAsJsonObject().get("metric").getAsString()));
		forcast.setTempFH(Integer.parseInt(jsonElement.get("temp").getAsJsonObject().get("english").getAsString()));
		forcast.setConditions(jsonElement.get("condition").getAsString());
		forcast.setIcon_url(jsonElement.get("icon_url").getAsString());
		forcast.setSky(Integer.parseInt(jsonElement.get("sky").getAsString()));
		forcast.setWindKPH(Integer.parseInt(jsonElement.get("wspd").getAsJsonObject().get("metric").getAsString()));
		forcast.setWindMPH(Integer.parseInt(jsonElement.get("wspd").getAsJsonObject().get("english").getAsString()));
		forcast.setDir(jsonElement.get("wdir").getAsJsonObject().get("dir").getAsString());
		forcast.setDirDegrees(Integer.parseInt(jsonElement.get("wdir").getAsJsonObject().get("degrees").getAsString()));
		forcast.setUvIndex(Integer.parseInt(jsonElement.get("uvi").getAsString()));
		forcast.setHumidity(Integer.parseInt(jsonElement.get("humidity").getAsString()));
		forcast.setFeelsLikeC(Integer.parseInt(jsonElement.get("feelslike").getAsJsonObject().get("metric").getAsString()));
		forcast.setFeelsLikeFH(Integer.parseInt(jsonElement.get("feelslike").getAsJsonObject().get("english").getAsString()));
		forcast.setQpfIN(Double.parseDouble(jsonElement.get("qpf").getAsJsonObject().get("english").getAsString()));
		forcast.setQpfMM(Integer.parseInt(jsonElement.get("qpf").getAsJsonObject().get("metric").getAsString()));
		forcast.setSnowIN(Double.parseDouble(jsonElement.get("snow").getAsJsonObject().get("english").getAsString()));
		forcast.setSnowMM(Integer.parseInt(jsonElement.get("snow").getAsJsonObject().get("metric").getAsString()));
		forcast.setPop(Integer.parseInt(jsonElement.get("pop").getAsString()));
		forcast.setMslphinHg(Double.parseDouble(jsonElement.get("mslp").getAsJsonObject().get("english").getAsString()));
		forcast.setMslphPa(Integer.parseInt(jsonElement.get("mslp").getAsJsonObject().get("metric").getAsString()));
		return forcast;
	}
	private DayForcast createDay(JsonObject jsonElement) {
		DayForcast forcast = new DayForcast();
		System.out.println(jsonElement.toString());
		forcast.setConditions(jsonElement.get("conditions").getAsString());
		forcast.setWeekday(jsonElement.get("date").getAsJsonObject().get("weekday").getAsString());
		forcast.setDay(jsonElement.get("date").getAsJsonObject().get("day").getAsInt());
		forcast.setMonth(jsonElement.get("date").getAsJsonObject().get("month").getAsInt());
		forcast.setYear(jsonElement.get("date").getAsJsonObject().get("year").getAsInt());
		forcast.setEpoch(jsonElement.get("date").getAsJsonObject().get("epoch").getAsLong());
		forcast.setIcon_url(jsonElement.get("icon_url").getAsString());
		forcast.setPop(jsonElement.get("pop").getAsInt());
		forcast.setMaxwind_degrees(jsonElement.get("maxwind").getAsJsonObject().get("degrees").getAsDouble());
		forcast.setMaxwind_dir(jsonElement.get("maxwind").getAsJsonObject().get("dir").getAsString());
		forcast.setMaxwind_kph(jsonElement.get("maxwind").getAsJsonObject().get("kph").getAsDouble());
		forcast.setMaxwind_mph(jsonElement.get("maxwind").getAsJsonObject().get("mph").getAsDouble());
		forcast.setMonthName(jsonElement.get("date").getAsJsonObject().get("monthname").getAsString());
		forcast.setPretty(jsonElement.get("date").getAsJsonObject().get("pretty").getAsString());
		forcast.setQpf_alldayIn(jsonElement.get( "qpf_allday").getAsJsonObject().get("in").getAsDouble());
		forcast.setQpf_alldayMM(jsonElement.get( "qpf_allday").getAsJsonObject().get("mm").getAsDouble());
		forcast.setSnow_alldayIn(jsonElement.get("snow_allday").getAsJsonObject().get("in").getAsDouble());
		forcast.setSnow_alldayMM(jsonElement.get("snow_allday").getAsJsonObject().get("cm").getAsDouble());
		forcast.setTempHighCel(jsonElement.get("high").getAsJsonObject().get("celsius").getAsString());
		forcast.setTempHighFahr(jsonElement.get("high").getAsJsonObject().get("fahrenheit").getAsString());
		forcast.setTempLowCel(jsonElement.get("low").getAsJsonObject().get("celsius").getAsString());
		forcast.setTempLowFahr(jsonElement.get("low").getAsJsonObject().get("fahrenheit").getAsString());
		forcast.setAvehumidity(jsonElement.get("avehumidity").getAsDouble());
		forcast.setMaxhumidity(jsonElement.get("maxhumidity").getAsDouble());
		forcast.setMinhumidity(jsonElement.get("minhumidity").getAsDouble());
		return forcast;
	}

	@Override
	public ArrayList<DayForcast> getThreeDaysFromWUnderground(String country, String city,String language){
		System.out.println("city "+city + "country "+ country);
		
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<DayForcast> threeDayForcast = new ArrayList<>();
		String wundergroungUrl = "http://api.wunderground.com/api/ba6800955f5db321/forecast/lang:"+language+"/q/"+country.replace(' ','_')+"/"+city.replace(' ','_')+".json";
		JsonObject weatherData = new JsonParser().parse(restTemplate.getForObject(wundergroungUrl, String.class)).getAsJsonObject();
		System.out.println(weatherData.toString());
		JsonArray array;
		try{
			array = weatherData.get("forecast").getAsJsonObject().get("simpleforecast").getAsJsonObject().get("forecastday").getAsJsonArray();
		}
		catch(NullPointerException e){
			return null;
		}
		for(int i =0;i<array.size()-1;i++){
			threeDayForcast.add(createDay(array.get(i).getAsJsonObject()));
		}
		return threeDayForcast;
	}
	
	

}
//http://api.wunderground.com/api/ba6800955f5db321/forecast10day/q/CA/San_Francisco.json