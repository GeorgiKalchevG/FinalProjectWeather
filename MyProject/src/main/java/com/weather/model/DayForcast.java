package com.weather.model;

import java.io.Serializable;

public class DayForcast implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9112089845645250822L;
	private String cityName;
	private String countryName;
	private long epoch;
	private String pretty;
	private int day;
	private int month;
	private int year;
	private String monthName;
	private String weekday;
	private String tempHighCel;
	private String tempHighFahr;
	private String tempLowCel;
	private String tempLowFahr;
	private String conditions;
	private String icon_url;
	private double qpf_alldayIn;
	private double qpf_alldayMM;
	private double qpf_dayIn;
	private double qpf_dayMM;
	private double qpf_nightIn;
	private double qpf_nightMM;
	private double snow_alldayIn;
	private double snow_alldayMM;
	private double snow_day;
	private double snow_dayMM;
	private double snow_nightIn;
	private double snow_nightMM;
	private double maxwind_mph;
	private double maxwind_kph;
	private String maxwind_dir;
	private double maxwind_degrees;
	private double avehumidity;
	private double maxhumidity;
	private double minhumidity;
	private int pop;
	
	@Override
	public boolean equals(Object obj) {
		DayForcast thisObject = (DayForcast) obj;
		if(thisObject.cityName.equals(this.cityName) && thisObject.countryName.equals(this.countryName)){
			return true;
		}
		return false;
	}
	
	
	public double getPop() {
		return pop;
	}
	public void setPop(int pop) {
		this.pop = pop;
	}
	public double getAvehumidity() {
		return avehumidity;
	}
	public void setAvehumidity(double avehumidity) {
		this.avehumidity = avehumidity;
	}
	public double getMaxhumidity() {
		return maxhumidity;
	}
	public void setMaxhumidity(double maxhumidity) {
		this.maxhumidity = maxhumidity;
	}
	public double getMinhumidity() {
		return minhumidity;
	}
	public void setMinhumidity(double minhumidity) {
		this.minhumidity = minhumidity;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public long getEpoch() {
		return epoch;
	}
	public void setEpoch(long epoch) {
		this.epoch = epoch;
	}
	
	public String getPretty() {
		return pretty;
	}
	public void setPretty(String pretty) {
		this.pretty = pretty;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public String getWeekday() {
		return weekday;
	}
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}
	public String getTempHighCel() {
		return tempHighCel;
	}
	public void setTempHighCel(String tempHighCel) {
		this.tempHighCel = tempHighCel;
	}
	public String getTempHighFahr() {
		return tempHighFahr;
	}
	public void setTempHighFahr(String tempHighFahr) {
		this.tempHighFahr = tempHighFahr;
	}
	public String getTempLowCel() {
		return tempLowCel;
	}
	public void setTempLowCel(String tempLowCel) {
		this.tempLowCel = tempLowCel;
	}
	public String getTempLowFahr() {
		return tempLowFahr;
	}
	public void setTempLowFahr(String tempLowFahr) {
		this.tempLowFahr = tempLowFahr;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public String getIcon_url() {
		return icon_url;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	public double getQpf_alldayIn() {
		return qpf_alldayIn;
	}
	public void setQpf_alldayIn(double qpf_alldayIn) {
		this.qpf_alldayIn = qpf_alldayIn;
	}
	public double getQpf_alldayMM() {
		return qpf_alldayMM;
	}
	public void setQpf_alldayMM(double qpf_alldayMM) {
		this.qpf_alldayMM = qpf_alldayMM;
	}
	public double getQpf_dayIn() {
		return qpf_dayIn;
	}
	public void setQpf_dayIn(double qpf_dayIn) {
		this.qpf_dayIn = qpf_dayIn;
	}
	public double getQpf_dayMM() {
		return qpf_dayMM;
	}
	public void setQpf_dayMM(double qpf_dayMM) {
		this.qpf_dayMM = qpf_dayMM;
	}
	public double getQpf_nightIn() {
		return qpf_nightIn;
	}
	public void setQpf_nightIn(double qpf_nightIn) {
		this.qpf_nightIn = qpf_nightIn;
	}
	public double getQpf_nightMM() {
		return qpf_nightMM;
	}
	public void setQpf_nightMM(double qpf_nightMM) {
		this.qpf_nightMM = qpf_nightMM;
	}
	public double getSnow_alldayIn() {
		return snow_alldayIn;
	}
	public void setSnow_alldayIn(double snow_alldayIn) {
		this.snow_alldayIn = snow_alldayIn;
	}
	public double getSnow_alldayMM() {
		return snow_alldayMM;
	}
	public void setSnow_alldayMM(double snow_alldayMM) {
		this.snow_alldayMM = snow_alldayMM;
	}
	public double getSnow_day() {
		return snow_day;
	}
	public void setSnow_day(double snow_day) {
		this.snow_day = snow_day;
	}
	public double getSnow_dayMM() {
		return snow_dayMM;
	}
	public void setSnow_dayMM(double snow_dayMM) {
		this.snow_dayMM = snow_dayMM;
	}
	public double getSnow_nightIn() {
		return snow_nightIn;
	}
	public void setSnow_nightIn(double snow_nightIn) {
		this.snow_nightIn = snow_nightIn;
	}
	public double getSnow_nightMM() {
		return snow_nightMM;
	}
	public void setSnow_nightMM(double snow_nightMM) {
		this.snow_nightMM = snow_nightMM;
	}
	public double getMaxwind_mph() {
		return maxwind_mph;
	}
	public void setMaxwind_mph(double maxwind_mph) {
		this.maxwind_mph = maxwind_mph;
	}
	public double getMaxwind_kph() {
		return maxwind_kph;
	}
	public void setMaxwind_kph(double maxwind_kph) {
		this.maxwind_kph = maxwind_kph;
	}
	public String getMaxwind_dir() {
		return maxwind_dir;
	}
	public void setMaxwind_dir(String maxwind_dir) {
		this.maxwind_dir = maxwind_dir;
	}
	public double getMaxwind_degrees() {
		return maxwind_degrees;
	}
	public void setMaxwind_degrees(double maxwind_degrees) {
		this.maxwind_degrees = maxwind_degrees;
	}
	
	
	
	
}
