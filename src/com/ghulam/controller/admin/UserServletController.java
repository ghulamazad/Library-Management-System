package com.ghulam.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ghulam.controller.ServletController;
import com.ghulam.dao.UserDAO;
import com.ghulam.model.User;
import com.ghulam.utility.css.AlertType;
import com.ghulam.utility.css.Icon;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet({ "/addUser", "/modifyUser", "/deleteUser", "/updateUser" })
public class UserServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDAO userDAO = new UserDAO();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			switch (request.getServletPath()) {
			case "/addUser":
				saveUser(request, response);
				break;
			case "/modifyUser":
				modifyUser(request, response);
				break;
			case "/deleteUser":
				deleteUser(request, response);
				break;
			case "/updateUser":
				updateUser(request, response);
				break;
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void saveUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = getUser(request, false);
		user.setType("User");
		userDAO.save(user);
		ServletController.forwardRequest(request, response, "/AdminUser", "User Successfully Saved.", AlertType.SUCCESS,
				Icon.CHECK);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = getUser(request, false);
		userDAO.update(user);
		ServletController.forwardRequest(request, response, "/AdminUser", "User Successfully Updated.",
				AlertType.SUCCESS, Icon.CHECK);
	}

	private void modifyUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User user = userDAO.get(id);
		request.setAttribute("isUpdate", true);
		request.setAttribute("user", user);
		ServletController.forwardRequest(request, response, "/AdminUser");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.delete(id);
		ServletController.forwardRequest(request, response, "/AdminUser", "User Successfully Deleted.",
				AlertType.SUCCESS, Icon.CHECK);
	}

	/*
	 * form data to object
	 */
	private User getUser(HttpServletRequest request, boolean isUpdate) throws IOException, ServletException {
		Part filePart = request.getPart("img");
		User user = new User();
		if (request.getParameter("username") != null) {
			user.setUsername(request.getParameter("username"));
		}
		if (request.getParameter("email") != null) {
			user.setEmail(request.getParameter("email"));
		}
		if ((request.getParameter("password") != null) && (request.getParameter("password") == "")) {
			user.setPassword(request.getParameter("password"));
		}
		if ((filePart != null) && filePart.getContentType().contains("image/jpeg")) {
			user.setImage(ServletController.getBlob(filePart));
		}

		if (isUpdate) {
			user.setId(Integer.parseInt(request.getParameter("id")));
		}
		return user;
	}
}