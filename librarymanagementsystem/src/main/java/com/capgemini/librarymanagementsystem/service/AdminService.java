package com.capgemini.librarymanagementsystem.service;

import java.util.List;

import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public interface AdminService {
	Admin auth(String email, String password);

	boolean register(Admin a);

	boolean addBook(Book b);

	boolean issueBooks(User user, Book book);

	boolean removeBook(int bookId);

	boolean updateBook(Book book);

	List<Book> searchBookByTitle(String bname);

	List<Book> searchBookByAuthor(String bauthor);

	List<Book> searchBookByCategory(String category);

	List<Book> getBooks();

	boolean isBookReceived(User user, Book book);

	List<Request> showRequests();

	List<User> showUsers();

}
