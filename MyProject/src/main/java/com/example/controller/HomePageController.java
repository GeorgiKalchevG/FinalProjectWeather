package com.example.controller;

import java.util.ArrayList;
import java.util.TreeMap;
import java.lang.Object;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.ILocationDAO;
import com.example.dao.LocationDAO;
import com.example.model.Car;
import com.example.model.DayForcast;
import com.example.model.Forcast;
import com.example.model.HourForcast;

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
		model.addAttribute("location", location);
		String cityName =dao.getCityNameByIp(ipAddress);
		System.out.println("1 "+cityName);
		ArrayList<DayForcast> list = dao.getThreeDaysFromWUnderground(cityName);
		model.addAttribute("city", WordUtils.capitalize(cityName));
		model.addAttribute("list", list);
		
		
		return "index";
		
	}
	@RequestMapping("search")
	String getDataForLocation(@RequestParam String search,HttpSession session){
		System.out.println("in search " + search);
		
		if(!search.isEmpty()){
			ArrayList<DayForcast> list = dao.getThreeDaysFromWUnderground(search);
			ArrayList<HourForcast> list24hours= dao.getDayFromWUnderground(search);
			System.out.println("Size of the list 24 hours: " + list24hours.size());
			session.setAttribute("list24hours", list24hours);
			session.setAttribute("list", list);
			session.setAttribute("city",WordUtils.capitalize(search));
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
