package com.ghulam.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ghulam.dao.UserDAO;
import com.ghulam.model.ActivityLog;
import com.ghulam.model.User;

@WebFilter(urlPatterns = { "/*" })
public class SaveActivityLog implements Filter {
	private static boolean isActivitySave = false;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		if (!isActivitySave && (session != null) && (session.getAttribute("loggedUser") != null)) {
			// get user from session
			User user = (User) session.getAttribute("loggedUser");
			// add activity log into user object
			user.addActivityLogs(new ActivityLog(LocalDateTime.now()));
			// update user database
			new UserDAO().update(user);
			isActivitySave = true;
		}
		chain.doFilter(request, response);
	}
}
