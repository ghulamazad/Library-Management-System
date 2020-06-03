package com.ghulam.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ghulam.utility.CustomDateTimeFormatter;

@Entity
@Table(name = "issue_book_and_request")
public class IssueBookAndRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id")
	private Book book;
	private String bookType;
	private LocalDateTime issueDate;
	private LocalDateTime returnDate;

	public IssueBookAndRequest() {
	}

	public IssueBookAndRequest(User user, Book book, String bookType, LocalDateTime issueDate, LocalDateTime returnDate) {
		this.user = user;
		this.book = book;
		this.bookType = bookType;
		this.issueDate =issueDate;
		this.returnDate = returnDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getIssueDate() {
		return  CustomDateTimeFormatter.format(issueDate);
	}

	public void setIssueDate(LocalDateTime issueDate) {
		this.issueDate = issueDate;
	}

	public String getReturnDate() {
		return CustomDateTimeFormatter.format(returnDate);
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}
}