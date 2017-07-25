package io.pivotal.gas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanAccessor;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.gas.GasPriceClient;

@RestController
public class GasPriceController {
	
	@Autowired
	private GasPriceClient gasPriceClient;
	
	@Value("${gas.service.apiKey}")
	private String apiKey;

	@Autowired
	private Tracer tracer;
	
	private final SpanAccessor spanAccessor;
	
	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	GasPriceController(SpanAccessor spanAccessor) {
		this.spanAccessor = spanAccessor;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/")
	public String ping()
	{
		spanInfo();
		return "The gas price server is up and running";
	}


	@RequestMapping(method=RequestMethod.GET, value="/gasPrices", params = {"lat", "lng", "distance"})
	public GasPriceResponse gasPrices(@RequestParam("lat") String lat, @RequestParam("lng") String lng, @RequestParam("distance") String distance)
	{
		GasPriceResponse gasPriceResp = null;
		
		Span newSpan = this.tracer.createSpan("getGasStations");
		try {
			this.tracer.addTag("lat", lat);
			this.tracer.addTag("lng", lng);
			
			gasPriceResp = gasPriceClient.getGasPrices(lat, lng, distance, apiKey);

			newSpan.logEvent("gasStationsRetrieved");
		} finally {
			this.tracer.close(newSpan);
		}		

		return gasPriceResp;
	}

	private void spanInfo() {
		Span span = this.spanAccessor.getCurrentSpan();
		this.log.info(String.format("An example span and it's trace: spanId: span %s belongs to trace %s, ", span.getTraceId(), span.getSpanId()));
	}	
}
