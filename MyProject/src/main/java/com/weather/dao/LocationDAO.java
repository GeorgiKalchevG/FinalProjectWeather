package com.weather.dao;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.lang.WordUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weather.model.CurrentForcast;
import com.weather.model.DayForcast;
import com.weather.model.Event;
import com.weather.model.HourForcast;

import com.weather.model.User;

public class LocationDAO implements ILocationDAO {
	private static final String GEOIP_KEY = "f4907a66fb924bc6a9d34610ab082fd2"; //f4907a66fb924bc6a9d34610ab082fd2  e75dc720b8ce4497cd1aed66d4c1cced
	private static final String CURRENTLY = "currently";
	private static final String GEONAMES_USERNAME = "gzufi1";
	private static final String FORECAST_IO_KEY = "034143e2c674af082f9336e044c19312";
	private static final String FB_STREET = "street";
	private static final String FB_LONGITUDE = "longitude";
	private static final String FB_LATITUDE = "latitude";
	private static final String FB_COUNTRY = "country";
	private static final String FB_LOCATION = "location";
	private static final String FB_PLACE = "place";
	private static final String FB_NAME = "name";
	private static final String FB_DESCRIPTION = "description";
	private static final String FB_SOURCE = "source";
	private static final String FB_COVER = "cover";
	private static final String FB_START_TIME = "start_time";
	private static final String FB_DATA = "data";
	private static final String FB_EVENTS = "events";
	private static final String VARIATION2 = "variation";
	private static final String CLOUD_COVER2 = "cloudCover";
	private static final String COND = "cond";
	private static final String CLOUD_COVER = "cloud_cover";
	private static final String TITLE_TRIP = "title";
	private static final String CHANCEOFSNOWDAY = "chanceofsnowday";
	private static final String TEMPOVERNINETY = "tempoverninety";
	private static final String TEMPBELOWFREEZING = "tempbelowfreezing";
	private static final String CHANCEOFHUMIDDAY = "chanceofhumidday";
	private static final String CHANCEOFSULTRYDAY = "chanceofsultryday";
	private static final String CHANCEOFTORNADODAY = "chanceoftornadoday";
	private static final String CHANCEOFSNOWONGROUND = "chanceofsnowonground";
	private static final String CHANCEOFHAILDAY = "chanceofhailday";
	private static final String CHANCEOFTHUNDERDAY = "chanceofthunderday";
	private static final String CHANCEOFWINDYDAY = "chanceofwindyday";
	private static final String CHANCEOFFOGDAY = "chanceoffogday";
	private static final String CHANCEOFCLOUDYDAY = "chanceofcloudyday";
	private static final String CHANCEOFSUNNYCLOUDYDAY = "chanceofsunnycloudyday";
	private static final String TEMPOVERFREEZING = "tempoverfreezing";
	private static final String CHANCEOFRAINDAY = "chanceofrainday";
	private static final String CHANCEOFPRECIP = "chanceofprecip";
	private static final String CHANCEOFPARTLYCLOUDYDAY = "chanceofpartlycloudyday";
	private static final String PERCENTAGE = "percentage";
	private static final String TEMPOVERSIXTY = "tempoversixty";
	private static final String MAX = "max";
	private static final String AVG = "avg";
	private static final String MIN = "min";
	private static final String DEWPOINT_LOW = "dewpoint_low";
	private static final String DEWPOINT_HIGH = "dewpoint_high";
	private static final String PRECIP = "precip";
	private static final String TEMP_LOW = "temp_low";
	private static final String TEMP_HIGH = "temp_high";
	private static final String CHANCE_OF = "chance_of";
	private static final String TRIP = "trip";
	private static final String FORECASTDAY = "forecastday";
	private static final String FORECAST = "forecast";
	private static final String MINHUMIDITY = "minhumidity";
	private static final String MAXHUMIDITY = "maxhumidity";
	private static final String AVEHUMIDITY = "avehumidity";
	private static final String LOW = "low";
	private static final String FAHRENHEIT = "fahrenheit";
	private static final String CELSIUS = "celsius";
	private static final String HIGH = "high";
	private static final String CM = "cm";
	private static final String SNOW_ALLDAY = "snow_allday";
	private static final int FOR_THREE_DAYS_FORCAST =3;
	private static final String MM = "mm";
	private static final String IN = "in";
	private static final String QPF_ALLDAY = "qpf_allday";
	private static final String DATE_STYLE = "pretty";
	private static final String MONTHNAME = "monthname";
	private static final String MPH = "mph";
	private static final String KPH = "kph";
	private static final String MAXWIND = "maxwind";
	private static final String EPOCH = "epoch";
	private static final String MONTH = "month";
	private static final String DAY = "day";
	private static final String WEEKDAY = "weekday";
	private static final String DATE = "date";
	private static final String CONDITIONS = "conditions";
	private static final String MSLP = "mslp";
	private static final String POP = "pop";
	private static final String SNOW = "snow";
	private static final String QPF = "qpf";
	private static final String FEELSLIKE = "feelslike";
	private static final String HUMIDITY = "humidity";
	private static final String UVI = "uvi";
	private static final String DEGREES = "degrees";
	private static final String WDIR = "wdir";
	private static final String DIR = "dir";
	private static final String WSPD = "wspd";
	private static final String SKY = "sky";
	private static final String ICON_URL = "icon_url";
	private static final String BU = "BU";
	private static final String TEMPERATURE = "temp";
	private static final String CONDITION = "condition";
	private static final String ENGLISH = "english";
	private static final String METRIC = "metric";
	private static final String WEEKDAY_NAME = "weekday_name";
	private static final String MDAY = "mday";
	private static final String MON = "mon";
	private static final String YEAR = "year";
	private static final String HOUR = "hour";
	private static final String SIMPLEFORECAST = "simpleforecast";
	private static final String RESULTS = "results";
	private static final String WUNDERGROUND_KEY = "88f2b27b9585755c";
	private static final String HOURLY_FORECAST = "hourly_forecast";
	private static final String CITY = "city";
	private static final String COUNTRY_NAME = "country_name";
	private static final String RESPONSE = "response";
	private final static String apiKey = "9885a830e31d144089368b0a44b2f9f7";
	private final static String CITY_BY_IP_LOCATION = FB_LOCATION;
	private static LocationDAO instance=null;
	private  LocationDAO() {}
	public synchronized static LocationDAO getInstance(){
		if(instance==null)
			instance=new LocationDAO();
		return instance;
	}
	@Override
	public String getCityNameByIp(String ip) {
		String ipToGeotag = "https://api.geoips.com/ip/"+ip+"/key/"+GEOIP_KEY+"/output/json";
		RestTemplate restTemplate = new RestTemplate();
		String data = restTemplate.getForObject(ipToGeotag, String.class);
		JsonObject countryNameObject = new JsonParser().parse(data).getAsJsonObject().get(RESPONSE).getAsJsonObject();
		System.out.println(countryNameObject);
	
		String countryName=countryNameObject.get(CITY_BY_IP_LOCATION).getAsJsonObject().get(COUNTRY_NAME).getAsString();
		countryName=WordUtils.capitalize(countryName.toLowerCase());
		String ipToGeotag1 = "http://api.db-ip.com/addrinfo?addr=" + ip
				+ "&api_key=2847ed47c9cf4242bb2e09a10aeb3c313c5ebb06";
		//RestTemplate restTemplate = new RestTemplate();
		String data1 = restTemplate.getForObject(ipToGeotag1, String.class);
		String cityName = new JsonParser().parse(data1).getAsJsonObject().get(CITY).getAsString();
		//String countryName = new JsonParser().parse(data).getAsJsonObject().get("country").getAsString();
		return countryName + "/" + cityName;
	}

	

