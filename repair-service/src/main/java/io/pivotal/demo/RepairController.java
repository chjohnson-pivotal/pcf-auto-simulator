package io.pivotal.demo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class RepairController {
	
	private static int START_OF_DAY = 8;
	private static int END_OF_DAY = 18; // military time
	private static int MAX_NUMBER_OF_SLOTS = 6;
	
	@RequestMapping(method=RequestMethod.GET, value="/")
	@ResponseBody
	public String ping()
	{
		return "The repair server is up and running";
	}
	
	@RequestMapping(value="/ServiceOpenings/{dealerId}")
	public @ResponseBody List<Schedule> findServiceOpenings(@PathVariable("dealerId") String dealerId)
	{
		ArrayList<Schedule> schedules = new ArrayList<Schedule>();
		
		Calendar today = Calendar.getInstance();
		
		// get the current hour
		int currentHour = today.get(Calendar.HOUR_OF_DAY);
		
		float startAt = (float) currentHour + 1;
		if (currentHour < START_OF_DAY)
		{
			startAt = START_OF_DAY;
		}
		else if (currentHour > END_OF_DAY)
		{
			// at the end of the day, so let's look at tomorrow
			startAt = START_OF_DAY;
			today.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		// is the day sunday? - if so, we're closed so add another day
		if (Calendar.SUNDAY == today.get(Calendar.DAY_OF_WEEK))
			today.add(Calendar.DAY_OF_MONTH,  1);
		
		findSlots(schedules, today, startAt, dealerId);
		if (schedules.size() != MAX_NUMBER_OF_SLOTS)
		{
			today.add(Calendar.DAY_OF_MONTH, 1);
			findSlots(schedules, today, START_OF_DAY, dealerId );
		}
		
		return schedules;
	}

	
	private void findSlots(List<Schedule> schedules, Calendar aDate, float startAt, String dealerId)
	{
		Random randomGenerator = new Random();
		
		for(float i = startAt; i < END_OF_DAY; i+=0.5f)
		{
			// limit the number of slots that are returned
			if (schedules.size() == MAX_NUMBER_OF_SLOTS)
				break;
			
			boolean isOpen = randomGenerator.nextBoolean();
			
			if (isOpen)
			{
				String startTime = convertFloatToTime(i);
								
				Schedule s = new Schedule(dealerId, convertCalendarToDateString(aDate), startTime, "30 minutes", 30);
				
				schedules.add(s);
			}
		}
	}
	
	private static String convertCalendarToDateString(Calendar date)
	{
		if (date == null)
			return null;
		
		Date theDate = date.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(theDate);
	}
	
	private static String convertFloatToTime(float input)
    {
		float originalInput = input;
		float nonMilitaryTime = input;
		if (nonMilitaryTime >= 13)
			nonMilitaryTime = nonMilitaryTime - 12.0f;
		
        String input_string = Float.toString(nonMilitaryTime);
        BigDecimal inputBD = new BigDecimal(input_string);
        String hhStr = input_string.split("\\.")[0];
        BigDecimal output = new BigDecimal(Float.toString(Integer.parseInt(hhStr)));
        output = output.add(
        		(inputBD.subtract(output).multiply(BigDecimal.valueOf(60))).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_EVEN)
        );
        
        String amPm = originalInput < 12 ? " AM" : " PM";

        return output.toString().replace(".",":") + amPm;
    }
	
}
