package io.pivotal.dealer;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.pivotal.dealer.Dealers;

@FeignClient(url="http://api.edmunds.com")
public interface DealerClient {

	// Example:  http://api.edmunds.com/api/dealer/v2/dealers?zipcode=43065&radius=100&make=Ford&state=new&pageNum=1&pageSize=10&sortby=distance&view=basic&api_key=wyanxf4hcvq7reh379nb5z67
    @RequestMapping(value = "/api/dealer/v2/dealers", method = RequestMethod.GET, params = {"zipcode", "radius", "make", "state", "pageNum", "pageSize", "sortby", "view", "api_key" } , consumes = "application/json")
    public Dealers nearestDealerships(@RequestParam("zipcode") String zipcode, 
    									   @RequestParam("radius") Integer radius,
    									   @RequestParam("make") String make,
    									   @RequestParam("state") String state,
    									   @RequestParam("pageNum") Integer pageNum,
    									   @RequestParam("pageSize") Integer pageSize,
    									   @RequestParam("sortby") String sortby,
    									   @RequestParam("view") String view,
    									   @RequestParam("api_key") String api_key);

}