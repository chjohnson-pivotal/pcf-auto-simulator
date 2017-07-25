package io.pivotal.places;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.pivotal.places.GooglePlacesClient;

@Controller
@RequestMapping("/")
public class PlacesController {
	
	@Autowired
	GooglePlacesClient googlePlacesClient;
	
	@Value("${google.service.apiKey}")
	private String apiKey;
	
	private Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(method=RequestMethod.GET, value="/")
	@ResponseBody
	public String ping()
	{
		return "The places service is up and running";
	}


	@RequestMapping(method=RequestMethod.GET, value="/places", params = {"lat", "lng", "type"})
	@ResponseBody
	public PlacesResponse getPlaces(@RequestParam("lat") String lat, @RequestParam("lng") String lng, @RequestParam("type") String type)
	{
		PlacesResponse gasPriceResp = googlePlacesClient.getPlaces(lat + "," + lng, "distance", type, apiKey);
		return gasPriceResp;
	}

	@RequestMapping(method=RequestMethod.GET, value="/reverseGeocode", params = {"lat", "lng"})
	@ResponseBody
	public PostalCode reverseGeocode(@RequestParam("lat") String lat, @RequestParam("lng") String lng)
	{
    	log.info("reverse geocoding the lat and lng passed");

		ReverseGeocodeResponse resp = googlePlacesClient.reverseGeocode(lat + "," + lng, apiKey);
		PostalCode p = new PostalCode();
		for (AddressResult address : resp.getResults()) {
			for (AddressComponents component : address.getAddressComponents()) {
				for (String type : component.getTypes()) {
					if (type.equals("postal_code")) {
						p.setPostalCode(component.getShortName());
					}
				}
			}
		}
		return p;
	}
	
}
