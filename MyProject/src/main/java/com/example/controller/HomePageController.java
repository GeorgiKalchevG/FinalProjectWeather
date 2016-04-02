package com.example.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.lang.Object;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.WordUtils;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.dao.ILocationDAO;
import com.example.dao.LocationDAO;
import com.example.model.Car;
import com.example.model.DayForcast;
import com.example.model.Forcast;
import com.example.model.HourForcast;
import com.example.model.Loc;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class HomePageController {
	ILocationDAO dao = new LocationDAO();

	@RequestMapping(value = "index")
	String loadHomePage(Model model, HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("language") == null) {
			session.setAttribute("language", "EN");
		}
		session.setAttribute("page", "cityInfo.jsp");
		if (session.getAttribute("units") == null) {
			System.out.println("setvam units na true");
			session.setAttribute("units", "true");
		}
		System.out.println("zarejdam vednuj indexa");
		String ipAddress=(String) session.getAttribute("ipAddress");
		if (ipAddress==null) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("0:0:0:0:0:0:0:1")) {
				ipAddress = "46.10.58.161";
			} 
			session.setAttribute("ipAddress", ipAddress);
			System.out.println(ipAddress);
		}
		System.out.println(ipAddress);
		String cityName = dao.getCityNameByIp(ipAddress);
		System.out.println("What city name we got from getcITYNAMEBYIP IN HPC " + cityName);
		ArrayList<ArrayList<DayForcast>> forTheThreeTablesAtOnce = null;
		if(session.getAttribute(cityName+session.getAttribute("language"))!=null){
			forTheThreeTablesAtOnce = (ArrayList<ArrayList<DayForcast>>) session.getAttribute(cityName+session.getAttribute("language"));
			session.setAttribute("city", WordUtils.capitalize(cityName));
			session.setAttribute(cityName+session.getAttribute("language"), forTheThreeTablesAtOnce);
			session.setAttribute("list3days", forTheThreeTablesAtOnce.get(0));
			session.setAttribute("listweekenddays", forTheThreeTablesAtOnce.get(1));
			session.setAttribute("list5days", forTheThreeTablesAtOnce.get(2));
			ArrayList<HourForcast> list24hours = (ArrayList<HourForcast>) session.getAttribute(cityName+session.getAttribute("language")+"24");
			session.setAttribute("list24hours", list24hours);
		}else{
		forTheThreeTablesAtOnce = dao.getFiveDaysFromWUnderground(
				cityName.split("/")[0], cityName.split("/")[1], session.getAttribute("language").toString());
		session.setAttribute(cityName+session.getAttribute("language"), forTheThreeTablesAtOnce);
		session.setAttribute("city", WordUtils.capitalize(cityName));
		session.setAttribute("list3days", forTheThreeTablesAtOnce.get(0));
		session.setAttribute("listweekenddays", forTheThreeTablesAtOnce.get(1));
		session.setAttribute("list5days", forTheThreeTablesAtOnce.get(2));
		ArrayList<HourForcast> list24hours = dao.getDayFromWUnderground(cityName.split("/")[0], cityName.split("/")[1],
				session.getAttribute("language").toString());
		session.setAttribute("list24hours", list24hours);
		session.setAttribute(cityName+session.getAttribute("language")+"24", list24hours);
		}
		if (session.getAttribute("queueforCities") == null) {
			LinkedList<DayForcast> queueCities = new LinkedList<>();
			queueCities.add(forTheThreeTablesAtOnce.get(0).get(0));
			session.setAttribute("queueforCities", queueCities);
		}
		return "index";

	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping("search")
	String getDataForLocation(HttpSession session, @RequestParam String city, @RequestParam String country) {

		if (!city.isEmpty()) {
			System.out.println("city name: "+city);
			System.out.println("country name: "+country);
			ArrayList<ArrayList<DayForcast>> forTheThreeTablesAtOnce = dao.getFiveDaysFromWUnderground(country, city,
					session.getAttribute("language").toString());
			if (forTheThreeTablesAtOnce == null) {
				session.setAttribute("city", "Couldnt find this city");
				session.setAttribute("list24hours", null);
				session.setAttribute("list3days", null);
				session.setAttribute("listweekenddays", null);
				session.setAttribute("list5days", null);
				System.out.println("vrushta ako e null pri city info");
				return "index";
			}
			else if(forTheThreeTablesAtOnce.get(0).get(0).getIcon_url()==null){
				session.setAttribute("cityForCurrentSearch", WordUtils.capitalize(city));
				session.setAttribute("list24hours", null);
				session.setAttribute("list3days", null);
				session.setAttribute("listweekenddays", null);
				session.setAttribute("list5days", null);
				session.setAttribute("availableCities", forTheThreeTablesAtOnce.get(0));
				System.out.println("vrushta ako e nqma url snimka  pri chooseCityPage");
				session.setAttribute("page", "chooseCityPage.jsp");
				return "index"; 
			}else {
				ArrayList<HourForcast> list24hours = dao.getDayFromWUnderground(country, city,
						session.getAttribute("language").toString());
				session.setAttribute("list24hours", list24hours);
				session.setAttribute("list3days", forTheThreeTablesAtOnce.get(0));
				session.setAttribute("listweekenddays", forTheThreeTablesAtOnce.get(1));
				session.setAttribute("list5days", forTheThreeTablesAtOnce.get(2));
				session.setAttribute("city", WordUtils.capitalize(city));
				LinkedList<DayForcast> queueCities = (LinkedList<DayForcast>) session.getAttribute("queueforCities");
				if (queueCities.size() > 2) {
					queueCities.removeLast();
				}
				queueCities.addFirst(forTheThreeTablesAtOnce.get(0).get(0));
				session.setAttribute("queueforCities", queueCities);
			}
			if(country.equals("")){
				System.out.println("vrushta sled celiq if pri index");
				return "index";
			}
			System.out.println("vrushta sled celiq if pri city info");
			return "cityInfo";
			
		}
		System.out.println("vrushta sled krainiq if pri index");
		return "index";

	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody String buildList(HttpServletRequest request) {
		String query = "http://autocomplete.wunderground.com/aq?query=" + request.getParameter("city");
		RestTemplate restTemplate = new RestTemplate();
		String data = restTemplate.getForObject(query, String.class);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!" + data);

		JsonObject object = new JsonObject();

		// System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		// object.toString());
		return data; /// return actual list
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "ChangeLanguage", method = RequestMethod.POST)
	public String changeLanguage(HttpSession session) {
		System.out.println("ezikyt v momenta e " + session.getAttribute("language"));
		if (session.getAttribute("language").equals("EN")) {
			System.out.println("smenqm ot angliiski na bulgarski");
			session.setAttribute("language", "BU");
			return "redirect:index?language=es";
		} else {
			System.out.println("smenqm ot bulgarski na angliiski");
			session.setAttribute("language", "EN");
			return "redirect:index?language=en";
		}

	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "ChangeUnits", method = RequestMethod.GET)
	public String ChangeUnits(HttpSession session) {
		System.out.println("Sega e " + session.getAttribute("units"));
		if (session.getAttribute("units").equals("true")) {
			System.out.println("ot true na false");
			session.setAttribute("units", "false");
		} else {
			System.out.println("smenqm ot false na true");
			session.setAttribute("units", "true");
		}
		return "index";
	}

	@RequestMapping(value = "planner")
	public String loadPlanner(HttpSession session) {
		session.setAttribute("page", "planner.jsp");

		return "index";
	}
	@RequestMapping(value = "shukarii")
	public String loadShukarii(HttpSession session) {
		session.setAttribute("page", "fb.jsp");
		return "index";
	}
}
