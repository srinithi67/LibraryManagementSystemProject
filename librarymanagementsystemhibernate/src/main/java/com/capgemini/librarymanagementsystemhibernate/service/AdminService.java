package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dto.Book;
import com.capgemini.librarymanagementsystemhibernate.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestDetails;
import com.capgemini.librarymanagementsystemhibernate.dto.User;

public interface AdminService {
	
	boolean addBook(Book book);

	boolean removeBook(int bId);

	boolean updateBook(Book book);

	boolean issueBook(int bId, int uId);

	List<RequestDetails> showRequests();

	List<BookIssueDetails> showIssuedBooks();

	List<User> showUsers();

}
