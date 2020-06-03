package com.ghulam.dao;

import com.ghulam.model.ActivityLog;
import com.ghulam.model.User;
import com.ghulam.model.UserAuthToken;

public class ActivityLogDAO extends JpaDAO<ActivityLog> {

	public ActivityLogDAO() {
		super(ActivityLog.class, User.class, UserAuthToken.class);
	}

	@Override
	protected void updateCount(int increamentValue) {

	}

}
