package io.pivotal.demo.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.pivotal.demo.GasStations;

@FeignClient("gas-price-service")
public interface GasStationClient {
	
    @RequestMapping(method = RequestMethod.GET, value = "/gasPrices", params = {"lat", "lng", "distance"}, consumes = "application/json")
    public GasStations nearestGasStationsWithPrices(@RequestParam("lat") String lat, 
    												   @RequestParam("lng") String lng,
    												   @RequestParam("distance") Integer distance);

}