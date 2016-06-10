package io.pivotal.places;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReverseGeocodeResponse {
	private List<AddressResult> results;
	private String status;
	
	public List<AddressResult> getResults() {
		return results;
	}
	public void setResults(List<AddressResult> results) {
		this.results = results;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

class AddressResult {
	@JsonProperty("address_components")
	private List<AddressComponents> addressComponents;
	
	@JsonProperty("formatted_address")
	private String formattedAddress;
	
	public List<AddressComponents> getAddressComponents() {
		return addressComponents;
	}
	public void setAddressComponents(List<AddressComponents> addressComponents) {
		this.addressComponents = addressComponents;
	}
	public String getFormattedAddress() {
		return formattedAddress;
	}
	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}
}

class AddressComponents {

	@JsonProperty("long_name")
	private String longName;

	@JsonProperty("short_name")
	private String shortName;
	
	private String types[];
	
	public String getLongName() {
		return longName;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
}