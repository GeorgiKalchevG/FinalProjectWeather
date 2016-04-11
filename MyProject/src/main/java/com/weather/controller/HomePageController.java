package com.weather.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.WordUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.weather.dao.ILocationDAO;
import com.weather.dao.LocationDAO;
import com.weather.model.DayForcast;
import com.weather.model.HourForcast;
import com.weather.model.User;


@Controller
public class HomePageController {
	private static final String PLANNER_JSP = "planner.jsp";
	private static final String FACEBOOK_JSP = "fb.jsp";
	private static final String MAP_JSP = "map.jsp";
	private static final String FAHRENHEIT_SYMBOL = "&#8457";
	private static final String CELSIUS_SYMBOL = "&#8451";
	private static final String SESSION_BACK_GROUND_GIF = "backGroundGIF";
	private static final String SESSION_LIST24HOURS = "list24hours";
	private static final String SESSION_LIST5DAYS = "list5days";
	private static final String SESSION_LISTWEEKENDDAYS = "listweekenddays";
	private static final String SESSION_LIST3DAYS = "list3days";
	private static final String SESSION_CITY = "city";
	private static final String SESSION_UNIT_KM_MILES = "unitKmMiles";
	private static final String SESSION_UNIT_MM = "unitMM";
	private static final String SESSION_UNIT_SPEED = "unitSpeed";
	private static final String SESSION_UNIT_TEMP = "unitTemp";
	private static final String SESSION_QUEUEFOR_CITIES = "queueforCities";
	private static final String SESSION_USER = "user";
	private static final String DEFAULT_IP = "46.10.58.161";
	private static final String IP_ADDRESS = "ipAddress";
	private static final String SESSION_DEFAUL_ICONS = "i";
	private static final String SESSION_ICONS = "icons";
	private static final String SESSION_UNITS = "units";
	private static final String SESSION_PAGE = "page";
	private static final String SESSION_FLAG = "flag";
	private static final String SESSION_LANGUAGE = "language";
	private static final int MAX_HISTORY_CITIES = 3;
	ILocationDAO dao = LocationDAO.getInstance();
	String[] arr = {"Afghanistan","Albania","Algeria","Andorra","Angola","Antigua and Barbuda","Argentina","Armenia","Australia","Austria","Azerbaijan","Bahamas","Bahrain","BG","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bhutan","Bolivia","Bosnia and Herzegovina","Botswana","Brazil","Brunei","Bulgaria","Burkina Faso","Burundi","Cabo Verde","Cambodia","Cameroon","Canada","Central African Republic","Chad","Chile","China","Colombia","Comoros","Congo","Costa Rica","Cote d'Ivoire","Croatia","Cuba","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia","Fiji","Finland","France","Gabon","Gambia","Georgia","Germany","Ghana","Greece","Grenada","Guatemala","Guinea","Guinea-Bissau","Guyana","Haiti","Honduras","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Israel","Italy","JamaicaJapan","Jordan","Kazakhstan","Kenya","Kiribati","Kosovo","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liecht","ensteinLithuania","Luxembourg","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montenegro","Morocco","Mozambique","Myanmar (Burma)","Namibia","Nauru","Nepal","Netherlands","New Zealand","Nicaragua","Niger","Nigeria","North Korea","Norway","Oman","Pakistan","Palau","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Qatar","Romania","Russia","Rwanda","St. Kitts and Nevis","St. Lucia","St. Vincent and The Grenadines","Samoa","San Marino","Sao Tome and Principe","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Korea","South Sudan","Spain","Sri Lanka","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor-Leste","Togo","Tonga","Trinidad and Tobago","Tunisia","Turkey","Turkmenistan","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States of America","Uruguay","Uzbekistan","Vanuatu","Vatican City","Venezuela","Vietnam","Yemen","Zambia","Zimbabwe"};
	ArrayList<String> allCountries = new ArrayList<>(Arrays.asList(arr));
	

