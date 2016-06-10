package io.pivotal.demo;

public class Dealership {	
	private Long dealerId;
	private String name;
	private String niceName;
	private Float distance;
	private Boolean active;
	private String type;
	private Address address;
	private Operations operations;
	
	public Dealership() { }
	
	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
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

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Operations getOperations() {
		return operations;
	}

	public void setOperations(Operations operations) {
		this.operations = operations;
	}

	public class Address {
		private String street;
		private String city;
		private String stateCode;
		private String stateName;
		private String zipcode;
		private String latitude;
		private String longitude;
		
		public Address() { }
	
		public String getStreet() {
			return street;
		}
	
		public void setStreet(String street) {
			this.street = street;
		}
	
		public String getCity() {
			return city;
		}
	
		public void setCity(String city) {
			this.city = city;
		}
	
		public String getStateCode() {
			return stateCode;
		}
	
		public void setStateCode(String stateCode) {
			this.stateCode = stateCode;
		}
	
		public String getStateName() {
			return stateName;
		}
	
		public void setStateName(String stateName) {
			this.stateName = stateName;
		}
	
		public String getZipcode() {
			return zipcode;
		}
	
		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}
	
		public String getLatitude() {
			return latitude;
		}
	
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
	
		public String getLongitude() {
			return longitude;
		}
	
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		
		
	}
	
	public class Operations {
		private String monday;
		private String tuesday;
		private String wednesday;
		private String thursday;
		private String friday;
		private String saturday;
		private String sunday;
		
		public Operations() { }
	
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
}