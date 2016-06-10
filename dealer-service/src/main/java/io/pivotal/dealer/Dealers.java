package io.pivotal.dealer;

import java.util.List;

public class Dealers {
	List<Dealer> dealers;

	public List<Dealer> getDealers() {
		return dealers;
	}

	public void setDealers(List<Dealer> dealers) {
		this.dealers = dealers;
	}
}

class Dealer {
	private String dealerId;
	private String name;
	private String niceName;
	private Double distance;
	private Boolean active;
	private Address address;
	private String type;
	private Operations operations;
	
	public String getDealerId() {
		return dealerId;
	}
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNiceName() {
		return niceName;
	}
	public void setNiceName(String niceName) {
		this.niceName = niceName;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Operations getOperations() {
		return operations;
	}
	public void setOperations(Operations operations) {
		this.operations = operations;
	}


}
