package com.ghulam.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ghulam.model.ActivityLog;
import com.ghulam.model.Book;
import com.ghulam.model.IssueBookAndRequest;
import com.ghulam.model.User;
import com.ghulam.model.UserAuthToken;

public class IssueBookAndRequestDAO extends JpaDAO<IssueBookAndRequest> {
	private static int totalIssueBook = -1;
	private static int totalBookRequest = -1;



	public enum Type {
		ISSUE_BOOK, BOOK_REQUEST;
	}

	// Initialize the totalIssueBook variable
	static {
		IssueBookAndRequestDAO issueBookAndRequestDAO = new IssueBookAndRequestDAO();
		totalIssueBook = issueBookAndRequestDAO.getAllIssueBook().size();
		totalBookRequest = issueBookAndRequestDAO.getAllBookRequest().size();
	}

	public IssueBookAndRequestDAO() {
		super(IssueBookAndRequest.class, User.class, Book.class, UserAuthToken.class, ActivityLog.class);
	}

	public List<IssueBookAndRequest> getAllBookRequest() {
		return findByType(Type.BOOK_REQUEST.toString());
	}

	public List<IssueBookAndRequest> getAllIssueBook() {
		return findByType(Type.ISSUE_BOOK.toString());
	}

	private List<IssueBookAndRequest> findByType(String type) {
		HashMap<String, String> parameters = new HashMap<>();
		parameters.put("bookType", type);
		List<IssueBookAndRequest> list = findWithQuery("FROM IssueBookAndRequest ibr WHERE ibr.bookType = :bookType",
				parameters);
		return list;
	}

	public static int getTotalIssueBook() {
		return totalIssueBook;
	}

	public static int getTotalBookRequest() {
		return totalBookRequest;
	}

	/*
	 * This is method is use to increase Issue Book records counter and decrease
	 * Request Book records counter
	 */
	public static void updateIssueBookAndRequest() {
		totalBookRequest--;
		totalIssueBook++;
	}

	/*
	 * This is method is use decrease Request Book records counter
	 */
	public static void decreaseBookRequest() {
		totalBookRequest--;
	}

	protected void updateCount() {
		IssueBookAndRequestDAO issueBookAndRequestDAO = new IssueBookAndRequestDAO();
		totalIssueBook = issueBookAndRequestDAO.getAllIssueBook().size();
		totalIssueBook = issueBookAndRequestDAO.getAllBookRequest().size();
	}

	@Override
	protected void updateCount(int increamentValue) {
		// There is no implementation
	}

	protected void updateCount(int increamentValue, String type) {
		if (type.equals(Type.BOOK_REQUEST.toString())) {
			totalBookRequest += increamentValue;
		} else {
			totalIssueBook += increamentValue;
		}
	}

	@Override
	public void saveAll(List<IssueBookAndRequest> list) {
		super.saveAll(list);
		updateCount(1, Type.BOOK_REQUEST.toString());
	}

	@Override
	public void save(IssueBookAndRequest obj) {
		super.save(obj);
		updateCount(1, obj.getBookType());
	}

	@Override
	public void delete(int id) {
		super.delete(id);
		updateCount();
	}

	@Override
	public void delete(IssueBookAndRequest obj) {
		super.delete(obj);
		updateCount(-1, obj.getBookType());
	}
}