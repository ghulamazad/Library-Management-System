package com.ghulam.model;

import com.ghulam.controller.ServletController;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String email;
	private String password;
	private String type;
	private Blob image;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserAuthToken> userTokens;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "activity_log")
	private Set<ActivityLog> activityLogs;

	@Transient
	private String base64Image;

	public User() {
	}

	public User(String username, String email, String password, String type, Blob image) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.type = type;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Set<UserAuthToken> getUserTokens() {
		return userTokens;
	}

	public void setUserTokens(Set<UserAuthToken> userTokens) {
		this.userTokens = userTokens;
	}

	public Set<ActivityLog> getActivityLogs() {
		return activityLogs;
	}

	public void setActivityLogs(Set<ActivityLog> activityLogs) {
		this.activityLogs = activityLogs;
	}

	public void addActivityLogs(ActivityLog activityLog) {
		if (activityLogs == null) {
			activityLogs = new HashSet<>();
		}
		activityLogs.add(activityLog);
	}

	public String getBase64Image() {
		return ServletController.getBase64Image(image, base64Image);
	}
}
