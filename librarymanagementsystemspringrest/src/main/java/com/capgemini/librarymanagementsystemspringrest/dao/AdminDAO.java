package com.capgemini.librarymanagementsystemspringrest.dao;

import java.util.List;

import com.capgemini.librarymanagementsystemspringrest.dto.Book;
import com.capgemini.librarymanagementsystemspringrest.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemspringrest.dto.RequestDetails;
import com.capgemini.librarymanagementsystemspringrest.dto.User;

public interface AdminDAO {

	boolean addBook(Book book);

	boolean removeBook(int bookId);

	boolean updateBook(Book book);

	boolean issueBook(int bookId, int uId);

	List<RequestDetails> showRequests();

	List<BookIssueDetails> showIssuedBooks();

	List<User> showUsers();
}
