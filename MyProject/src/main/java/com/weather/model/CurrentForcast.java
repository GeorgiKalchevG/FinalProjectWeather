package com.weather.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentForcast implements Serializable {
	private static final long serialVersionUID = 6505531860926358318L;
	private String time;
	private String day;
	private String month;
	private String year;
	private String startTime;
	private String summary;
	private String precipIntensity;
	private String precipProbabilty;
	private String latitude;
	private String longitude;
	private String icon;
	private String temperature;
	private String temperatureFH;
	private String apparentTemperature;
	private String apparentTemperatureFH;
	private String dewPoint;
	private String humidity;
	private String windSpeed;
	private String windSpeedMPH;
	private String windBearing;
	private String visibility;
	private String visibilityMiles;
	private String cloudCover;
	private String pressure;
	private String ozone;

	public String getVisibilityMiles() {
		try {
			if (this.visibilityMiles == null)
				this.visibilityMiles = ((Double.parseDouble(this.visibility) * 0.621371192)+ "").substring(0, 4);
		} catch (NullPointerException e) {
			System.out.println("nqma visibility v km dafuq?");
		}
		return visibilityMiles;
	}

	public void setVisibilityMiles(String visibilityMiles) {
		this.visibilityMiles = visibilityMiles;
	}

	public String getStartTime() {
		if (this.startTime == null) {
			System.out.println("Parse long " + Long.parseLong(this.time));
			System.out.println("This time " + this.time);
			long miliseconds = Long.parseLong(this.time) * 1000;
			Date d = new Date(miliseconds);
			System.out.println("kak taka nqma start time" + d + " " + miliseconds);
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			this.startTime = df.format(d);
		}
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPrecipIntensity() {
		return precipIntensity;
	}

	public void setPrecipIntensity(String precipIntensity) {
		this.precipIntensity = precipIntensity;
	}

	public String getPrecipProbabilty() {
		return precipProbabilty;
	}

	public void setPrecipProbabilty(String precipProbabilty) {
		this.precipProbabilty = precipProbabilty;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTemperatureFH() {
		if (this.temperatureFH == null)
			this.temperatureFH = ((Double.parseDouble(this.temperature) * 9 / 5 + 32) + "").substring(0, 4);
		return temperatureFH;
	}

	public void setTemperatureFH(String temperatureFH) {
		this.temperatureFH = temperatureFH;
	}

	public String getApparentTemperature() {
		return apparentTemperature;
	}

	public void setApparentTemperature(String apparentTemperature) {
		this.apparentTemperature = apparentTemperature;
	}

	public String getApparentTemperatureFH() {
		return apparentTemperatureFH;
	}

	public void setApparentTemperatureFH(String apparentTemperatureFH) {
		this.apparentTemperatureFH = apparentTemperatureFH;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getDewPoint() {
		return dewPoint;
	}

	public void setDewPoint(String dewPoint) {
		this.dewPoint = dewPoint;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getWindSpeedMPH() {
		if (windSpeedMPH == null) {
			this.windSpeedMPH = (Double.parseDouble(windSpeed)*0.621371192 + "").substring(0, 4);
		}
		return windSpeedMPH;
	}

	public void setWindSpeedMPH(String windSpeedMPH) {
		this.windSpeedMPH = windSpeedMPH;
	}

	public String getWindBearing() {
		return windBearing;
	}

	public void setWindBearing(String windBearing) {
		this.windBearing = windBearing;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getCloudCover() {
		return cloudCover;
	}

	public void setCloudCover(String cloudCover) {
		this.cloudCover = cloudCover;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getOzone() {
		return ozone;
	}

	public void setOzone(String ozone) {
		this.ozone = ozone;
	}

}
