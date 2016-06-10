package io.pivotal.demo;

import java.util.List;

public class GasStations {
	private Status status;
	private List<GasStation> gasStations;
	
	public GasStations() { }

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<GasStation> getStations() {
		return gasStations;
	}

	public void setStations(List<GasStation> gasStations) {
		this.gasStations = gasStations;
	}
	
	
}
