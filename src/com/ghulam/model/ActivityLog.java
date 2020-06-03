package com.ghulam.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activity_log")
public class ActivityLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String localDateTime;

	public ActivityLog() {
	}

	public ActivityLog(LocalDateTime localDateTime) {
		this.localDateTime = getDateAndTimeInString(localDateTime);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = getDateAndTimeInString(localDateTime);
	}

	private String getDateAndTimeInString(LocalDateTime localDateTime) {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd MMM yyyy  hh:mm a");
		return localDateTime.format(pattern);
	}
}
