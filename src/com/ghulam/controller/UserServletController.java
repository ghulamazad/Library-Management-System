package com.ghulam.controller;

import com.ghulam.customexception.HashGenerationException;
import com.ghulam.dao.UserAuthDAO;
import com.ghulam.dao.UserDAO;
import com.ghulam.model.User;
import com.ghulam.model.UserAuthToken;
import com.ghulam.utility.HashGenerator;
import com.ghulam.utility.css.AlertType;
import com.ghulam.utility.css.Icon;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet({"/login", "/register", "/updateCurrentUser", "/logout"})
public class UserServletController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final UserDAO userDAO = new UserDAO();
    private static final UserAuthDAO userAuthDAO = new UserAuthDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            switch (request.getServletPath()) {
                case "/login":
                    login(request, response);
                    break;
                case "/register":
                    register(request, response);
                    break;
                case "/updateCurrentUser":
                    updateCurrentUser(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
            }
        } catch (ServletException | IOException | SQLException | HashGenerationException e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, HashGenerationException {

        // get request paramaters
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean rememberMe = "true".equals(request.getParameter("remember_me"));

        User user = userDAO.getUser(email, password);
        if (user == null) {
            // login failed, show login form again with error messsage
            ServletController.forwardRequest(request, response, "index.jsp", "Invalid Username and Password.",
                    AlertType.DANGER, Icon.CROSS);
        } else {
            // login succeed, store customer information in the session
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);
            if (rememberMe) {
                // create new token (selector, validator)
                String selector = RandomStringUtils.randomAlphanumeric(12);
                String rawValidator = RandomStringUtils.randomAlphanumeric(64);

                UserAuthToken newToken = new UserAuthToken();

                String hashedValidator = HashGenerator.generateSHA256(rawValidator);

                newToken.setSelector(selector);
                newToken.setValidator(hashedValidator);
                newToken.setUser(user);
                // save the token into the database
                userAuthDAO.save(newToken);
                // create a cookie to store the selector
                Cookie cookieSelector = new Cookie("selector", selector);
                cookieSelector.setMaxAge(24 * 60 * 60 * 365); // for one year
                // create a cookie to store the validator
                Cookie cookieValidator = new Cookie("validator", rawValidator);
                cookieValidator.setMaxAge(24 * 60 * 60 * 365);
                response.addCookie(cookieSelector);
                response.addCookie(cookieValidator);
            }
            // show destination page
            if (user.getType().equalsIgnoreCase("admin")) {
                response.sendRedirect("AdminDashboard");
            } else {
                response.sendRedirect("UserDashboard");
            }
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        userDAO.save(getUser(request));
        ServletController.forwardRequest(request, response, "/index.jsp", "User Registered Successfully.",
                AlertType.SUCCESS, Icon.CHECK);
    }

    private void updateCurrentUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedUser");
        getUser(request, user);
        userDAO.update(user);
        ServletController.forwardRequest(request, response, "AdminDashboard", "User Details updated", AlertType.SUCCESS,
                Icon.CHECK);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // clear the cookies of user
        request.getSession().removeAttribute("loggedUser");

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            String selector = "";

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("selector")) {
                    selector = cookie.getValue();
                    break;
                }
            }

            if (!selector.isEmpty()) {
                // delete token from database
                UserAuthToken token = userAuthDAO.findBySelector(selector);

                if (token != null) {
                    userAuthDAO.delete(token);

                    // delete cookies form cookie object
                    Cookie cookieSelector = new Cookie("selector", "");
                    cookieSelector.setMaxAge(0);

                    Cookie cookieValidator = new Cookie("validator", "");
                    cookieValidator.setMaxAge(0);
                    response.addCookie(cookieSelector);
                    response.addCookie(cookieValidator);
                }
            }
        }
        ServletController.forwardRequest(request, response, "index.jsp");
    }

    /*
     * set detail of user request by form into object
     */
    private User getUser(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart("img");
        return new User(request.getParameter("name"), request.getParameter("email"), request.getParameter("password"),
                "user", ServletController.getBlob(filePart));
    }

    private User getUser(HttpServletRequest request, User user) throws IOException, ServletException {
        Part filePart = request.getPart("img");
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
        return user;
    }
}
