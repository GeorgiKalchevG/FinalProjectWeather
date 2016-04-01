package com.example.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

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
	public @ResponseBody ArrayList<Loc> buildList( @RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("city") String city, @RequestParam("country") String country){
		System.out.println("From planner "+from);
		System.out.println("From planner "+to);
		System.out.println("From planner "+city);
		System.out.println("From planner "+country);
		dao.plannerResponse(from, to, city, country);
	    return new ArrayList<>(); ///return actual list
	}
	
	


	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value="/facebook", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Loc> facebook( @RequestParam("ivanshishman") String resp){
		System.out.println("kvo");
		System.out.println("From planner "+resp);
		
	    return new ArrayList<>(); ///return actual list
	}
	



}
