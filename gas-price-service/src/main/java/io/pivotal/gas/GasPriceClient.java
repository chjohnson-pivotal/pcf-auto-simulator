package io.pivotal.gas;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.pivotal.gas.GasPriceResponse;

@FeignClient(name="gas-feed", url="http://api.mygasfeed.com")
public interface GasPriceClient {

	// Example:  http://api.mygasfeed.com/stations/radius/40.1578400/-83.0751870/10/reg/distance/u3t7n5vzxl.json
    @RequestMapping(value = "/stations/radius/{lat}/{lng}/{distance}/reg/distance/{apiKey}.json", method = RequestMethod.GET , consumes = "application/json")
    public GasPriceResponse getGasPrices(@RequestParam("lat") String lat, 
    						   			 @RequestParam("lng") String lng,
    						   			 @RequestParam("distance") String distance,
    						   			 @RequestParam("apiKey") String apiKey);
}