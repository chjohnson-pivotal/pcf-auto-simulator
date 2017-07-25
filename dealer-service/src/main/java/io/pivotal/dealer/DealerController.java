package io.pivotal.dealer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.pivotal.dealer.DealerClient;

@Controller
@RequestMapping("/")
public class DealerController {
	
	@Autowired
	DealerClient dealerClient;
	
	@Value("${radius}")
    Integer radius;	

	@Value("${make}")
	String make;

	@Value("${state}")
	String state;

	@Value("${pageNum}")
	Integer pageNum;

	@Value("${pageSize}")
	Integer pageSize;

	@Value("${sortby}")
	String sortby;

	@Value("${view}")
	String view;

	@Value("${dealer.service.apiKey}")
	String key;
	
	private Log log = LogFactory.getLog(getClass());

	@RequestMapping(method=RequestMethod.GET, value="/")
	@ResponseBody
	public String ping()
	{
		return "The repair server is up and running";
	}


	@RequestMapping(method=RequestMethod.GET, value="/dealers", params = {"brand", "zipcode"})
	@ResponseBody
	public Dealers dealers(@RequestParam("brand") String brand, @RequestParam("zipcode") String zipcode)
	{
		log.info("finding nearest dealers: " + zipcode);
		Dealers dealers = dealerClient.nearestDealerships(zipcode, radius, brand, state, pageNum, pageSize, sortby, view, key);
		return dealers;
	}

}
