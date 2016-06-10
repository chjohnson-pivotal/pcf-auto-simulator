package io.pivotal.demo.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.pivotal.demo.Dealerships;

@FeignClient("dealer-service")
public interface DealershipsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/dealers", params = {"brand", "zipcode"}, consumes = "application/json")
    public Dealerships nearestDealerships(@RequestParam("brand") String brand, @RequestParam("zipcode") String zipcode);

}