package io.pivotal.demo;

import java.util.Calendar;

public class Schedule 
{
	private String   dealerId;
	private String   date;
	private String   startTime;
	private String   duration;
	private Integer  durationInMinutes;
	
	public Schedule(String dealerId, String date, String startTime, String duration, Integer durationInMinutes)
	{
		super();
		
		this.dealerId = dealerId;
		this.date = date;
		this.startTime = startTime;
		this.duration = duration;
		this.durationInMinutes = durationInMinutes;
	}
	
	public String getDealerId() {
		return dealerId;
	}
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Integer getDurationInMinutes() {
		return durationInMinutes;
	}
	public void setDurationInMinutes(Integer durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}
	
	@Override
	public String toString() {
		return "Schedule [dealerId=" + dealerId + ", date=" + date
				+ ", startTime=" + startTime + ", duration=" + duration
				+ ", durationInMinutes=" + durationInMinutes + "]";
	}
	
	
}
