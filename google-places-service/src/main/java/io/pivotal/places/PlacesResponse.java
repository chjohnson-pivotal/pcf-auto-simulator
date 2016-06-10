package io.pivotal.places;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlacesResponse {	
	private List<Result> results;
	private String status;
	
	public List<Result> getResults() {
		return results;
	}
	public void setResults(List<Result> results) {
		this.results = results;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

class Result {
	private Geo geometry;
	private String name;
	@JsonProperty("place_id")
	private String placeId;
	private String vicinity;
	
	public Geo getGeometry() {
		return geometry;
	}
	public void setGeometry(Geo geometry) {
		this.geometry = geometry;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	public String getVicinity() {
		return vicinity;
	}
	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}
}

class Location {
	private Float lat;
	private Float lng;
	
	public Float getLat() {
		return lat;
	}
	public void setLat(Float lat) {
		this.lat = lat;
	}
	public Float getLng() {
		return lng;
	}
	public void setLng(Float lng) {
		this.lng = lng;
	}
	
}

class Geo {
	private Location location;
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}	
}
