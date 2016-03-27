//package com.example.controller;
//
//import java.util.ArrayList;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.client.RestTemplate;
//
//import com.example.model.Loc;
//
//
//@Controller
//public class SearchController {
//
//	
//	@RequestMapping(value="/add", method = RequestMethod.POST)
//	public @ResponseBody ArrayList<Loc> buildList(@RequestBody String city){
//		String query = "http://autocomplete.wunderground.com/aq?query=var"+city;
//		
//		RestTemplate restTemplate = new RestTemplate();
//		String data = restTemplate.getForObject(query, String.class);
//		System.out.println(data);
//	    return new ArrayList<>(); ///return actual list
//	}
//	
//	
//
////	@RequestMapping(value="/getListOFCitys", method=RequestMethod.POST )
////	String getListOfLocations(Model model, HttpServletRequest request){
////		String city = request.getParameter("city");
////		String query = "http://autocomplete.wunderground.com/aq?query="+city;
////		
////		RestTemplate restTemplate = new RestTemplate();
////		String data = restTemplate.getForObject(query, String.class);
////		System.out.println(data);
////		
////		return "index";
////		
////	}
//
//
//
//}
