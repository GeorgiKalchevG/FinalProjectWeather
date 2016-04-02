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
		session.setAttribute("list3days", forTheThreeTablesAtOnce.get(0));
		session.setAttribute("listweekenddays", forTheThreeTablesAtOnce.get(1));
		session.setAttribute("list5days", forTheThreeTablesAtOnce.get(2));
		LinkedList<DayForcast> queueCities = (LinkedList<DayForcast>) session.getAttribute("queueforCities");
		if (queueCities.size() > 2) {
			queueCities.removeLast();
		}
		queueCities.addFirst(forTheThreeTablesAtOnce.get(0).get(0));
		session.setAttribute("queueforCities", queueCities);
		session.removeAttribute("availableCities");
		session.removeAttribute("cityForCurrentSearch");
		session.setAttribute("page", "cityInfo.jsp");
		return "index";
	}


}
