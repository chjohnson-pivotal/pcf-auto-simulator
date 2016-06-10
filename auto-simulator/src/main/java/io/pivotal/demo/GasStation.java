package io.pivotal.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GasStation {
	private String id;	
	private String lat;
	private String lng;
	private String station;
	private String address;
	private String city;
	private String region;
	private String zip;
	private String distance;

	@JsonProperty("reg_price")
	private String regPrice;

	@JsonProperty("mid_price")
	private String midPrice;

	@JsonProperty("pre_price")
	private String prePrice;
	
	@JsonProperty("diesel_price")	
	private String dieselPrice;

	@JsonProperty("reg_date")
	private String regDate;

	@JsonProperty("mid_date")
	private String midDate;
	
	@JsonProperty("pre_date")
	private String preDate;
	
	@JsonProperty("diesel_date")	
	private String dieselDate;

	public GasStation () {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getRegPrice() {
		return regPrice;
	}

	public void setRegPrice(String regPrice) {
		this.regPrice = regPrice;
	}

	public String getMidPrice() {
		return midPrice;
	}

	public void setMidPrice(String midPrice) {
		this.midPrice = midPrice;
	}

	public String getPrePrice() {
		return prePrice;
	}

	public void setPrePrice(String prePrice) {
		this.prePrice = prePrice;
	}

	public String getDieselPrice() {
		return dieselPrice;
	}

	public void setDieselPrice(String dieselPrice) {
		this.dieselPrice = dieselPrice;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getMidDate() {
		return midDate;
	}

	public void setMidDate(String midDate) {
		this.midDate = midDate;
	}

	public String getPreDate() {
		return preDate;
	}

	public void setPreDate(String preDate) {
		this.preDate = preDate;
	}

	public String getDieselDate() {
		return dieselDate;
	}

	public void setDieselDate(String dieselDate) {
		this.dieselDate = dieselDate;
	}


}