	@RequestMapping(value = "index")
	String loadHomePage(Model model, HttpServletRequest request, HttpSession session) {
		checkIfNewSession(session);
		System.out.println("zarejdam vednuj indexa");
		String ipAddress=(String) session.getAttribute(IP_ADDRESS);
		if (ipAddress==null) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("0:0:0:0:0:0:0:1")) {
				ipAddress = DEFAULT_IP;
			} 
			session.setAttribute(IP_ADDRESS, ipAddress);
			System.out.println(ipAddress);
		}
		System.out.println(ipAddress);
		String cityName = dao.getCityNameByIp(ipAddress);
		System.out.println("What city name we got from getcITYNAMEBYIP IN HPC " + cityName);
		ArrayList<ArrayList<DayForcast>> forTheThreeTablesAtOnce = null;
		if(session.getAttribute(cityName+session.getAttribute(SESSION_LANGUAGE))!=null){
			forTheThreeTablesAtOnce = (ArrayList<ArrayList<DayForcast>>) session.getAttribute(cityName+session.getAttribute(SESSION_LANGUAGE));
			ArrayList<HourForcast> list24hours = (ArrayList<HourForcast>) session.getAttribute(cityName+session.getAttribute(SESSION_LANGUAGE)+"24");
			saveOrNullItemsInSession(session,cityName,forTheThreeTablesAtOnce,forTheThreeTablesAtOnce.get(0),forTheThreeTablesAtOnce.get(1),forTheThreeTablesAtOnce.get(2),list24hours);
		}else{
		forTheThreeTablesAtOnce = dao.getFiveDaysFromWUnderground(
				cityName.split("/")[0], cityName.split("/")[1], session.getAttribute(SESSION_LANGUAGE).toString(),(User)session.getAttribute(SESSION_USER),null);
		
		ArrayList<HourForcast> list24hours = dao.getDayFromWUnderground(cityName.split("/")[0], cityName.split("/")[1],
				session.getAttribute(SESSION_LANGUAGE).toString(),(User)session.getAttribute(SESSION_USER),null);
		saveOrNullItemsInSession(session,cityName,forTheThreeTablesAtOnce,forTheThreeTablesAtOnce.get(0),forTheThreeTablesAtOnce.get(1),forTheThreeTablesAtOnce.get(2),list24hours);

		}
		if (session.getAttribute(SESSION_QUEUEFOR_CITIES) == null) {
			LinkedList<DayForcast> queueCities = new LinkedList<>();
			queueCities.add(forTheThreeTablesAtOnce.get(0).get(0));
			session.setAttribute(SESSION_QUEUEFOR_CITIES, queueCities);
		}
		return "index";

	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping("search")
	String getDataForLocation(HttpSession session, @RequestParam String city, @RequestParam String country, HttpServletRequest req) {
		checkIfNewSession(session);
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
				session.setAttribute(SESSION_PAGE, "notFound.jsp");
				return "index";
			}
			
				
		}

		if (!country.isEmpty()) {
			
			System.out.println("city name: "+city);
			System.out.println("country name: "+country);
			System.out.println("tuka li sme");
			ArrayList<ArrayList<DayForcast>> forTheThreeTablesAtOnce = dao.getFiveDaysFromWUnderground(country, city,
					session.getAttribute(SESSION_LANGUAGE).toString(),(User)session.getAttribute(SESSION_USER),req.getParameter("locID"));
			if (forTheThreeTablesAtOnce == null) {
				saveOrNullItemsInSession(session,"N/A",forTheThreeTablesAtOnce,null,null,null,null);
				System.out.println("vrushta ako e null pri city info");
				session.setAttribute(SESSION_PAGE, "cityInfo.jsp");
				return "index";
			}
			else if(forTheThreeTablesAtOnce.get(0).get(0).getIcon_url()==null){
				session.setAttribute("cityForCurrentSearch", WordUtils.capitalize(city));
				saveOrNullItemsInSession(session,null,forTheThreeTablesAtOnce,null,null,null,null);
				session.setAttribute("availableCities", forTheThreeTablesAtOnce.get(0));
				System.out.println("vrushta ako e nqma url snimka  pri chooseCityPage");
				session.setAttribute(SESSION_PAGE, "chooseCityPage.jsp");
				return "index"; 
			}else {
				ArrayList<HourForcast> list24hours = dao.getDayFromWUnderground(country, city,
						session.getAttribute(SESSION_LANGUAGE).toString(),(User)session.getAttribute(SESSION_USER),req.getParameter("locID"));
				saveOrNullItemsInSession(session,WordUtils.capitalize(country+"/"+city),forTheThreeTablesAtOnce,forTheThreeTablesAtOnce.get(0),forTheThreeTablesAtOnce.get(1),forTheThreeTablesAtOnce.get(2),list24hours);

				LinkedList<DayForcast> queueCities = session.getAttribute(SESSION_QUEUEFOR_CITIES)==null?new LinkedList<DayForcast>():(LinkedList<DayForcast>) session.getAttribute(SESSION_QUEUEFOR_CITIES);
				if(!queueCities.contains(forTheThreeTablesAtOnce.get(0).get(0))){
				if (queueCities.size() > MAX_HISTORY_CITIES) {
					queueCities.removeLast();
				}
				queueCities.addFirst(forTheThreeTablesAtOnce.get(0).get(0));
				session.setAttribute(SESSION_QUEUEFOR_CITIES, queueCities);
				}
			}
			if(country.equals("")){
				System.out.println("vrushta sled celiq if pri index");
				return "index";
			}
			System.out.println("vrushta sled celiq if pri city info");
			if(req.getParameter("fromAjax") != null){
				System.out.println("tuka from ajaxa");
				System.out.println(req.getParameter("locID"));
				return "cityInfo";
			}
			else if(req.getParameter("fromMap")!=null){
				System.out.println("ot mapa");
				session.setAttribute(SESSION_PAGE, "cityInfo.jsp");
				return "index";
			}
			}else{
				System.out.println("tuka ne e from ajaxa");
				session.setAttribute(SESSION_PAGE, "cityInfo.jsp");
				return "index";
			}
		System.out.println("vrushta sled krainiq if pri index");
		session.setAttribute(SESSION_PAGE, "cityInfo.jsp");
		return "index";

	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody String buildList(HttpServletRequest request) {
		String query = "http://autocomplete.wunderground.com/aq?query=" + request.getParameter(SESSION_CITY);
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
		checkIfNewSession(session);
		System.out.println("ezikyt v momenta e " + session.getAttribute(SESSION_LANGUAGE));
		if (session.getAttribute(SESSION_LANGUAGE)==null){
			session.setAttribute(SESSION_FLAG, "http://www.printableworldflags.com/icon-flags/32/Bulgaria.png");
			session.setAttribute(SESSION_LANGUAGE, "EN");

		}
			
		if (session.getAttribute(SESSION_LANGUAGE).equals("EN")) {
			System.out.println("smenqm ot angliiski na bulgarski");
			session.setAttribute(SESSION_FLAG, "http://www.printableworldflags.com/icon-flags/32/United%20Kingdom.png");
	
			session.setAttribute(SESSION_LANGUAGE, "BU");
			return "redirect:index?language=bu";
		} else {
			System.out.println("smenqm ot bulgarski na angliiski");
			session.setAttribute(SESSION_LANGUAGE, "EN");
			session.setAttribute(SESSION_FLAG, "http://www.printableworldflags.com/icon-flags/32/Bulgaria.png");
			
			return "redirect:index?language=en";
		}

	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "ChangeUnits", method = RequestMethod.GET)
	public static String changeUnits(HttpSession session) {
		HomePageController.checkIfNewSession(session);
		System.out.println("Sega e " + session.getAttribute(SESSION_UNITS));
		if (session.getAttribute(SESSION_UNITS).equals("false")) {
			System.out.println("ot true na false");
			session.setAttribute(SESSION_UNITS, "true");
			session.setAttribute(SESSION_UNIT_TEMP, CELSIUS_SYMBOL);
			session.setAttribute(SESSION_UNIT_SPEED, "Km/h");
			session.setAttribute(SESSION_UNIT_MM, "mm");
			session.setAttribute(SESSION_UNIT_KM_MILES, "Km");
		} else {
			System.out.println("smenqm ot false na true");
			session.setAttribute(SESSION_UNITS, "false");
			session.setAttribute(SESSION_UNIT_TEMP, FAHRENHEIT_SYMBOL);
			session.setAttribute(SESSION_UNIT_SPEED, "Mph");
			session.setAttribute(SESSION_UNIT_MM, "In");
			session.setAttribute(SESSION_UNIT_KM_MILES, "Miles");
		}
		return "index";
	}
	void saveOrNullItemsInSession(HttpSession session,String cityName,ArrayList<ArrayList<DayForcast>> forTheThreeTablesAtOnce,ArrayList<DayForcast> list3days,ArrayList<DayForcast> listweekenddays,ArrayList<DayForcast> list5days,ArrayList<HourForcast> list24hours){
		session.setAttribute(cityName+session.getAttribute(SESSION_LANGUAGE), forTheThreeTablesAtOnce);
		session.setAttribute(SESSION_CITY, WordUtils.capitalize(cityName));
		session.setAttribute(SESSION_LIST3DAYS, list3days);
		session.setAttribute(SESSION_LISTWEEKENDDAYS, listweekenddays);
		session.setAttribute(SESSION_LIST5DAYS, list5days);
		session.setAttribute(SESSION_LIST24HOURS, list24hours);
		session.setAttribute(SESSION_BACK_GROUND_GIF, SearchController.chooseBackGroundGIF(list24hours));
		session.setAttribute(cityName+session.getAttribute(SESSION_LANGUAGE)+"24", list24hours);
	}
	@RequestMapping(value = "planner")
	public String loadPlanner(HttpSession session) {
		session.setAttribute(SESSION_PAGE, PLANNER_JSP);
		return "index";
	}
	@RequestMapping(value = "facebookEvents")
	public String loadShukarii(HttpSession session) {
		session.setAttribute(SESSION_PAGE, FACEBOOK_JSP);
		return "index";
	}
	@RequestMapping(value = "worldMap")
	public String loadWorldMap(HttpSession session) {
		session.setAttribute(SESSION_PAGE, MAP_JSP);
		return "index";
	}
	public static void checkIfNewSession(HttpSession session){
		if (session.getAttribute(SESSION_LANGUAGE) == null) {
			session.setAttribute(SESSION_LANGUAGE, "EN");
			session.setAttribute(SESSION_FLAG, "http://www.printableworldflags.com/icon-flags/32/Bulgaria.png");
		}
		session.setAttribute(SESSION_PAGE, "cityInfo.jsp");
		if (session.getAttribute(SESSION_UNITS) == null) {
			System.out.println("setvam units na true");
			session.setAttribute(SESSION_UNITS, "false");
			changeUnits(session);
		}
		if (session.getAttribute(SESSION_ICONS) == null) {
			System.out.println("setvam icons na i");
			session.setAttribute(SESSION_ICONS, SESSION_DEFAUL_ICONS);
		}
	}
}