	@Override
	public ArrayList<HourForcast> getDayFromWUnderground(String country, String city, String language, User user, String locID) {
		System.out.println("2          " + city.replace(' ', '_') + "/" + country.replace(' ', '_') + ".json");
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<HourForcast> DayForcast = new ArrayList<>();
		String wundergroungUrl;
		if(locID==null){
			//opitvam se da namerq locID
			locID=tryToFindLocID(country,city);
		}
		if(locID!=null){
			wundergroungUrl = "http://api.wunderground.com/api/"+WUNDERGROUND_KEY+"/hourly"+locID+".json";
			System.out.println("za 24 4asa 1: location id "+locID+"zaqkva "+ wundergroungUrl);
		}
		else{
			wundergroungUrl = "http://api.wunderground.com/api/"+""+WUNDERGROUND_KEY+""+"/hourly/q/"
				+ country.replace(' ', '_') + "/" + city.replace(' ', '_') + ".json";
			System.out.println("za 24 4asa 2: location id "+locID+"zaqkva "+ wundergroungUrl);
		}
		JsonObject weatherData = new JsonParser().parse(restTemplate.getForObject(wundergroungUrl, String.class))
				.getAsJsonObject();
		System.out.println(weatherData.toString());
		JsonArray array = weatherData.get(HOURLY_FORECAST).getAsJsonArray();
		for (int i = 0; i < 24; i++) {
			DayForcast.add(createHour(array.get(i).getAsJsonObject(), language, user));
		}
		return DayForcast;
	}

