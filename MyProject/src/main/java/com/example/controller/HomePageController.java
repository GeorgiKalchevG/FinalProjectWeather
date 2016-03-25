package com.example.controller;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.dao.ILocationDAO;
import com.example.dao.LocationDAO;
import com.example.model.Car;
import com.example.model.Forcast;
import com.example.model.Location;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class HomePageController {
	ILocationDAO dao = new LocationDAO();
	@RequestMapping(value="index")
	String loadHomePage(Model model, HttpServletRequest request ){
		
		 String weatherApiUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
		 String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		   if (ipAddress == null) {  
			   ipAddress = request.getRemoteAddr();  
		   }
		   ipAddress = "46.10.58.161";
		model.addAttribute("ipAddress",ipAddress);
		TreeMap<Integer,Forcast> location = dao.getLocationData(dao.getDataByIP(ipAddress));
		//System.out.println("from controler");
		//System.out.println("from controler"+location.getCity());
		model.addAttribute("location", location);
		
		TreeMap<Integer, Car> map = new TreeMap<>();
		map.put(1, new Car("toyota", "corolla"));
		map.put(2,	new Car("ferrari", "f431"));
		map.put(3, new Car("da", "12"));
		map.put(4, new Car("ne", "2444"));
		model.addAttribute("map", map);
		return "index";
		
	}
	@RequestMapping("search")
	String getDataForLocation(@RequestParam String search,Model model){
		if(!search.isEmpty()){
			TreeMap<Integer,Forcast> location = dao.getLocationData(dao.getDataByCityName(search));
			model.addAttribute("location", location);
			return "index";
		}
		return"index";
	}
	
}
/*
 *	String ipToGeotag = "http://ip-api.com/json/";
		RestTemplate restTemplate = new RestTemplate();
		String data = restTemplate.getForObject(ipToGeotag+ipAddress, String.class);
		String city = new JsonParser().parse(data).getAsJsonObject().get("city").getAsString();
		
		JsonObject weatherData = new JsonParser().parse(restTemplate.getForObject(weatherApiUrl+city+"&appid="+apiKey, String.class)).getAsJsonObject();
		System.out.println(city);
		System.out.println(weatherData);
		model.addAttribute("weatherData",weatherData);
		JsonObject coord = weatherData.get("coord").getAsJsonObject();
		
		double lon = coord.get("lon").getAsDouble();
		double lat = coord.get("lat").getAsDouble();
		JsonArray array = weatherData.get("weather").getAsJsonArray();
		String forcast = null;
		String ico = null;
		for(int i = 0;i<array.size();i++){
			JsonObject obj = array.get(i).getAsJsonObject();
			forcast = obj.get("main").getAsString();
			ico = obj.get("icon").getAsString();
		}
		String icon = "http://openweathermap.org/img/w/";
		icon = icon.concat(ico);
		icon = icon.concat(".png");
		System.out.println(weatherData);
		System.out.println(forcast);
		model.addAttribute("lon", lon);
		model.addAttribute("lat", lat);
		model.addAttribute("forcast", forcast);
		model.addAttribute("temp", weatherData.get("main").getAsJsonObject().get("temp").getAsDouble()-272.15);
		model.addAttribute("pressure", weatherData.get("main").getAsJsonObject().get("pressure").getAsDouble());
		model.addAttribute("temp_min", weatherData.get("main").getAsJsonObject().get("temp_min").getAsDouble()-272.15);
		model.addAttribute("temp_max", weatherData.get("main").getAsJsonObject().get("temp_max").getAsDouble()-272.15);
		model.addAttribute("icon", icon); 
 */
