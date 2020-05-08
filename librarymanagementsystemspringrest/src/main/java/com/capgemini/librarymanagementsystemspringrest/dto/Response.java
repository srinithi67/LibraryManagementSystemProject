package com.capgemini.librarymanagementsystemspringrest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

	private boolean error;
	private String message;

	private User user;
	private Book book;
	private BookIssueDetails bookIssueDetails;
	private BorrowedBooks borrowedBooks;
	private RequestDetails requestDetails;

	private List<User> user1;
	private List<Book> book1;
	private List<BookIssueDetails> bookIssueDetails1;
	private List<BorrowedBooks> borrowedBooks1;
	private List<RequestDetails> requestDetails1;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public BookIssueDetails getBookIssueDetails() {
		return bookIssueDetails;
	}

	public void setBookIssueDetails(BookIssueDetails bookIssueDetails) {
		this.bookIssueDetails = bookIssueDetails;
	}

	public BorrowedBooks getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(BorrowedBooks borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}

	public RequestDetails getRequestDetails() {
		return requestDetails;
	}

	public void setRequestDetails(RequestDetails requestDetails) {
		this.requestDetails = requestDetails;
	}

	public List<User> getUser1() {
		return user1;
	}

	public void setUser1(List<User> user1) {
		this.user1 = user1;
	}

	public List<Book> getBook1() {
		return book1;
	}

	public void setBook1(List<Book> book1) {
		this.book1 = book1;
	}

	public List<BookIssueDetails> getBookIssueDetails1() {
		return bookIssueDetails1;
	}

	public void setBookIssueDetails1(List<BookIssueDetails> bookIssueDetails1) {
		this.bookIssueDetails1 = bookIssueDetails1;
	}

	public List<BorrowedBooks> getBorrowedBooks1() {
		return borrowedBooks1;
	}

	public void setBorrowedBooks1(List<BorrowedBooks> borrowedBooks1) {
		this.borrowedBooks1 = borrowedBooks1;
	}

	public List<RequestDetails> getRequestDetails1() {
		return requestDetails1;
	}

	public void setRequestDetails1(List<RequestDetails> requestDetails1) {
		this.requestDetails1 = requestDetails1;
	}

}