	private HourForcast createHour(JsonObject jsonElement, String language, User user) {
		HourForcast forcast = new HourForcast();
		forcast.setHour(Integer.parseInt(jsonElement.get("FCTTIME").getAsJsonObject().get(HOUR).getAsString()));
		forcast.setYear(Integer.parseInt(jsonElement.get("FCTTIME").getAsJsonObject().get(YEAR).getAsString()));
		forcast.setMonth(Integer.parseInt(jsonElement.get("FCTTIME").getAsJsonObject().get(MON).getAsString()));
		forcast.setDay(Integer.parseInt(jsonElement.get("FCTTIME").getAsJsonObject().get(MDAY).getAsString()));
		forcast.setWeekday(language.equals(BU)
				? changeDayLanguage(jsonElement.get("FCTTIME").getAsJsonObject().get(WEEKDAY_NAME).getAsString())
				: jsonElement.get("FCTTIME").getAsJsonObject().get(WEEKDAY_NAME).getAsString());
		forcast.setTempC(Integer.parseInt(jsonElement.get(TEMPERATURE).getAsJsonObject().get(METRIC).getAsString()));
		forcast.setTempFH(Integer.parseInt(jsonElement.get(TEMPERATURE).getAsJsonObject().get(ENGLISH).getAsString()));
		forcast.setConditions(
				language.equals(BU) ? changeFromEnglishToBularian(jsonElement.get(CONDITION).getAsString())
						: jsonElement.get(CONDITION).getAsString());
		forcast.setIcon_url(user == null ? jsonElement.get(ICON_URL).getAsString()
				: switchUserIcons(jsonElement.get(ICON_URL).getAsString(), user.getIcon().charAt(0)));
		forcast.setSky(Integer.parseInt(jsonElement.get(SKY).getAsString()));
		forcast.setWindKPH(Integer.parseInt(jsonElement.get(WSPD).getAsJsonObject().get(METRIC).getAsString()));
		forcast.setWindMPH(Integer.parseInt(jsonElement.get(WSPD).getAsJsonObject().get(ENGLISH).getAsString()));
		forcast.setDir(jsonElement.get(WDIR).getAsJsonObject().get(DIR).getAsString());
		forcast.setDirDegrees(Integer.parseInt(jsonElement.get(WDIR).getAsJsonObject().get(DEGREES).getAsString()));
		forcast.setUvIndex(Integer.parseInt(jsonElement.get(UVI).getAsString()));
		forcast.setHumidity(Integer.parseInt(jsonElement.get(HUMIDITY).getAsString()));
		forcast.setFeelsLikeC(
				Integer.parseInt(jsonElement.get(FEELSLIKE).getAsJsonObject().get(METRIC).getAsString()));
		forcast.setFeelsLikeFH(
				Integer.parseInt(jsonElement.get(FEELSLIKE).getAsJsonObject().get(ENGLISH).getAsString()));
		forcast.setQpfIN(Double.parseDouble(jsonElement.get(QPF).getAsJsonObject().get(ENGLISH).getAsString()));
		forcast.setQpfMM(Integer.parseInt(jsonElement.get(QPF).getAsJsonObject().get(METRIC).getAsString()));
		forcast.setSnowIN(Double.parseDouble(jsonElement.get(SNOW).getAsJsonObject().get(ENGLISH).getAsString()));
		forcast.setSnowMM(Integer.parseInt(jsonElement.get(SNOW).getAsJsonObject().get(METRIC).getAsString()));
		forcast.setPop(Integer.parseInt(jsonElement.get(POP).getAsString()));
		forcast.setMslphinHg(
				Double.parseDouble(jsonElement.get(MSLP).getAsJsonObject().get(ENGLISH).getAsString()));
		forcast.setMslphPa(Integer.parseInt(jsonElement.get(MSLP).getAsJsonObject().get(METRIC).getAsString()));
		return forcast;
	}

