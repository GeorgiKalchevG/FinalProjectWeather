package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.dao.ILocationDAO;
import com.example.dao.LocationDAO;
import com.example.model.ChanceOfTypeOfWeather;
import com.example.model.DayForcast;
import com.example.model.Event;
import com.example.model.HourForcast;
import com.example.model.Loc;
import com.example.model.Location;
import com.example.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;




@Controller
public class SearchController {
 ILocationDAO dao = new LocationDAO(); 
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value="/plan", method = RequestMethod.POST)
	String buildPlan(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("city") String city, HttpSession session){
		System.out.println("From planner "+from);
		System.out.println("From planner "+to);
	/*	System.out.println("From planner "+city.split(", ")[0]);
		System.out.println("From planner "+city.split(", ")[1]);*/
		String plan =null;
		try{
			plan  = dao.plannerResponse(from, to, city.split(", ")[0], city.split(", ")[1]);
		}
		catch (Exception e){
			session.setAttribute("page", "notFound.jsp");
		    return "index"; ///return actual list
		}
		System.out.println("Printing the plan /*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*");
		System.out.println(plan);
		session.setAttribute("location", city);
		session.setAttribute("plan", plan);
		session.setAttribute("page", "polarChart.jsp");
	    return "index"; ///return actual list
	}
	
	


	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value="/facebook", method = RequestMethod.POST)
	@ResponseBody
	String facebook( HttpSession session,@RequestParam("ivanshishman") String resp,HttpServletResponse response){
		System.out.println("kvo");
		System.out.println("From planner "+resp);
		if(session.getAttribute("facebookForecast")==null){
		session.setAttribute("facebookForecast", dao.getEventsFromFacebookForcast(resp));
		return "true";
		}else{
			return "false";
		}
	}
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value="/logoutFromFacebook", method = RequestMethod.POST)
	@ResponseBody
	void logoutFromFacebook(HttpSession session){
		session.removeAttribute("facebookForecast");
		System.out.println("tuka izobshto vliza li?");
	}
	
	
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/charts", method=RequestMethod.POST)
	String loadChart(){
		return "polarChart";
	}
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "searchFromMap", method = RequestMethod.POST)
	@ResponseBody
	public String searchFromMap(@RequestParam String latitude, @RequestParam String longitude,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		String query = "http://api.geonames.org/timezoneJSON?lat="+latitude+"&lng="+longitude+"&username=gzufi1";
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(query);
		String data = restTemplate.getForObject(query, String.class);
		return data; /// return actual
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "searchInSpecificCountry", method = RequestMethod.POST)
	String searchInSpecificCountry(HttpServletRequest request,HttpSession session,@RequestParam("country") String country) {
		String city= session.getAttribute("cityForCurrentSearch").toString();
		System.out.println("vuv searchInSpecifiCountry city "+city+"   country   "+country);
		ArrayList<ArrayList<DayForcast>> forTheThreeTablesAtOnce = dao.getFiveDaysFromWUnderground(country, city,
				session.getAttribute("language").toString(),(User)session.getAttribute("user"),null);
		ArrayList<HourForcast> list24hours = dao.getDayFromWUnderground(country, city,
				session.getAttribute("language").toString(),(User)session.getAttribute("user"),null);
		session.setAttribute("city", country+"/"+city);
		session.setAttribute("list24hours", list24hours);
		session.setAttribute("backGroundGIF", chooseBackGroundGIF(list24hours));
		session.setAttribute("list3days", forTheThreeTablesAtOnce.get(0));
		session.setAttribute("listweekenddays", forTheThreeTablesAtOnce.get(1));
		session.setAttribute("list5days", forTheThreeTablesAtOnce.get(2));
		LinkedList<DayForcast> queueCities = (LinkedList<DayForcast>) session.getAttribute("queueforCities");
		if (queueCities.size() > 2) {
			queueCities.removeLast();
		}
		queueCities.addFirst(forTheThreeTablesAtOnce.get(0).get(0));
		session.setAttribute("queueforCities", queueCities);
		session.setAttribute("page", "cityInfo.jsp");
		return "index";
	}
	public static String chooseBackGroundGIF(ArrayList<HourForcast> list24hours){
		int currentHour = java.time.LocalDateTime.now().getHour();
		String currGIF=null;
		if(list24hours!=null){
		currGIF = list24hours.get(0).getIcon_url();
		}
		else{
			return null;
		}
		int k=currGIF.length();
		for(int i=currGIF.length()-1;i>0;i--){
			if(currGIF.charAt(i)=='/'){
				break;
			}
			k--;
		}
		currGIF=currGIF.substring(k);
		currGIF = currGIF.replace("nt_", "");
		System.out.println("**********************************************************");
		System.out.println(currGIF);
		System.out.println("**********************************************************");
		switch(currGIF){
		case "chancesnow.gif":	return "/imgs/13.png"; 
		case "chanceflurries.gif": return "/imgs/14.png"; 
		case "flurries.gif": return "/imgs/16.png"; 
		case "snow.gif": return "/imgs/16.png"; 
		case "chancerain.gif": return (currentHour>7&&currentHour<18?"/imgs/39.png":"/imgs/45.png");
		case "rain.gif": return "/imgs/8.png"; 
		case "chancesleet.gif": return "/imgs/18.png"; 
		case "sleet.gif": return "/imgs/18.png"; 
		case "chancetstorms.gif": return (currentHour>7&&currentHour<18?"/imgs/38.png":"/imgs/47.png"); 
		case "tstorms.gif": return "/imgs/35.png"; 
		case "clear.gif": return (currentHour>7&&currentHour<18?"/imgs/32.png":"/imgs/31.png"); 
		case "sunny.gif": return "/imgs/32.png"; 
		case "cloudy.gif": return (currentHour>7&&currentHour<18?"/imgs/28.png":"/imgs/29.png"); 
		case "fog.gif": return "/imgs/22.png"; 
		case "hazy.gif": return "/imgs/20.png"; 
		case "mostlycloudy.gif": return (currentHour>7&&currentHour<18?"/imgs/28.png":"/imgs/29.png"); 
		case "partlycloudy.gif": return (currentHour>7&&currentHour<18?"/imgs/34.png":"/imgs/33.png");  
		default: return "/imgs/44.png"; 
		}
	}

}
/*/imgs/
 * 
 * case "chancesnow.gif":	return "https://media.giphy.com/media/xTcnThWTvBZGrgx2dW/giphy.gif"; 
		case "chanceflurries.gif": return "https://media.giphy.com/media/xTcnThWTvBZGrgx2dW/giphy.gif"; 
		case "flurries.gif": return "https://media.giphy.com/media/xTcnThWTvBZGrgx2dW/giphy.gif"; 
		case "snow.gif": return "https://media.giphy.com/media/xTcnThWTvBZGrgx2dW/giphy.gif"; 
		case "chancerain.gif": return "https://45.media.tumblr.com/940f60067b1b80c3ce21d25bff25c51d/tumblr_mngb2jFLB11riolmio1_500.gif";
		case "rain.gif": return "https://45.media.tumblr.com/940f60067b1b80c3ce21d25bff25c51d/tumblr_mngb2jFLB11riolmio1_500.gif"; 
		case "chancesleet.gif": return "https://media.giphy.com/media/xTcnThWTvBZGrgx2dW/giphy.gif"; 
		case "sleet.gif": return "https://media.giphy.com/media/xTcnThWTvBZGrgx2dW/giphy.gif"; 
		case "chancetstorms.gif": return "https://45.media.tumblr.com/baeb3be0fb41e15620d9ddfc355c9746/tumblr_mmc56s67LI1rf5vsao1_500.gif";
		case "tstorms.gif": return "https://45.media.tumblr.com/baeb3be0fb41e15620d9ddfc355c9746/tumblr_mmc56s67LI1rf5vsao1_500.gif";
		case "clear.gif": return "http://www.auplod.com/u/ulopad78451.gif"; 
		case "sunny.gif": return "http://www.auplod.com/u/ulopad78451.gif"; 
		case "cloudy.gif": return "https://media.giphy.com/media/RO5XhlFWOPs6k/giphy.gif";
		case "fog.gif": return "https://media.giphy.com/media/xTcnThWTvBZGrgx2dW/giphy.gif"; 
		case "hazy.gif": return "https://media.giphy.com/media/ZWRCWdUymIGNW/giphy.gif"; 
		default: return "https://d13yacurqjgara.cloudfront.net/users/345826/screenshots/1163640/weather-partly-cloudy.gif"; 
 * 
 * 
 * */
