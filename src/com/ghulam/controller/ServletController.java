package com.ghulam.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ghulam.utility.css.AlertType;
import com.ghulam.utility.css.Icon;

public class ServletController {
	/*
	 * Forward the Request to the given url
	 */
	public static void forwardRequest(HttpServletRequest request, HttpServletResponse response, String url, String msg,
			AlertType cssClass, Icon icon) throws ServletException, IOException {
		setNotificationContent(request, true, msg, cssClass, icon);
		request.getRequestDispatcher(url).forward(request, response);
	}

	/*
	 * Forward the Request to the given url. By default notification is false
	 */
	public static void forwardRequest(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		setNotificationContent(request, false, "", AlertType.INTO, Icon.INFO);
		request.getRequestDispatcher(url).forward(request, response);
	}

	/*
	 * set the notification details
	 */
	private static void setNotificationContent(HttpServletRequest request, boolean isToastShow, String msg,
			AlertType alertType, Icon icon) {
		request.setAttribute("isShowToast", isToastShow);
		request.setAttribute("msg", msg);
		request.setAttribute("alertType", alertType.toString());
		request.setAttribute("icon", icon.toString());
	}

	/*
	 * change to Part to Blog object
	 */
	public static Blob getBlob(Part filePart) throws IOException {
		if (filePart != null) {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			Blob blob = Hibernate.getLobCreator(session).createBlob(filePart.getInputStream(), filePart.getSize());
			session.close();
			sessionFactory.close();
			return blob;
		}
		return null;
	}


	/*

	 */
	public static String getBase64Image(Blob image, String base64Image) {
		if ((base64Image == null) && (image != null)) {
			try {
				byte[] bytes = image.getBytes(1, (int) image.length());
				base64Image = Base64.getEncoder().encodeToString(bytes);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return base64Image;
	}
}
