package io.pivotal.places;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.pivotal.places.PlacesResponse;

@FeignClient(name="places-service", url="https://maps.googleapis.com")
public interface GooglePlacesClient {

	// Example:  https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.1578400,-83.0751870&rankby=distance&types=gas_station&key=AIzaSyCJNAAvyohMMJ8lWDFnQxKtumpJB-7idvE
    @RequestMapping(value = "/maps/api/place/nearbysearch/json", params = {"location", "rankby", "types", "key" }, method = RequestMethod.GET , consumes = "application/json")
    public PlacesResponse getPlaces(@RequestParam("location") String location, 
    						   		 @RequestParam("rankby") String rankby,
    						   		 @RequestParam("type") String type,
    						   		 @RequestParam("key") String apiKey);

	// Example:  https://maps.googleapis.com/maps/api/geocode/json?latlng=40.1578400,-83.0751870&key=AIzaSyCJNAAvyohMMJ8lWDFnQxKtumpJB-7idvE
    @RequestMapping(value = "/maps/api/geocode/json", params = {"latlng", "key"}, method = RequestMethod.GET , consumes = "application/json")
    public ReverseGeocodeResponse reverseGeocode(@RequestParam("latlng") String location,
    								@RequestParam("key") String apiKey);


}