package com.ghulam.dao;

import java.util.HashMap;
import java.util.List;

import com.ghulam.model.ActivityLog;
import com.ghulam.model.User;
import com.ghulam.model.UserAuthToken;

public class UserAuthDAO extends JpaDAO<UserAuthToken> {

	public UserAuthDAO() {
		super(UserAuthToken.class, User.class, ActivityLog.class);
	}

	/*
	 * The findBySelector() method will be used to find a token in the
	 * authentication table, given a selector read from the cookie.
	 */
	public UserAuthToken findBySelector(String selector) {
		HashMap<String, String> parameters = new HashMap<>();
		parameters.put("selector", selector);
		List<UserAuthToken> list = findWithQuery("FROM UserAuthToken u WHERE u.selector = :selector", parameters);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	protected void updateCount(int increamentValue) {
	}

}
