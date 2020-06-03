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
import com.ghulam.dao.BookDAO;
import com.ghulam.model.Book;
import com.ghulam.model.User;
import com.ghulam.utility.css.AlertType;
import com.ghulam.utility.css.Icon;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet({ "/saveBook", "/modifyBook", "/deleteBook", "/updateBook" })
public class BookServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static BookDAO bookDAO = new BookDAO();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			switch (request.getServletPath()) {
			case "/saveBook":
				saveBook(request, response);
				break;
			case "/modifyBook":
				modifyBook(request, response);
				break;
			case "/deleteBook":
				deleteBook(request, response);
				break;
			case "/updateBook":
				updateBook(request, response);
				break;
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void saveBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = getBook(request, false);
		bookDAO.save(book);
		ServletController.forwardRequest(request, response, "/AdminBook", "Book Successfully Saved.", AlertType.SUCCESS,
				Icon.CHECK);
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = getBook(request, true);
		bookDAO.update(book);
		ServletController.forwardRequest(request, response, "/AdminBook", "Book Successfully Updated.",
				AlertType.SUCCESS, Icon.CHECK);
	}

	private void modifyBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(id);
		request.setAttribute("isUpdate", true);
		request.setAttribute("book", book);
		ServletController.forwardRequest(request, response, "/AdminBook");
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		bookDAO.delete(id);
		ServletController.forwardRequest(request, response, "/AdminBook", "Book Successfully Deleted.",
				AlertType.SUCCESS, Icon.CHECK);
	}

	/*
	 * form data to object
	 */
	private Book getBook(HttpServletRequest request, boolean isUpdate) throws IOException, ServletException {
		Part filePart = request.getPart("img");
		Book book = new Book();
		if (isUpdate) {
			book.setId(Integer.parseInt(request.getParameter("id")));
		}
		book.setBookName(request.getParameter("bookName"));
		book.setAuthor(request.getParameter("author"));
		book.setQuantity(Integer.parseInt(request.getParameter("bookQuantity")));

		if ((filePart != null) && filePart.getContentType().contains("image/jpeg")) {
			book.setImage(ServletController.getBlob(filePart));
		}
		return book;
	}
}