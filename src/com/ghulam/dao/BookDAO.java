package com.ghulam.dao;

import com.ghulam.model.Book;

public class BookDAO extends JpaDAO<Book> {

	private static int totalBook = -1;

	// Initialize the totalBook variable
	static {
		totalBook = new BookDAO().getAll().size();
	}

	public BookDAO() {
		super(Book.class);
	}

	public static int getTotalBook() {
		return totalBook;
	}

	@Override
	protected void updateCount(int increamentValue) {
		totalBook += increamentValue;
	}
}
