package io.pivotal.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleInfo {
	private Double odometer;
	
	@JsonProperty("fuel_level")
	private Double fuelLevel;
	private Double latitude;
	private Double longitude;
	private Double time;
	
	public Double getOdometer() {
		return odometer;
	}
	public void setOdometer(Double odometer) {
		this.odometer = odometer;
	}
	public Double getFuelLevel() {
		return fuelLevel;
	}
	public void setFuelLevel(Double fuelLevel) {
		this.fuelLevel = fuelLevel;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getTime() {
		return time;
	}
	public void setTime(Double time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "VehicleInfo [odometer=" + odometer + ", fuelLevel=" + fuelLevel
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", time=" + time +"]";
	}
	
}
