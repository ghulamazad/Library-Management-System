package com.ghulam.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ghulam.controller.ServletController;
import com.ghulam.dao.ActivityLogDAO;
import com.ghulam.dao.BookDAO;
import com.ghulam.dao.IssueBookAndRequestDAO;
import com.ghulam.dao.UserDAO;
import com.ghulam.model.ActivityLog;

@WebServlet({ "/AdminDashboard", "/AdminBook", "/AdminIssueBook", "/AdminUser" })
public class AdminDashboardServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static BookDAO bookDAO = new BookDAO();
	private static IssueBookAndRequestDAO issueBookAndRequestDAO = new IssueBookAndRequestDAO();
	private static UserDAO userDAO = new UserDAO();
	private static List<ActivityLog> activityLogs;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			// set activity log to the attribute
			request.setAttribute("activityLogs", getActivityLogs());

			// redirect to user request page
			switch (request.getServletPath()) {
			case "/AdminDashboard":
				homePage(request, response);
				break;
			case "/AdminBook":
				bookPage(request, response);
				break;
			case "/AdminIssueBook":
				issueBookPage(request, response);
				break;
			case "/AdminUser":
				userPage(request, response);
				break;
			default:
				homePage(request, response);
				break;
			}
		} catch (ServletException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void homePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		setAttribute(request);
		requestForward(request, response, "view/admin/home.jsp");
	}

	private void bookPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("books", bookDAO.getAll());
		requestForward(request, response, "view/admin/book.jsp");
	}

	private void issueBookPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("issueBooks", issueBookAndRequestDAO.getAllIssueBook());
		requestForward(request, response, "view/admin/issue_book.jsp");
	}

	private void userPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("users", userDAO.getAll());
		requestForward(request, response, "view/admin/user.jsp");
	}

	private void requestForward(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		if (request.getAttribute("isShowToast") != null) {
			request.getRequestDispatcher(page).forward(request, response);
		} else {
			ServletController.forwardRequest(request, response, page);
		}
	}

	// Set counter variable in request object
	private void setAttribute(HttpServletRequest request) {
		// Set Attribute in request
		request.setAttribute("totalBook", BookDAO.getTotalBook());
		request.setAttribute("totalUser", UserDAO.getTotalUser());
		request.setAttribute("totalIssueBook", IssueBookAndRequestDAO.getTotalIssueBook());
		request.setAttribute("totalBookIssueRequest", IssueBookAndRequestDAO.getTotalBookRequest());
		request.setAttribute("bookIssueRequests", issueBookAndRequestDAO.getAllBookRequest());
	}

	private List<ActivityLog> getActivityLogs() {
		if (activityLogs == null) {
			activityLogs = new ActivityLogDAO().getAll();
		}
		return activityLogs;
	}
}
