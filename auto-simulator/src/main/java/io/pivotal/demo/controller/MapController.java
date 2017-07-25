package io.pivotal.demo.controller;

import io.pivotal.demo.Dealerships;
import io.pivotal.demo.Geo;
import io.pivotal.demo.GasStations;
import io.pivotal.demo.Schedule;
import io.pivotal.demo.VehicleInfo;
import io.pivotal.demo.service.DealershipsClient;
import io.pivotal.demo.service.GeocodeClient;
import io.pivotal.demo.service.RepairClient;
import io.pivotal.demo.service.GasStationClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
@RequestMapping("/map")
public class MapController {
    
	private static final String queueName = "vehicle-data-queue";
	
    @Autowired
    private RepairClient repairClient;

    @Autowired
    private GasStationClient gasStationClient;

    @Autowired
    private GeocodeClient geocodeClient;
    
    @Autowired
    private DealershipsClient dealershipsClient;
    
    @Autowired 
    private RabbitTemplate rabbitTemplate;
    
	private Log log = LogFactory.getLog(getClass());
    
    @RequestMapping("/dealershipOpenings")
//    @HystrixCommand(fallbackMethod = "defaultSchedule")
	public @ResponseBody List<Schedule> dealershipOpenings(@RequestParam(required=true) Long dealerId) {

		return repairClient.openings(dealerId);
	
	} 
    
	public @ResponseBody List<Schedule> defaultSchedule(@RequestParam(required=true) Long dealerId) {
		List<Schedule> list = new ArrayList<Schedule>(1);
		Schedule schedule = new Schedule();
		schedule.setDate("No times available.  Please check back later.");
		list.add(schedule);
		return list;
	} 
    
	// URL Example: /nearestGasStationsWithPrices?lat=42.221786&lng=-83.414139&distance=5
    @RequestMapping(value = "/nearestGasStationsWithPrices")
	public @ResponseBody GasStations nearestGasStationsWithPrices(@RequestParam(required=true) String lat, 
																  @RequestParam(required=true) String lng,
																  @RequestParam(required=true) Integer distance) {
    	log.info("calling gas station service");
		return gasStationClient.nearestGasStationsWithPrices(lat, lng, distance);
		
	}    

    @RequestMapping("/nearestDealerships")
	public @ResponseBody Dealerships nearestDealerships(@RequestParam(required=true) String lat, 
															 @RequestParam(required=true) String lng,
															 @RequestParam(required=true) String brand) {

    	log.info("calling geocode service");
    	Geo geo = geocodeClient.geocode(lat, lng);
    	
    	log.info("calling dealer service");
		return dealershipsClient.nearestDealerships(brand, geo.getPostalCode());
	}    
    
    @RequestMapping("/vehicleInfo")
    public @ResponseBody VehicleInfo vehicleInfo() throws Exception {

    	log.info("getting next vehicle data record");
    	
    	String json = new String((byte[])rabbitTemplate.receiveAndConvert(queueName));
		json = java.net.URLDecoder.decode(json, "UTF-8");
    	ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		return mapper.readValue(json, VehicleInfo.class);        
    }
    
    @RequestMapping("/killApp")
    public void killApp()
    {
    	System.exit(1);
    }
    
    @RequestMapping("/ipAddress")
    public @ResponseBody String serverIpAddress()
    {
    	try 
    	{
    		return "{ \"ipAddress\" : \"" + InetAddress.getLocalHost().getHostAddress() + "\"}";
    	} 
    	catch (UnknownHostException e) 
    	{
    		return "{ \"ipAddress\" : \"" + "Unknown/Error" + "\"}";
		}
    }
    	
    @RequestMapping("/haveRabbitConnection")
    public @ResponseBody String haveRabbitMqConnection()
    {
    	Boolean haveRabbit = false;
    	if (rabbitTemplate != null)
    	{
    		
    		try
    		{
    			ConnectionFactory cn = rabbitTemplate.getConnectionFactory();
    			haveRabbit = true;
    		}
    		catch(AmqpException ae)
    		{
    			ae.printStackTrace();
    			haveRabbit = false;
    		}
    	}
    	return "{ \"haveRabbitMqConnection\" : \"" + haveRabbit + "\"}";    	
    }

}