	private DayForcast createDay(JsonObject jsonElement, String cityName, String countryName, String language,
			User user) {
		DayForcast forcast = new DayForcast();
		System.out.println(jsonElement.toString());
		forcast.setCityName(cityName);
		forcast.setCountryName(countryName);
		forcast.setConditions(
				language.equals(BU) ? changeFromEnglishToBularian(jsonElement.get(CONDITIONS).getAsString())
						: jsonElement.get(CONDITIONS).getAsString());
		forcast.setWeekday(language.equals(BU)
				? changeDayLanguage(jsonElement.get(DATE).getAsJsonObject().get(WEEKDAY).getAsString())
				: jsonElement.get(DATE).getAsJsonObject().get(WEEKDAY).getAsString());
		forcast.setDay(jsonElement.get(DATE).getAsJsonObject().get(DAY).getAsInt());
		forcast.setMonth(jsonElement.get(DATE).getAsJsonObject().get(MONTH).getAsInt());
		forcast.setYear(jsonElement.get(DATE).getAsJsonObject().get(YEAR).getAsInt());
		forcast.setEpoch(jsonElement.get(DATE).getAsJsonObject().get(EPOCH).getAsLong());
		System.out.println(user == null ? "null e manqk" : "ne e null");
		forcast.setIcon_url(user == null ? (jsonElement.get(ICON_URL).getAsString())
				: switchUserIcons(jsonElement.get(ICON_URL).getAsString(), user.getIcon().charAt(0)));
		forcast.setPop(jsonElement.get(POP).getAsInt());
		forcast.setMaxwind_degrees(jsonElement.get(MAXWIND).getAsJsonObject().get(DEGREES).getAsDouble());
		forcast.setMaxwind_dir(jsonElement.get(MAXWIND).getAsJsonObject().get(DIR).getAsString());
		forcast.setMaxwind_kph(jsonElement.get(MAXWIND).getAsJsonObject().get(KPH).getAsDouble());
		forcast.setMaxwind_mph(jsonElement.get(MAXWIND).getAsJsonObject().get(MPH).getAsDouble());
		forcast.setMonthName(jsonElement.get(DATE).getAsJsonObject().get(MONTHNAME).getAsString());
		forcast.setPretty(jsonElement.get(DATE).getAsJsonObject().get(DATE_STYLE).getAsString());
		forcast.setQpf_alldayIn(jsonElement.get(QPF_ALLDAY).getAsJsonObject().get(IN).getAsDouble());
		forcast.setQpf_alldayMM(jsonElement.get(QPF_ALLDAY).getAsJsonObject().get(MM).getAsDouble());
		forcast.setSnow_alldayIn(jsonElement.get(SNOW_ALLDAY).getAsJsonObject().get(IN).getAsDouble());
		forcast.setSnow_alldayMM(jsonElement.get(SNOW_ALLDAY).getAsJsonObject().get(CM).getAsDouble());
		forcast.setTempHighCel(jsonElement.get(HIGH).getAsJsonObject().get(CELSIUS).getAsString());
		forcast.setTempHighFahr(jsonElement.get(HIGH).getAsJsonObject().get(FAHRENHEIT).getAsString());
		forcast.setTempLowCel(jsonElement.get(LOW).getAsJsonObject().get(CELSIUS).getAsString());
		forcast.setTempLowFahr(jsonElement.get(LOW).getAsJsonObject().get(FAHRENHEIT).getAsString());
		forcast.setAvehumidity(jsonElement.get(AVEHUMIDITY).getAsDouble());
		forcast.setMaxhumidity(jsonElement.get(MAXHUMIDITY).getAsDouble());
		forcast.setMinhumidity(jsonElement.get(MINHUMIDITY).getAsDouble());
		return forcast;
	}

