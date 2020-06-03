package com.ghulam.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import com.ghulam.customexception.HashGenerationException;
import com.ghulam.dao.UserAuthDAO;
import com.ghulam.model.User;
import com.ghulam.model.UserAuthToken;
import com.ghulam.utility.HashGenerator;

/*
 * It is check user is login or not
 */
@WebFilter(urlPatterns = { "/index.jsp", "/AdminDashboard", "/home" })
public class Authenticate implements Filter {
	private static String[] loginRequiredURLs = { "/AdminDashboard", "/home" };
	private static UserAuthDAO userAuthDAO = new UserAuthDAO();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);

		boolean loggedIn = (session != null) && (session.getAttribute("loggedUser") != null);

		// check user object is present in session object
		if (loggedIn) {
			moveForward(request, response, chain, httpRequest, httpResponse, loggedIn);
			return;
		}

		Cookie[] cookies = httpRequest.getCookies();
		if (!loggedIn && (cookies != null)) {
			// process auto login for remember me feature
			String selector = "";
			String rawValidator = "";

			for (Cookie aCookie : cookies) {
				if (aCookie.getName().equals("selector")) {
					selector = aCookie.getValue();
				} else if (aCookie.getName().equals("validator")) {
					rawValidator = aCookie.getValue();
				}
			}

			try {
				if (!"".equals(selector) && !"".equals(rawValidator)) {
					UserAuthToken token = userAuthDAO.findBySelector(selector);
					if (token != null) {
						String hashedValidatorDatabase = token.getValidator();
						String hashedValidatorCookie = HashGenerator.generateSHA256(rawValidator);
						if (hashedValidatorCookie.equals(hashedValidatorDatabase)) {
							session = httpRequest.getSession();
							session.setAttribute("loggedUser", token.getUser());
							loggedIn = true;
							updateTokenInDatabase(httpResponse, token);
						}
					}
				}
			} catch (HashGenerationException e) {
				e.printStackTrace();
			}
		}

		moveForward(request, response, chain, httpRequest, httpResponse, loggedIn);
	}

	private void moveForward(ServletRequest request, ServletResponse response, FilterChain chain,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse, boolean loggedIn)
			throws IOException, ServletException {
		if (!loggedIn && isLoginRequired(httpRequest)) {
			// forward to the login page
			httpResponse.sendRedirect("index.jsp");
		} else if (loggedIn && !isLoginRequired(httpRequest)) {
			// check which user is logged in
			HttpSession session = httpRequest.getSession();
			User user = (User) session.getAttribute("loggedUser");
			String url = user.getType().equalsIgnoreCase("admin") ? "AdminDashboard" : "UserDashboard";
			httpResponse.sendRedirect(url);
		} else {
			// user is logged in, move forward
			chain.doFilter(request, response);
		}
	}

	// update new token in database
	private void updateTokenInDatabase(HttpServletResponse httpResponse, UserAuthToken token)
			throws HashGenerationException {
		String newSelector = RandomStringUtils.randomAlphanumeric(12);
		String newRawValidator = RandomStringUtils.randomAlphanumeric(64);
		String newHashedValidator = HashGenerator.generateSHA256(newRawValidator);

		token.setSelector(newSelector);
		token.setValidator(newHashedValidator);
		userAuthDAO.update(token);

		updateCookie(httpResponse, newSelector, newRawValidator);
	}

	// update cookie
	private void updateCookie(HttpServletResponse httpResponse, String newSelector, String newRawValidator) {
		Cookie cookieSelector = new Cookie("selector", newSelector);
		cookieSelector.setMaxAge(24 * 60 * 60 * 365);

		Cookie cookieValidator = new Cookie("validator", newRawValidator);
		cookieValidator.setMaxAge(24 * 60 * 60 * 365);

		httpResponse.addCookie(cookieSelector);
		httpResponse.addCookie(cookieValidator);
	}

	private boolean isLoginRequired(HttpServletRequest httpRequest) {
		String requestURL = httpRequest.getServletPath();
		return Arrays.stream(loginRequiredURLs).anyMatch(t -> t.equals(requestURL));
	}
}