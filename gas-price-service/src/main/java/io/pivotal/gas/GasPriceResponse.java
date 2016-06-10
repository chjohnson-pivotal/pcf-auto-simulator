package io.pivotal.gas;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GasPriceResponse {
	List<Station> stations;
	Status status;
	
	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}

class Status {
	private String error;
	private Integer code;
	private String description;
	private String message;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

class Station {
	private String id; //id
	private String lat;  //lat
	private String lng;  //lng

	@JsonProperty("station")
	private String name;  // station
	private String address;  //address
	private String city;  //city
	private String region;  //region
	private String zip;  //zip
	private String distance;  //distance

	@JsonProperty("reg_price")
	private String regPrice;  //reg_price

	@JsonProperty("mid_price")
	private String midPrice;  //mid_price

	@JsonProperty("pre_price")
	private String prePrice;  //pre_price

	@JsonProperty("diesel_price")
	private String dieselPrice;  //diesel_price

	@JsonProperty("reg_date")
	private String regDate;  //reg_date

	@JsonProperty("mid_date")
	private String midDate;  //mid_date

	@JsonProperty("pre_date")
	private String preDate;  //pre_date

	@JsonProperty("diesel_date")
	private String dieselDate;  //diesel_date
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