	@Override
	public ArrayList<ArrayList<DayForcast>> getFiveDaysFromWUnderground(String country, String city, String language,
			User user,String locID) {
		System.out.println("city " + city + "country " + country);
		ArrayList<ArrayList<DayForcast>> forTheThreeTablesAtOnce = new ArrayList<ArrayList<DayForcast>>();
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<DayForcast> threeDayForcast = new ArrayList<>();
		ArrayList<DayForcast> fiveDayForcast = new ArrayList<>();
		ArrayList<DayForcast> weekendDayForcast = new ArrayList<>();
		System.out.println("from location dao ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		String wundergroungUrl;
		if(locID==null){
			locID=tryToFindLocID(country,city);
		}
		if(locID!=null){
			wundergroungUrl="http://api.wunderground.com/api/d1141c29f3b4adb3/forecast10day"+locID+".json";
			System.out.println("za 5 dena 1: location id "+locID+"zaqkva "+ wundergroungUrl);
		}
		else{
			wundergroungUrl = "http://api.wunderground.com/api/d1141c29f3b4adb3/forecast10day/q/"
					+ country.replace(' ', '_') + "/" + city.replace(' ', '_') + ".json";
			System.out.println("za 5 dena 2: location id "+locID+"zaqkva "+ wundergroungUrl);
		}
		JsonObject weatherData = new JsonParser().parse(restTemplate.getForObject(wundergroungUrl, String.class))
				.getAsJsonObject();
		System.out.println(wundergroungUrl);
		System.out.println(weatherData.toString());
		JsonArray array;
		try {
			array = weatherData.get(FORECAST).getAsJsonObject().get(SIMPLEFORECAST).getAsJsonObject()
					.get(FORECASTDAY).getAsJsonArray();
		} catch (NullPointerException e) {
			try {
				array = weatherData.get(RESPONSE).getAsJsonObject().get(RESULTS).getAsJsonArray();
				ArrayList<DayForcast> arrayWithCities = new ArrayList<>();
				for (int i = 0; i < array.size(); i++) {
					DayForcast currLocation = new DayForcast();
					JsonObject currObjectLocation = array.get(i).getAsJsonObject();
					currLocation.setCountryName(currObjectLocation.get(COUNTRY_NAME).getAsString());
					arrayWithCities.add(currLocation);
				}
				forTheThreeTablesAtOnce.add(arrayWithCities);
				return forTheThreeTablesAtOnce;
			} catch (NullPointerException e1) {
				return null;
			}
		}
		Calendar c = Calendar.getInstance();
		c.getTime();
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		// ako e 1 + 4 =5
		// ako e 2 + 3 = 5
		// ako e 3 + 2 =5
		// ako e 4 + 1 =5
		// ako e 5 + 7 = 12
		// ako e 6 + 6 = 12
		// ako e 7 + 5 = 12
		// (5-1)
		// i+(5-i) proverqvam kolko dni ima do weekenda i vzimam dnite samoza
		// weekenda v tozi for
		dayOfWeek = dayOfWeek < 5 ? (5 - dayOfWeek) : (12 - dayOfWeek);
		for (int i = dayOfWeek; i < dayOfWeek + 3; i++) {
			weekendDayForcast.add(createDay(array.get(i).getAsJsonObject(), city, country, language, user));
		}
		for (int i = 0; i < FOR_THREE_DAYS_FORCAST; i++) { // vrushta prognoza za 5 dena i go puham v
										// purvoto pole na arraylista
			threeDayForcast.add(createDay(array.get(i).getAsJsonObject(), city, country, language, user));
		}
		forTheThreeTablesAtOnce.add(threeDayForcast);
		// addvam trite dni v 5-cata da ne pravq crateday otnovo i advam trite
		// dni v arraylista s progonozatakato
		fiveDayForcast.addAll(threeDayForcast);
		for (int i = 3; i < 5; i++) {
			fiveDayForcast.add(createDay(array.get(i).getAsJsonObject(), city, country, language, user));
		}
		forTheThreeTablesAtOnce.add(weekendDayForcast);
		forTheThreeTablesAtOnce.add(fiveDayForcast);
		return forTheThreeTablesAtOnce; // na pyrvo mqsto s index 0 e 3dnevna
										// index 1 e za weekenda index 2 e
										// 5dnevnna
	}//sled alexander i kiro

	@Override
	public String plannerResponse(String from, String to, String city, String country,String locID) {
		String url;
		if(locID==null){
			locID=tryToFindLocID(country, city);
		}
		if(locID==null){
		url = "http://api.wunderground.com/api/"+WUNDERGROUND_KEY+"/planner_" + from.split("/")[0]+ from.split("/")[1] + to.split("/")[0] + to.split("/")[1] + "/q/" + country + "/" + city + ".json";
		}else{
			url = "http://api.wunderground.com/api/"+WUNDERGROUND_KEY+"/planner_" + from.split("/")[0]+ from.split("/")[1] + to.split("/")[0] + to.split("/")[1] + locID+".json";
		}
		System.out.println(url);
		RestTemplate restTemplate = new RestTemplate();
		JsonObject obj = new JsonParser().parse(restTemplate.getForObject(url, String.class)).getAsJsonObject();
		System.out.println(obj.toString());
		JsonObject chanceOf = obj.get(TRIP).getAsJsonObject().get(CHANCE_OF).getAsJsonObject();
		JsonObject tempHiJson = obj.get(TRIP).getAsJsonObject().get(TEMP_HIGH).getAsJsonObject();
		JsonObject tempLoJson = obj.get(TRIP).getAsJsonObject().get(TEMP_LOW).getAsJsonObject();
		JsonObject precipJson = obj.get(TRIP).getAsJsonObject().get(PRECIP).getAsJsonObject();
		JsonObject dewHiJson = obj.get(TRIP).getAsJsonObject().get(DEWPOINT_HIGH).getAsJsonObject();
		JsonObject dewLoJson = obj.get(TRIP).getAsJsonObject().get(DEWPOINT_LOW).getAsJsonObject();
		JsonArray variation = new JsonArray();
		String[] value = { MIN, AVG, MAX };
		String unit = "C";
		for (int i = 0; i < 3; i++) {
			variation.add(Double.parseDouble(tempHiJson.get(value[i]).getAsJsonObject().get(unit).getAsString()));
			variation.add(Double.parseDouble(tempLoJson.get(value[i]).getAsJsonObject().get(unit).getAsString()));
			variation.add(Double.parseDouble(precipJson.get(value[i]).getAsJsonObject().get(CM).getAsString()));
			variation.add(Double.parseDouble(dewHiJson.get(value[i]).getAsJsonObject().get(unit).getAsString()));
			variation.add(Double.parseDouble(dewLoJson.get(value[i]).getAsJsonObject().get(unit).getAsString()));
			System.out.println(variation.toString());
		}

		JsonObject obj1 = new JsonObject();
		JsonArray arr = new JsonArray();
		arr.add(Integer.parseInt(chanceOf.get(TEMPOVERSIXTY).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer
				.parseInt(chanceOf.get(CHANCEOFPARTLYCLOUDYDAY).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer.parseInt(chanceOf.get(CHANCEOFPRECIP).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer.parseInt(chanceOf.get(CHANCEOFRAINDAY).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer.parseInt(chanceOf.get(TEMPOVERFREEZING).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer
				.parseInt(chanceOf.get(CHANCEOFSUNNYCLOUDYDAY).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer.parseInt(chanceOf.get(CHANCEOFCLOUDYDAY).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer.parseInt(chanceOf.get(CHANCEOFFOGDAY).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer.parseInt(chanceOf.get(CHANCEOFWINDYDAY).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer.parseInt(chanceOf.get(CHANCEOFTHUNDERDAY).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer.parseInt(chanceOf.get(CHANCEOFHAILDAY).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer
				.parseInt(chanceOf.get(CHANCEOFSNOWONGROUND).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer.parseInt(chanceOf.get(CHANCEOFTORNADODAY).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer.parseInt(chanceOf.get(CHANCEOFSULTRYDAY).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer.parseInt(chanceOf.get(CHANCEOFHUMIDDAY).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer.parseInt(chanceOf.get(TEMPBELOWFREEZING).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer.parseInt(chanceOf.get(TEMPOVERNINETY).getAsJsonObject().get(PERCENTAGE).getAsString()));
		arr.add(Integer.parseInt(chanceOf.get(CHANCEOFSNOWDAY).getAsJsonObject().get(PERCENTAGE).getAsString()));
		obj1.add("array", arr);
		String title = obj.get(TRIP).getAsJsonObject().get(TITLE_TRIP).getAsString();
		String cloudCover = obj.get(TRIP).getAsJsonObject().get(CLOUD_COVER).getAsJsonObject().get(COND)
				.getAsString();
		obj1.addProperty(TITLE_TRIP, title);
		obj1.addProperty(CLOUD_COVER2, cloudCover);
		obj1.add(VARIATION2, variation);
		return obj1.toString();// new
								// ChanceOfTypeOfWeather(chanceOFTypeOfWeather,
								// cloudCover, title, tempHigh, tempLow, precip,
								// dewpointHigh, dewpointLow);
	}

	@Override
	public ArrayList<Event> getEventsFromFacebookForcast(String responseFromFB) {
		try {
			JsonArray obj = new JsonParser().parse(responseFromFB).getAsJsonObject().get(FB_EVENTS).getAsJsonObject()
					.get(FB_DATA).getAsJsonArray();
			ArrayList<Event> facebookEventsForcast = new ArrayList<Event>();
			for (int i = 0; i < 5; i++) {
				Event currEvent = new Event();
				JsonObject data = obj.get(i).getAsJsonObject();
				currEvent.setStart_time(data.get(FB_START_TIME).getAsString());
				currEvent.setCover_url(data.get(FB_COVER).getAsJsonObject().get(FB_SOURCE).getAsString());
				currEvent.setDescription(data.get(FB_DESCRIPTION).getAsString());
				currEvent.setNameOfEvent(data.get(FB_NAME).getAsString());
				currEvent.setNameOfEvent(data.get(FB_NAME).getAsString());
				currEvent.setNameOfPlace(data.get(FB_PLACE).getAsJsonObject().get(FB_NAME).getAsString());
				currEvent.setCity(data.get(FB_PLACE).getAsJsonObject().get(FB_LOCATION).getAsJsonObject().get(CITY)
						.getAsString());
				currEvent.setCountry(data.get(FB_PLACE).getAsJsonObject().get(FB_LOCATION).getAsJsonObject()
						.get(FB_COUNTRY).getAsString());
				currEvent.setLatitude(data.get(FB_PLACE).getAsJsonObject().get(FB_LOCATION).getAsJsonObject()
						.get(FB_LATITUDE).getAsString());
				currEvent.setLongitude(data.get(FB_PLACE).getAsJsonObject().get(FB_LOCATION).getAsJsonObject()
						.get(FB_LONGITUDE).getAsString());
				try {
					currEvent.setTicket_uri(data.get("ticket_uri").getAsString());
					currEvent.setStreet(data.get(FB_PLACE).getAsJsonObject().get(FB_LOCATION).getAsJsonObject()
							.get(FB_STREET).getAsString());
				} catch (NullPointerException e) {
					System.out.println("Nqma street v lokociqta na eventa ili ticket");
				}
				currEvent.setWeather(
						getWeather(currEvent.getLatitude(), currEvent.getLongitude(), currEvent.getStart_time()));
				facebookEventsForcast.add(currEvent);
			}
			return facebookEventsForcast;
		} catch (NullPointerException e) {
			System.out.println("User is not logged in");
		}
		return null;
	}

	private CurrentForcast getWeather(String latitude, String longitude, String startTime) {
		CurrentForcast currForcast = new CurrentForcast();
		Gson g = new Gson();
		String url = "https://api.forecast.io/forecast/"+FORECAST_IO_KEY+"/" + latitude + "," + longitude
				+ "," + startTime + "" + "?exclude=hourly,daily,flags&units=ca";
		System.out.println(url);
		RestTemplate restTemplate = new RestTemplate();
		JsonObject obj = new JsonParser().parse(restTemplate.getForObject(url, String.class)).getAsJsonObject()
				.get(CURRENTLY).getAsJsonObject();
		System.out.println(obj.toString());
		currForcast = g.fromJson(obj, CurrentForcast.class);
		return currForcast;
	}

	public static String changeFromEnglishToBularian(String fromEnglish) {
		switch (fromEnglish) {
		case "Chance of Flurries":
			return "Очакват се превалявания";
		case "Chance of Rain":
			return "Вероятност за дъжд";
		case "Chance Rain":
			return "Вероятност за дъжд";
		case "Chance of Freezing Rain":
			return "Шанс на замразяване дъжд";
		case "Chance of Sleet":
			return "Шанс на суграшица";
		case "Chance of Snow":
			return "Шанс на сняг";
		case "Chance of Thunderstorms":
			return "Шанс за гръмотевични бури";
		case "Chance of a Thunderstorm":
			return "Шанс за гръмотевични буря";
		case "Clear":
			return "Ясно";
		case "Cloudy":
			return "Облачно";
		case "Flurries":
			return "Вихрушки";
		case "Fog":
			return "Мъгла";
		case "Haze":
			return "Мътно";
		case "Mostly Cloudy":
			return "Предимно облачно";
		case "Mostly Sunny":
			return "Предимно слънчево";
		case "Partly Cloudy":
			return "Частично облачно";
		case "Partly Sunny":
			return "Частично слънчево";
		case "Freezing Rain":
			return "Смразяващ дъжд";
		case "Rain":
			return "Дъжд";
		case "Sleet":
			return "Суграшица";
		case "Snow":
			return "Сняг";
		case "Sunny":
			return "Слънчево";
		case "Thunderstorms":
			return "Гръмотевични бури";
		case "Thunderstorm":
			return "Гръмотевични буря";
		case "Overcast":
			return "Облачно";
		case "Scattered Clouds":
			return "Разпръснати облаци";
		default:
			return fromEnglish;
		}
	}

	public static String changeDayLanguage(String fromDay) {
		switch (fromDay) {
		case "Monday":
			return "Понеделник";
		case "Tuesday":
			return "Вторник";
		case "Wednesday":
			return "Сряда";
		case "Thursday":
			return "Четвърък";
		case "Friday":
			return "Петък";
		case "Saturday":
			return "Събота";
		default:
			return "Неделя";
		}
	}

	public static String switchUserIcons(String fromIcon, char toIcon) {
		char[] fromIconChar = fromIcon.toCharArray();
		fromIconChar[26] = toIcon;
		return new String(fromIconChar);
	}

	@Override
	public String getCityAndCountyByCoordinates(String latitude, String longitude) {
		String query = "http://api.geonames.org/timezoneJSON?lat="+latitude+"&lng="+longitude+"&username="+GEONAMES_USERNAME;
		RestTemplate restTemplate = new RestTemplate();
		JsonObject locationData = new JsonParser().parse(restTemplate.getForObject(query, String.class))
				.getAsJsonObject();
		String city=locationData.get(CITY).getAsString();
		String country=locationData.get(COUNTRY_NAME).getAsString();
		return country+"/"+city;
	}
	public String tryToFindLocID(String country,String city){
		System.out.println("Vlizam v tryTofind LOC ID");
		String query = "http://autocomplete.wunderground.com/aq?query=" + city;
		System.out.println("zaqvkata mi e "+ query );
		RestTemplate restTemplate = new RestTemplate();
		JsonArray results = new JsonParser().parse(restTemplate.getForObject(query, String.class))
				.getAsJsonObject().get("RESULTS").getAsJsonArray();
		System.out.println("rezultata "+results);
		JsonObject currentLocation;
		for(int i=0;i<results.size();i++){
			currentLocation=results.get(i).getAsJsonObject();
			if(currentLocation.get("name").getAsString().equals(city+", "+country)){
				System.out.println("vrushtam L");
				return currentLocation.get("l").getAsString();
			}
		}//
		System.out.println("vrushtam null pak");
		// System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		// object.toString());
		return null;
	}




}

// http://api.wunderground.com/api/"+"88f2b27b9585755c"+"/forecast10day/q/CA/San_Francisco.json