package com.ghulam.controller.user;

import com.ghulam.controller.ServletController;
import com.ghulam.dao.ActivityLogDAO;
import com.ghulam.dao.IssueBookAndRequestDAO;
import com.ghulam.model.ActivityLog;
import com.ghulam.model.Book;
import com.ghulam.model.IssueBookAndRequest;
import com.ghulam.model.User;
import com.ghulam.utility.css.AlertType;
import com.ghulam.utility.css.Icon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@WebServlet({"/UserDashboard", "/my-cart","/requestBook"})
public class UserDashboardServletController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static List<ActivityLog> activityLogs;
    private static List<Book> books;
    private static List<IssueBookAndRequest> bookRequests;


    static {
        books = new ArrayList<>();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // set activity log to the attribute
            request.setAttribute("activityLogs", getActivityLogs());
            // redirect to user request page
            switch (request.getServletPath()) {
                case "/UserDashboard":
                default:
                    homePage(request, response,books);
                    break;
                case "/my-cart":
                    mycart(request,response);
                    break;
                case "/requestBook":
                    requestBook(request,response);
                    break;
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void requestBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IssueBookAndRequestDAO issueBookAndRequestDAO = new IssueBookAndRequestDAO();
        String ids[] = request.getParameter("id").split(",");
        for (String id:ids){
            bookRequests.removeIf(bookRequest ->bookRequest.getId() == Integer.parseInt(id));
        }
        issueBookAndRequestDAO.saveAll(bookRequests);
        ServletController.forwardRequest(request, response, "/view/user/my-cart.jsp", "Request Send.",
                AlertType.SUCCESS, Icon.CHECK);
    }

    private void mycart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids[] = request.getParameter("id").split(",");
        getIssueBookAndRequests(request, ids);
        request.setAttribute("bookRequest", bookRequests);
        request.setAttribute("bookRequestIds", request.getParameter("id"));
        requestForward(request, response, "view/user/my-cart.jsp");
    }

    private void getIssueBookAndRequests(HttpServletRequest request, String[] ids) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedUser");
        bookRequests = new ArrayList<>();
        for (String id : ids){
            Book book = new Book();
            book.setId(Integer.parseInt(id));
            bookRequests.add(new IssueBookAndRequest(user,book, IssueBookAndRequestDAO.Type.BOOK_REQUEST.toString(), LocalDateTime.now(),LocalDateTime.now()));
        }
    }


    private void homePage(HttpServletRequest request, HttpServletResponse response,List<Book> books) throws ServletException, IOException {
        setAttribute(request);
        requestForward(request, response, "view/user/home.jsp");
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
        request.setAttribute("books", books);
    }

    private List<ActivityLog> getActivityLogs() {
        if (activityLogs == null) {
            activityLogs = new ActivityLogDAO().getAll();
        }
        return activityLogs;
    }
}
