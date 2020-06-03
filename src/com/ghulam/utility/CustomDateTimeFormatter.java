package com.ghulam.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTimeFormatter {
	public static String format(LocalDateTime localDateTime) {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd MMM yyyy  hh:mm a");
		return localDateTime.format(pattern);
	}
}
