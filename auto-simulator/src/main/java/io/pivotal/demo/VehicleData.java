package io.pivotal.demo;

class VehicleData 
{
	private String name;
	private Double value;
	private Double timestamp;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Double getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Double timestamp) {
		this.timestamp = timestamp;
	}
	
	// helper methods 
	public boolean isFuelLevel()
	{
		return "fuel_level".equals(name);
	}
	public boolean isOdometer()
	{
		return "odometer".equals(name);
	}
	public boolean isLatitude()
	{
		return "latitude".equals(name);
	}
	public boolean isLongitude()
	{
		return "longitude".equals(name);
	}
	
	@Override
	public String toString() {
		return "VehicleData [name=" + name + ", value=" + value
				+ ", timestamp=" + timestamp + "]";
	}
}
