package io.pivotal.gas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.gas.GasPriceClient;

@RestController
public class GasPriceController {
	
	@Autowired
	private GasPriceClient gasPriceClient;
	
	@Value("${gas.service.apiKey}")
	private String apiKey;

	private final SpanAccessor spanAccessor;
	
	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	GasPriceController(SpanAccessor spanAccessor) {
		this.spanAccessor = spanAccessor;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/")
	public String ping()
	{
		return "The gas price server is up and running";
	}


	@RequestMapping(method=RequestMethod.GET, value="/gasPrices", params = {"lat", "lng", "distance"})
	public GasPriceResponse gasPrices(@RequestParam("lat") String lat, @RequestParam("lng") String lng, @RequestParam("distance") String distance)
	{
		GasPriceResponse gasPriceResp = gasPriceClient.getGasPrices(lat, lng, distance, apiKey);
		debug();
		return gasPriceResp;
	}

	private void debug() {
		Span span = this.spanAccessor.getCurrentSpan();
		this.log.info(String.format("An example trace: traceId: %s, spanId: %s", span.getTraceId(), span.getSpanId()));
	}	
}
