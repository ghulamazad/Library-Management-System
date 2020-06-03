package com.ghulam.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ghulam.controller.ServletController;
import com.ghulam.dao.IssueBookAndRequestDAO;
import com.ghulam.dao.IssueBookAndRequestDAO.Type;
import com.ghulam.model.IssueBookAndRequest;
import com.ghulam.utility.css.AlertType;
import com.ghulam.utility.css.Icon;

@WebServlet({ "/accept", "/reject" })
public class BookIssueRequestServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IssueBookAndRequestDAO issueBookAndRequestDAO = new IssueBookAndRequestDAO();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			switch (request.getServletPath()) {
			case "/accept":
				acceptBookRequest(request, response);
				break;
			case "/reject":
				rejectBookRequest(request, response);
				break;
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void acceptBookRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		IssueBookAndRequest issueBookAndRequest = issueBookAndRequestDAO.get(id);
		issueBookAndRequest.setBookType(Type.ISSUE_BOOK.toString());
		issueBookAndRequestDAO.update(issueBookAndRequest);
		IssueBookAndRequestDAO.updateIssueBookAndRequest();
		ServletController.forwardRequest(request, response, "/AdminDashboard", "Book Request Successfully Accepted.",
				AlertType.SUCCESS, Icon.CHECK);
	}

	private void rejectBookRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		issueBookAndRequestDAO.delete(id);
		IssueBookAndRequestDAO.decreaseBookRequest();
		ServletController.forwardRequest(request, response, "/AdminDashboard", "Book Request Successfully Deleted.",
				AlertType.SUCCESS, Icon.CHECK);
	}

}
