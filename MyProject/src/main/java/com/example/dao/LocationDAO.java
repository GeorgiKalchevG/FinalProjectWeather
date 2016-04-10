package com.example.dao;

import java.util.ArrayList;
import java.util.Calendar;


import org.springframework.web.client.RestTemplate;

import com.example.model.CurrentForcast;

import com.example.model.DayForcast;
import com.example.model.Event;

import com.example.model.HourForcast;
import com.example.model.Location;
import com.example.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LocationDAO implements ILocationDAO {
	String apiKey = "9885a830e31d144089368b0a44b2f9f7";

	@Override
	public String getCityNameByIp(String ip) {
		String ipToGeotag = "http://api.db-ip.com/addrinfo?addr=" + ip
				+ "&api_key=2847ed47c9cf4242bb2e09a10aeb3c313c5ebb06";
		RestTemplate restTemplate = new RestTemplate();
		String data = restTemplate.getForObject(ipToGeotag, String.class);
		String cityName = new JsonParser().parse(data).getAsJsonObject().get("city").getAsString();
		String countryName = new JsonParser().parse(data).getAsJsonObject().get("country").getAsString();
		return countryName + "/" + cityName;
	}

	@Override
	public ArrayList<Location> collectMajorCitys(String ip) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HourForcast> getDayFromWUnderground(String country, String city, String language, User user, String locID) {
		System.out.println("2          " + city.replace(' ', '_') + "/" + country.replace(' ', '_') + ".json");
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<HourForcast> DayForcast = new ArrayList<>();
		String wundergroungUrl;
		if(locID!=null){
			wundergroungUrl = "http://api.wunderground.com/api/88f2b27b9585755c/hourly"+locID+".json";
			System.out.println("za 24 4asa 1: location id "+locID+"zaqkva "+ wundergroungUrl);
		}
		else{
			wundergroungUrl = "http://api.wunderground.com/api/88f2b27b9585755c/hourly/q/"
				+ country.replace(' ', '_') + "/" + city.replace(' ', '_') + ".json";
			System.out.println("za 24 4asa 2: location id "+locID+"zaqkva "+ wundergroungUrl);
		}
		JsonObject weatherData = new JsonParser().parse(restTemplate.getForObject(wundergroungUrl, String.class))
				.getAsJsonObject();
		System.out.println(weatherData.toString());
		JsonArray array = weatherData.get("hourly_forecast").getAsJsonArray();
		for (int i = 0; i < 24; i++) {
			DayForcast.add(createHour(array.get(i).getAsJsonObject(), language, user));
		}
		return DayForcast;
	}

	private HourForcast createHour(JsonObject jsonElement, String language, User user) {
		HourForcast forcast = new HourForcast();
		forcast.setHour(Integer.parseInt(jsonElement.get("FCTTIME").getAsJsonObject().get("hour").getAsString()));
		forcast.setYear(Integer.parseInt(jsonElement.get("FCTTIME").getAsJsonObject().get("year").getAsString()));
		forcast.setMonth(Integer.parseInt(jsonElement.get("FCTTIME").getAsJsonObject().get("mon").getAsString()));
		forcast.setDay(Integer.parseInt(jsonElement.get("FCTTIME").getAsJsonObject().get("mday").getAsString()));
		forcast.setWeekday(language.equals("BU")
				? changeDayLanguage(jsonElement.get("FCTTIME").getAsJsonObject().get("weekday_name").getAsString())
				: jsonElement.get("FCTTIME").getAsJsonObject().get("weekday_name").getAsString());
		forcast.setTempC(Integer.parseInt(jsonElement.get("temp").getAsJsonObject().get("metric").getAsString()));
		forcast.setTempFH(Integer.parseInt(jsonElement.get("temp").getAsJsonObject().get("english").getAsString()));
		forcast.setConditions(
				language.equals("BU") ? changeFromEnglishToBularian(jsonElement.get("condition").getAsString())
						: jsonElement.get("condition").getAsString());
		forcast.setIcon_url(user == null ? jsonElement.get("icon_url").getAsString()
				: switchUserIcons(jsonElement.get("icon_url").getAsString(), user.getIcon().charAt(0)));
		forcast.setSky(Integer.parseInt(jsonElement.get("sky").getAsString()));
		forcast.setWindKPH(Integer.parseInt(jsonElement.get("wspd").getAsJsonObject().get("metric").getAsString()));
		forcast.setWindMPH(Integer.parseInt(jsonElement.get("wspd").getAsJsonObject().get("english").getAsString()));
		forcast.setDir(jsonElement.get("wdir").getAsJsonObject().get("dir").getAsString());
		forcast.setDirDegrees(Integer.parseInt(jsonElement.get("wdir").getAsJsonObject().get("degrees").getAsString()));
		forcast.setUvIndex(Integer.parseInt(jsonElement.get("uvi").getAsString()));
		forcast.setHumidity(Integer.parseInt(jsonElement.get("humidity").getAsString()));
		forcast.setFeelsLikeC(
				Integer.parseInt(jsonElement.get("feelslike").getAsJsonObject().get("metric").getAsString()));
		forcast.setFeelsLikeFH(
				Integer.parseInt(jsonElement.get("feelslike").getAsJsonObject().get("english").getAsString()));
		forcast.setQpfIN(Double.parseDouble(jsonElement.get("qpf").getAsJsonObject().get("english").getAsString()));
		forcast.setQpfMM(Integer.parseInt(jsonElement.get("qpf").getAsJsonObject().get("metric").getAsString()));
		forcast.setSnowIN(Double.parseDouble(jsonElement.get("snow").getAsJsonObject().get("english").getAsString()));
		forcast.setSnowMM(Integer.parseInt(jsonElement.get("snow").getAsJsonObject().get("metric").getAsString()));
		forcast.setPop(Integer.parseInt(jsonElement.get("pop").getAsString()));
		forcast.setMslphinHg(
				Double.parseDouble(jsonElement.get("mslp").getAsJsonObject().get("english").getAsString()));
		forcast.setMslphPa(Integer.parseInt(jsonElement.get("mslp").getAsJsonObject().get("metric").getAsString()));
		return forcast;
	}

	private DayForcast createDay(JsonObject jsonElement, String cityName, String countryName, String language,
			User user) {
		DayForcast forcast = new DayForcast();
		System.out.println(jsonElement.toString());
		forcast.setCityName(cityName);
		forcast.setCountryName(countryName);
		forcast.setConditions(
				language.equals("BU") ? changeFromEnglishToBularian(jsonElement.get("conditions").getAsString())
						: jsonElement.get("conditions").getAsString());
		forcast.setWeekday(language.equals("BU")
				? changeDayLanguage(jsonElement.get("date").getAsJsonObject().get("weekday").getAsString())
				: jsonElement.get("date").getAsJsonObject().get("weekday").getAsString());
		forcast.setDay(jsonElement.get("date").getAsJsonObject().get("day").getAsInt());
		forcast.setMonth(jsonElement.get("date").getAsJsonObject().get("month").getAsInt());
		forcast.setYear(jsonElement.get("date").getAsJsonObject().get("year").getAsInt());
		forcast.setEpoch(jsonElement.get("date").getAsJsonObject().get("epoch").getAsLong());
		System.out.println(user == null ? "null e manqk" : "ne e null");
		forcast.setIcon_url(user == null ? (jsonElement.get("icon_url").getAsString())
				: switchUserIcons(jsonElement.get("icon_url").getAsString(), user.getIcon().charAt(0)));
		forcast.setPop(jsonElement.get("pop").getAsInt());
		forcast.setMaxwind_degrees(jsonElement.get("maxwind").getAsJsonObject().get("degrees").getAsDouble());
		forcast.setMaxwind_dir(jsonElement.get("maxwind").getAsJsonObject().get("dir").getAsString());
		forcast.setMaxwind_kph(jsonElement.get("maxwind").getAsJsonObject().get("kph").getAsDouble());
		forcast.setMaxwind_mph(jsonElement.get("maxwind").getAsJsonObject().get("mph").getAsDouble());
		forcast.setMonthName(jsonElement.get("date").getAsJsonObject().get("monthname").getAsString());
		forcast.setPretty(jsonElement.get("date").getAsJsonObject().get("pretty").getAsString());
		forcast.setQpf_alldayIn(jsonElement.get("qpf_allday").getAsJsonObject().get("in").getAsDouble());
		forcast.setQpf_alldayMM(jsonElement.get("qpf_allday").getAsJsonObject().get("mm").getAsDouble());
		forcast.setSnow_alldayIn(jsonElement.get("snow_allday").getAsJsonObject().get("in").getAsDouble());
		forcast.setSnow_alldayMM(jsonElement.get("snow_allday").getAsJsonObject().get("cm").getAsDouble());
		forcast.setTempHighCel(jsonElement.get("high").getAsJsonObject().get("celsius").getAsString());
		forcast.setTempHighFahr(jsonElement.get("high").getAsJsonObject().get("fahrenheit").getAsString());
		forcast.setTempLowCel(jsonElement.get("low").getAsJsonObject().get("celsius").getAsString());
		forcast.setTempLowFahr(jsonElement.get("low").getAsJsonObject().get("fahrenheit").getAsString());
		forcast.setAvehumidity(jsonElement.get("avehumidity").getAsDouble());
		forcast.setMaxhumidity(jsonElement.get("maxhumidity").getAsDouble());
		forcast.setMinhumidity(jsonElement.get("minhumidity").getAsDouble());
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
			array = weatherData.get("forecast").getAsJsonObject().get("simpleforecast").getAsJsonObject()
					.get("forecastday").getAsJsonArray();
		} catch (NullPointerException e) {
			try {
				array = weatherData.get("response").getAsJsonObject().get("results").getAsJsonArray();
				ArrayList<DayForcast> arrayWithCities = new ArrayList<>();
				for (int i = 0; i < array.size(); i++) {
					DayForcast currLocation = new DayForcast();
					JsonObject currObjectLocation = array.get(i).getAsJsonObject();
					currLocation.setCountryName(currObjectLocation.get("country_name").getAsString());
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
		for (int i = 0; i < 3; i++) { // vrushta prognoza za 5 dena i go puham v
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
	}

	@Override
	public String plannerResponse(String from, String to, String city, String country) {

		String url = "http://api.wunderground.com/api/88f2b27b9585755c/planner_" + from.split("/")[0]

				+ from.split("/")[1] + to.split("/")[0] + to.split("/")[1] + "/q/" + country + "/" + city + ".json";
		System.out.println(url);
		RestTemplate restTemplate = new RestTemplate();
		JsonObject obj = new JsonParser().parse(restTemplate.getForObject(url, String.class)).getAsJsonObject();
		System.out.println(obj.toString());
		JsonObject chanceOf = obj.get("trip").getAsJsonObject().get("chance_of").getAsJsonObject();
		JsonObject tempHiJson = obj.get("trip").getAsJsonObject().get("temp_high").getAsJsonObject();
		JsonObject tempLoJson = obj.get("trip").getAsJsonObject().get("temp_low").getAsJsonObject();
		JsonObject precipJson = obj.get("trip").getAsJsonObject().get("precip").getAsJsonObject();
		JsonObject dewHiJson = obj.get("trip").getAsJsonObject().get("dewpoint_high").getAsJsonObject();
		JsonObject dewLoJson = obj.get("trip").getAsJsonObject().get("dewpoint_low").getAsJsonObject();
		JsonArray variation = new JsonArray();
		String[] value = { "min", "avg", "max" };
		String unit = "C";
		for (int i = 0; i < 3; i++) {
			variation.add(Double.parseDouble(tempHiJson.get(value[i]).getAsJsonObject().get(unit).getAsString()));
			variation.add(Double.parseDouble(tempLoJson.get(value[i]).getAsJsonObject().get(unit).getAsString()));
			variation.add(Double.parseDouble(precipJson.get(value[i]).getAsJsonObject().get("cm").getAsString()));
			variation.add(Double.parseDouble(dewHiJson.get(value[i]).getAsJsonObject().get(unit).getAsString()));
			variation.add(Double.parseDouble(dewLoJson.get(value[i]).getAsJsonObject().get(unit).getAsString()));
			System.out.println(variation.toString());
		}

		JsonObject obj1 = new JsonObject();
		JsonArray arr = new JsonArray();
		arr.add(Integer.parseInt(chanceOf.get("tempoversixty").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer
				.parseInt(chanceOf.get("chanceofpartlycloudyday").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer.parseInt(chanceOf.get("chanceofprecip").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer.parseInt(chanceOf.get("chanceofrainday").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer.parseInt(chanceOf.get("tempoverfreezing").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer
				.parseInt(chanceOf.get("chanceofsunnycloudyday").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer.parseInt(chanceOf.get("chanceofcloudyday").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer.parseInt(chanceOf.get("chanceoffogday").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer.parseInt(chanceOf.get("chanceofwindyday").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer.parseInt(chanceOf.get("chanceofthunderday").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer.parseInt(chanceOf.get("chanceofhailday").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer
				.parseInt(chanceOf.get("chanceofsnowonground").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer.parseInt(chanceOf.get("chanceoftornadoday").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer.parseInt(chanceOf.get("chanceofsultryday").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer.parseInt(chanceOf.get("chanceofhumidday").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer.parseInt(chanceOf.get("tempbelowfreezing").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer.parseInt(chanceOf.get("tempoverninety").getAsJsonObject().get("percentage").getAsString()));
		arr.add(Integer.parseInt(chanceOf.get("chanceofsnowday").getAsJsonObject().get("percentage").getAsString()));
		obj1.add("array", arr);
		String title = obj.get("trip").getAsJsonObject().get("title").getAsString();
		String cloudCover = obj.get("trip").getAsJsonObject().get("cloud_cover").getAsJsonObject().get("cond")
				.getAsString();
		obj1.addProperty("title", title);
		obj1.addProperty("cloudCover", cloudCover);
		obj1.add("variation", variation);
		return obj1.toString();// new
								// ChanceOfTypeOfWeather(chanceOFTypeOfWeather,
								// cloudCover, title, tempHigh, tempLow, precip,
								// dewpointHigh, dewpointLow);
	}

	@Override
	public ArrayList<Event> getEventsFromFacebookForcast(String responseFromFB) {
		try {
			JsonArray obj = new JsonParser().parse(responseFromFB).getAsJsonObject().get("events").getAsJsonObject()
					.get("data").getAsJsonArray();
			ArrayList<Event> facebookEventsForcast = new ArrayList<Event>();
			for (int i = 0; i < 5; i++) {
				Event currEvent = new Event();
				JsonObject data = obj.get(i).getAsJsonObject();
				currEvent.setStart_time(data.get("start_time").getAsString());
				currEvent.setCover_url(data.get("cover").getAsJsonObject().get("source").getAsString());
				currEvent.setDescription(data.get("description").getAsString());
				currEvent.setNameOfEvent(data.get("name").getAsString());
				currEvent.setNameOfEvent(data.get("name").getAsString());
				currEvent.setNameOfPlace(data.get("place").getAsJsonObject().get("name").getAsString());
				currEvent.setCity(data.get("place").getAsJsonObject().get("location").getAsJsonObject().get("city")
						.getAsString());
				currEvent.setCountry(data.get("place").getAsJsonObject().get("location").getAsJsonObject()
						.get("country").getAsString());
				currEvent.setLatitude(data.get("place").getAsJsonObject().get("location").getAsJsonObject()
						.get("latitude").getAsString());
				currEvent.setLongitude(data.get("place").getAsJsonObject().get("location").getAsJsonObject()
						.get("longitude").getAsString());
				try {
					currEvent.setTicket_uri(data.get("ticket_uri").getAsString());
					currEvent.setStreet(data.get("place").getAsJsonObject().get("location").getAsJsonObject()
							.get("street").getAsString());
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
		String url = "https://api.forecast.io/forecast/034143e2c674af082f9336e044c19312/" + latitude + "," + longitude
				+ "," + startTime + "" + "?exclude=hourly,daily,flags&units=ca";
		System.out.println(url);
		RestTemplate restTemplate = new RestTemplate();
		JsonObject obj = new JsonParser().parse(restTemplate.getForObject(url, String.class)).getAsJsonObject()
				.get("currently").getAsJsonObject();
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
		String query = "http://api.geonames.org/timezoneJSON?lat="+latitude+"&lng="+longitude+"&username=gzufi1";
		RestTemplate restTemplate = new RestTemplate();
		JsonObject locationData = new JsonParser().parse(restTemplate.getForObject(query, String.class))
				.getAsJsonObject();
		String city=locationData.get("city").getAsString();
		String country=locationData.get("country_name").getAsString();
		return country+"/"+city;
	}

}

// http://api.wunderground.com/api/88f2b27b9585755c/forecast10day/q/CA/San_Francisco.json