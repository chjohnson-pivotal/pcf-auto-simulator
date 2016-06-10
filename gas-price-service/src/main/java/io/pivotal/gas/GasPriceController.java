package io.pivotal.gas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.pivotal.gas.GasPriceClient;

@Controller
@RequestMapping("/")
public class GasPriceController {
	
	@Autowired
	private GasPriceClient gasPriceClient;
	
	@Value("${gas.service.apiKey}")
	private String apiKey;
	
	@RequestMapping(method=RequestMethod.GET, value="/")
	@ResponseBody
	public String ping()
	{
		return "The repair server is up and running";
	}


	@RequestMapping(method=RequestMethod.GET, value="/gasPrices", params = {"lat", "lng", "distance"})
	@ResponseBody
	public GasPriceResponse gasPrices(@RequestParam("lat") String lat, @RequestParam("lng") String lng, @RequestParam("distance") String distance)
	{
		GasPriceResponse gasPriceResp = gasPriceClient.getGasPrices(lat, lng, distance, apiKey);
		return gasPriceResp;
	}

}
