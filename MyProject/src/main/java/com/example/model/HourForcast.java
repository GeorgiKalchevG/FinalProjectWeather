package com.example.model;

import java.io.Serializable;

public class HourForcast implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5119458822591018562L;
	private int day;
	private int month;
	private int year;
	private int hour;
	private String weekday;
	private int tempFH;
	private int tempC;
	private String conditions;
	private String icon_url;
	private int sky;
	private int pop;
	private double qpfIN;
	private int qpfMM;
	private int snowMM;
	private double snowIN;
	private int windMPH;
	private int windKPH;
	private String dir;
	private int dirDegrees;
	private int uvIndex;
	private int humidity;
	private int feelsLikeFH;
	private int feelsLikeC;
	private int mslphPa;
	private double mslphinHg;
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
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public String getWeekday() {
		return weekday;
	}
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}
	public int getTempFH() {
		return tempFH;
	}
	public void setTempFH(int tempFH) {
		this.tempFH = tempFH;
	}
	public int getTempC() {
		return tempC;
	}
	public void setTempC(int tempC) {
		this.tempC = tempC;
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
	public int getSky() {
		return sky;
	}
	public void setSky(int sky) {
		this.sky = sky;
	}
	public int getPop() {
		return pop;
	}
	public void setPop(int pop) {
		this.pop = pop;
	}
	public double getQpfIN() {
		return qpfIN;
	}
	public void setQpfIN(double qpfIN) {
		this.qpfIN = qpfIN;
	}
	public int getQpfMM() {
		return qpfMM;
	}
	public void setQpfMM(int qpfMM) {
		this.qpfMM = qpfMM;
	}
	public int getSnowMM() {
		return snowMM;
	}
	public void setSnowMM(int snowMM) {
		this.snowMM = snowMM;
	}
	public double getSnowIN() {
		return snowIN;
	}
	public void setSnowIN(double snowIN) {
		this.snowIN = snowIN;
	}
	public int getWindMPH() {
		return windMPH;
	}
	public void setWindMPH(int windMPH) {
		this.windMPH = windMPH;
	}
	public int getWindKPH() {
		return windKPH;
	}
	public void setWindKPH(int windKPH) {
		this.windKPH = windKPH;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public int getDirDegrees() {
		return dirDegrees;
	}
	public void setDirDegrees(int dirDegrees) {
		this.dirDegrees = dirDegrees;
	}
	public int getUvIndex() {
		return uvIndex;
	}
	public void setUvIndex(int uvIndex) {
		this.uvIndex = uvIndex;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public int getFeelsLikeFH() {
		return feelsLikeFH;
	}
	public void setFeelsLikeFH(int feelsLikeFH) {
		this.feelsLikeFH = feelsLikeFH;
	}
	public int getFeelsLikeC() {
		return feelsLikeC;
	}
	public void setFeelsLikeC(int feelsLikeC) {
		this.feelsLikeC = feelsLikeC;
	}
	public int getMslphPa() {
		return mslphPa;
	}
	public void setMslphPa(int mslphPa) {
		this.mslphPa = mslphPa;
	}
	public double getMslphinHg() {
		return mslphinHg;
	}
	public void setMslphinHg(double mslphinHg) {
		this.mslphinHg = mslphinHg;
	}
}
