package com.capgemini.librarymanagementsystemspringrest.service;

import java.util.List;

import com.capgemini.librarymanagementsystemspringrest.dto.Book;
import com.capgemini.librarymanagementsystemspringrest.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemspringrest.dto.RequestDetails;
import com.capgemini.librarymanagementsystemspringrest.dto.User;

public interface AdminService {

	boolean addBook(Book book);

	boolean removeBook(int bId);

	boolean updateBook(Book book);

	boolean issueBook(int bId, int uId);

	List<RequestDetails> showRequests();

	List<BookIssueDetails> showIssuedBooks();

	List<User> showUsers();

}
