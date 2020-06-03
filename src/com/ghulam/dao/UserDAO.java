package com.ghulam.dao;

import java.util.HashMap;
import java.util.List;

import com.ghulam.model.ActivityLog;
import com.ghulam.model.User;
import com.ghulam.model.UserAuthToken;

public class UserDAO extends JpaDAO<User> {
	private static int totalUser = -1;

	// Initialize the totalUser variable
	static {
		totalUser = new UserDAO().getAll().size();
	}

	public UserDAO() {
		super(User.class, UserAuthToken.class, ActivityLog.class);
	}

	public static int getTotalUser() {
		return totalUser;
	}

	@Override
	protected void updateCount(int increamentValue) {
		totalUser += increamentValue;

	}

	public User getUser(String email, String password) {
		HashMap<String, String> parameters = new HashMap<>();
		parameters.put("email", email);
		parameters.put("password", password);
		List<User> list = findWithQuery("FROM User u WHERE u.email = :email AND u.password = :password", parameters);
		return list.get(0);
	}

}