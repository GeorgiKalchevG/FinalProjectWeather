package com.example.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.lang.Object;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.WordUtils;
import org.apache.hadoop.mapred.legacyjobhistory_jsp;
import org.apache.shiro.web.session.HttpServletSession;
import org.neo4j.cypher.internal.compiler.v2_2.perty.recipe.formatErrors;
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
import com.example.model.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class HomePageController {
	ILocationDAO dao = new LocationDAO();
	String[] arr = {"Afghanistan","Albania","Algeria","Andorra","Angola","Antigua and Barbuda","Argentina","Armenia","Australia","Austria","Azerbaijan","Bahamas","Bahrain","BG","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bhutan","Bolivia","Bosnia and Herzegovina","Botswana","Brazil","Brunei","Bulgaria","Burkina Faso","Burundi","Cabo Verde","Cambodia","Cameroon","Canada","Central African Republic","Chad","Chile","China","Colombia","Comoros","Congo","Costa Rica","Cote d'Ivoire","Croatia","Cuba","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia","Fiji","Finland","France","Gabon","Gambia","Georgia","Germany","Ghana","Greece","Grenada","Guatemala","Guinea","Guinea-Bissau","Guyana","Haiti","Honduras","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Israel","Italy","JamaicaJapan","Jordan","Kazakhstan","Kenya","Kiribati","Kosovo","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liecht","ensteinLithuania","Luxembourg","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montenegro","Morocco","Mozambique","Myanmar (Burma)","Namibia","Nauru","Nepal","Netherlands","New Zealand","Nicaragua","Niger","Nigeria","North Korea","Norway","Oman","Pakistan","Palau","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Qatar","Romania","Russia","Rwanda","St. Kitts and Nevis","St. Lucia","St. Vincent and The Grenadines","Samoa","San Marino","Sao Tome and Principe","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Korea","South Sudan","Spain","Sri Lanka","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor-Leste","Togo","Tonga","Trinidad and Tobago","Tunisia","Turkey","Turkmenistan","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States of America","Uruguay","Uzbekistan","Vanuatu","Vatican City","Venezuela","Vietnam","Yemen","Zambia","Zimbabwe"};
	ArrayList<String> allCountries = new ArrayList<>(Arrays.asList(arr));
	

	@RequestMapping(value = "index")
	String loadHomePage(Model model, HttpServletRequest request, HttpSession session) {
		
		if (session.getAttribute("language") == null) {
			session.setAttribute("language", "EN");
			session.setAttribute("flag", "http://www.printableworldflags.com/icon-flags/32/Bulgaria.png");
		}
		session.setAttribute("page", "cityInfo.jsp");
		if (session.getAttribute("units") == null) {
			System.out.println("setvam units na true");
			session.setAttribute("units", "true");
			session.setAttribute("unitTemp", "&#8451");
			session.setAttribute("unitSpeed", "Km/h");
			session.setAttribute("unitMM", "mm");
		}
		if (session.getAttribute("icons") == null) {
			System.out.println("setvam icons na i");
			session.setAttribute("icons", "i");
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
			ArrayList<HourForcast> list24hours = (ArrayList<HourForcast>) session.getAttribute(cityName+session.getAttribute("language")+"24");
			saveOrNullItemsInSession(session,cityName,forTheThreeTablesAtOnce,forTheThreeTablesAtOnce.get(0),forTheThreeTablesAtOnce.get(1),forTheThreeTablesAtOnce.get(2),list24hours);
		}else{
		forTheThreeTablesAtOnce = dao.getFiveDaysFromWUnderground(
				cityName.split("/")[0], cityName.split("/")[1], session.getAttribute("language").toString(),(User)session.getAttribute("user"));
		
		ArrayList<HourForcast> list24hours = dao.getDayFromWUnderground(cityName.split("/")[0], cityName.split("/")[1],
				session.getAttribute("language").toString(),(User)session.getAttribute("user"));
		saveOrNullItemsInSession(session,cityName,forTheThreeTablesAtOnce,forTheThreeTablesAtOnce.get(0),forTheThreeTablesAtOnce.get(1),forTheThreeTablesAtOnce.get(2),list24hours);

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
	String getDataForLocation(HttpSession session, @RequestParam String city, @RequestParam String country, HttpServletRequest req) {
		
		if(country.isEmpty()){
			try{
			if(city.split(", ")[1]!=null){
				country = WordUtils.capitalize(city.split(", ")[1].toLowerCase());
				city = WordUtils.capitalize(city.split(", ")[0].toLowerCase());
				if(!allCountries.contains(country)){
					country="";
				}
			}
			}catch(Exception e){
				System.out.println("vrushta sled krainiq if pri index");
				session.setAttribute("page", "notFound.jsp");
				return "index";
			}
			
				
		}

		if (!country.isEmpty()) {
			
			System.out.println("city name: "+city);
			System.out.println("country name: "+country);
			ArrayList<ArrayList<DayForcast>> forTheThreeTablesAtOnce = dao.getFiveDaysFromWUnderground(country, city,
					session.getAttribute("language").toString(),(User)session.getAttribute("user"));
			if (forTheThreeTablesAtOnce == null) {
				saveOrNullItemsInSession(session,"N/A",forTheThreeTablesAtOnce,null,null,null,null);
				System.out.println("vrushta ako e null pri city info");
				session.setAttribute("page", "cityInfo.jsp");
				return "index";
			}
			else if(forTheThreeTablesAtOnce.get(0).get(0).getIcon_url()==null){
				session.setAttribute("cityForCurrentSearch", WordUtils.capitalize(city));
				saveOrNullItemsInSession(session,null,forTheThreeTablesAtOnce,null,null,null,null);
				session.setAttribute("availableCities", forTheThreeTablesAtOnce.get(0));
				System.out.println("vrushta ako e nqma url snimka  pri chooseCityPage");
				session.setAttribute("page", "chooseCityPage.jsp");
				return "index"; 
			}else {
				ArrayList<HourForcast> list24hours = dao.getDayFromWUnderground(country, city,
						session.getAttribute("language").toString(),(User)session.getAttribute("user"));
				saveOrNullItemsInSession(session,WordUtils.capitalize(country+"/"+city),forTheThreeTablesAtOnce,forTheThreeTablesAtOnce.get(0),forTheThreeTablesAtOnce.get(1),forTheThreeTablesAtOnce.get(2),list24hours);

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
			if(req.getParameter("fromAjax") != null){
				return "cityInfo";
			}else{
				session.setAttribute("page", "cityInfo.jsp");
				return "index";
			}
				
			
		}
		System.out.println("vrushta sled krainiq if pri index");
		session.setAttribute("page", "notFound.jsp");
		return "index";

	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody String buildList(HttpServletRequest request) {
		String query = "http://autocomplete.wunderground.com/aq?query=" + request.getParameter("city");
		RestTemplate restTemplate = new RestTemplate();
		String data = restTemplate.getForObject(query, String.class);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!" + data);


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
			session.setAttribute("flag", "http://www.printableworldflags.com/icon-flags/32/United%20Kingdom.png");
	
			session.setAttribute("language", "BU");
			
			return "redirect:index?language=es";
		} else {
			System.out.println("smenqm ot bulgarski na angliiski");
			session.setAttribute("language", "EN");
			session.setAttribute("flag", "http://www.printableworldflags.com/icon-flags/32/Bulgaria.png");
			
			return "redirect:index?language=en";
		}

	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "ChangeUnits", method = RequestMethod.GET)
	public static String ChangeUnits(HttpSession session) {
		System.out.println("Sega e " + session.getAttribute("units"));
		if (session.getAttribute("units").equals("false")) {
			System.out.println("ot true na false");
			session.setAttribute("units", "true");
			session.setAttribute("unitTemp", "&#8451");
			session.setAttribute("unitSpeed", "Km/h");
			session.setAttribute("unitMM", "mm");
		} else {
			System.out.println("smenqm ot false na true");
			session.setAttribute("units", "false");
			session.setAttribute("unitTemp", "F");
			session.setAttribute("unitSpeed", "Mph");
			session.setAttribute("unitMM", "In");
		}
		return "index";
	}
	void saveOrNullItemsInSession(HttpSession session,String cityName,ArrayList<ArrayList<DayForcast>> forTheThreeTablesAtOnce,ArrayList<DayForcast> list3days,ArrayList<DayForcast> listweekenddays,ArrayList<DayForcast> list5days,ArrayList<HourForcast> list24hours){
		session.setAttribute(cityName+session.getAttribute("language"), forTheThreeTablesAtOnce);
		session.setAttribute("city", WordUtils.capitalize(cityName));
		session.setAttribute("list3days", list3days);
		session.setAttribute("listweekenddays", listweekenddays);
		session.setAttribute("list5days", list5days);
		session.setAttribute("list24hours", list24hours);
		session.setAttribute("backGroundGIF", SearchController.chooseBackGroundGIF(list24hours));
		session.setAttribute(cityName+session.getAttribute("language")+"24", list24hours);
	}
	@RequestMapping(value = "planner")
	public String loadPlanner(HttpSession session) {
		session.setAttribute("page", "planner.jsp");
		return "index";
	}
	@RequestMapping(value = "facebookEvents")
	public String loadShukarii(HttpSession session) {
		session.setAttribute("page", "fb.jsp");
		return "index";
	}
	@RequestMapping(value = "worldMap")
	public String loadWorldMap(HttpSession session) {
		session.setAttribute("page", "map.jsp");
		return "index";
	}
}
