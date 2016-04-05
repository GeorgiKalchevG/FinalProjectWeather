package com.example.controller;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.dao.ILocationDAO;
import com.example.dao.LocationDAO;
import com.example.model.ChanceOfTypeOfWeather;
import com.example.model.DayForcast;
import com.example.model.HourForcast;
import com.example.model.Loc;
import com.example.model.Location;
import com.google.gson.JsonObject;




@Controller
public class SearchController {
 ILocationDAO dao = new LocationDAO(); 
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value="/plan", method = RequestMethod.POST)
	String buildPlan(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("city") String city, HttpSession session){
		System.out.println("From planner "+from);
		System.out.println("From planner "+to);
		System.out.println("From planner "+city.split(", ")[0]);
		System.out.println("From planner "+city.split(", ")[1]);
		
		String plan  = dao.plannerResponse(from, to, city.split(", ")[0], city.split(", ")[1]);
		System.out.println("Printing the plan /*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*");
		System.out.println(plan);
		session.setAttribute("location", city);
		session.setAttribute("plan", plan);
		session.setAttribute("page", "polarChart.jsp");
	    return "index"; ///return actual list
	}
	
	


	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value="/facebook", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Loc> facebook( @RequestParam("ivanshishman") String resp){
		System.out.println("kvo");
		System.out.println("From planner "+resp);
		
	    return new ArrayList<>(); ///return actual list
	}
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/charts", method=RequestMethod.POST)
	String loadChart(){
		return "polarChart";
	}
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "searchInSpecificCountry", method = RequestMethod.POST)
	String searchInSpecificCountry(HttpServletRequest request,HttpSession session,@RequestParam("country") String country) {
		String city= session.getAttribute("cityForCurrentSearch").toString();
		System.out.println("vuv searchInSpecifiCountry city "+city+"   country   "+country);
		ArrayList<ArrayList<DayForcast>> forTheThreeTablesAtOnce = dao.getFiveDaysFromWUnderground(country, city,
				session.getAttribute("language").toString());
		ArrayList<HourForcast> list24hours = dao.getDayFromWUnderground(country, city,
				session.getAttribute("language").toString());
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
		switch(currGIF){
		case "chancesnow.gif":	return "https://media.giphy.com/media/xTcnThWTvBZGrgx2dW/giphy.gif"; 
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
		}
	}

}
