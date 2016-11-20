package com.sunfeed.feed;

import java.time.Duration;
import java.time.temporal.Temporal;

public class AmountOfTimeToString {
	
	public static String between(Temporal start, Temporal end) {
		Duration duration = Duration.between(start, end);
		StringBuilder amountOfTimeString = new StringBuilder();
		if (duration.toDays() > 0) {
			amountOfTimeString.append(duration.toDays() + "d");
			return amountOfTimeString.toString();
		}
		
		if (duration.toHours() > 0) {
			amountOfTimeString.append(duration.toHours() + "h");
			return amountOfTimeString.toString();
		}
		
		if (duration.toMinutes() > 0) {
			amountOfTimeString.append(duration.toMinutes() + "m");
			return amountOfTimeString.toString();
		}
		
		amountOfTimeString.append(duration.toMinutes() + "s");
		return amountOfTimeString.toString();
	}
}
