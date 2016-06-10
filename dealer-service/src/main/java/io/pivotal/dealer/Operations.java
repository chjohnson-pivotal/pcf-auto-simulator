package io.pivotal.dealer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Operations 
{
	@JsonProperty("Monday")
	private String monday;
	
	@JsonProperty("Tuesday")
	private String tuesday;
	
	@JsonProperty("Wednesday")
	private String wednesday;
	
	@JsonProperty("Thursday")
	private String thursday;
	
	@JsonProperty("Friday")
	private String friday;
	
	@JsonProperty("Saturday")
	private String saturday;
	
	@JsonProperty("Sunday")
	private String sunday;
	
	public String getMonday() {
		return monday;
	}
	public void setMonday(String monday) {
		this.monday = monday;
	}
	public String getTuesday() {
		return tuesday;
	}
	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}
	public String getWednesday() {
		return wednesday;
	}
	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}
	public String getThursday() {
		return thursday;
	}
	public void setThursday(String thursday) {
		this.thursday = thursday;
	}
	public String getFriday() {
		return friday;
	}
	public void setFriday(String friday) {
		this.friday = friday;
	}
	public String getSaturday() {
		return saturday;
	}
	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}
	public String getSunday() {
		return sunday;
	}
	public void setSunday(String sunday) {
		this.sunday = sunday;
	}

	
}
