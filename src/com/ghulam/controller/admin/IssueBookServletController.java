package com.ghulam.controller.admin;

import com.ghulam.controller.ServletController;
import com.ghulam.dao.IssueBookAndRequestDAO;
import com.ghulam.utility.css.AlertType;
import com.ghulam.utility.css.Icon;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/deleteIssueBooks" })
public class IssueBookServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IssueBookAndRequestDAO issueBookAndRequestDAO = new IssueBookAndRequestDAO();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			switch (request.getServletPath()) {
			case "/deleteIssueBooks":
				deleteIssueBooks(request,response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteIssueBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		issueBookAndRequestDAO.delete(id);
		IssueBookAndRequestDAO.decreaseBookRequest();
		ServletController.forwardRequest(request, response, "/AdminIssueBook", "Issue Book Successfully Deleted.",
				AlertType.SUCCESS, Icon.CHECK);
	}
}