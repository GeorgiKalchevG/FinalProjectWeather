package com.example.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.model.Loc;


@Controller
public class SearchController {

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value="/plan", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Loc> buildList(@RequestBody String from,@RequestBody String to,@RequestBody String city,@RequestBody String country){
		System.out.println(from);
		System.out.println(to);
		System.out.println(city);
		System.out.println(country);
	    return new ArrayList<>(); ///return actual list
	}
	
	

	



}
