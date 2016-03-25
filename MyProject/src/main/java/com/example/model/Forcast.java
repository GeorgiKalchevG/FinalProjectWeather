package com.example.model;

public class Forcast {

	private long time;
	private String data;
	private String img;
	private String tempVar;
	private String tempCur;
	private String forcast;
	private String wind;
	private String windDirection;
	private String rainChance;
	private String rainQuantity;
	private String stromChance;
	private String cloudness;
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTempVar() {
		return tempVar;
	}
	public void setTempVar(String tempVar) {
		this.tempVar = tempVar;
	}
	public String getTempCur() {
		return tempCur;
	}
	public void setTempCur(String tempCur) {
		this.tempCur = tempCur;
	}
	public String getForcast() {
		return forcast;
	}
	public void setForcast(String forcast) {
		this.forcast = forcast;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	public String getRainChance() {
		return rainChance;
	}
	public void setRainChance(String rainChance) {
		this.rainChance = rainChance;
	}
	public String getRainQuantity() {
		return rainQuantity;
	}
	public void setRainQuantity(String rainQuantity) {
		this.rainQuantity = rainQuantity;
	}
	public String getStromChance() {
		return stromChance;
	}
	public void setStromChance(String stromChance) {
		this.stromChance = stromChance;
	}
	public String getCloudness() {
		return cloudness;
	}
	public void setCloudness(String cloudness) {
		this.cloudness = cloudness;
	}
	public String getUvIndex() {
		return uvIndex;
	}
	public void setUvIndex(String uvIndex) {
		this.uvIndex = uvIndex;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getSunset() {
		return sunset;
	}
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
	public String getSunrise() {
		return sunrise;
	}
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	public String getDaySpan() {
		return daySpan;
	}
	public void setDaySpan(String daySpan) {
		this.daySpan = daySpan;
	}
	private String uvIndex;
	private String pressure;
	private String humidity;
	private String visibility;
	private String sunset;
	private String sunrise;
	private String daySpan;
	public Forcast (long time, String forcast, String wind, String rainChance){
		this.time=time;
		this.forcast=forcast;
		this.wind=wind;
		this.rainChance=rainChance;
	}
	
	
}
