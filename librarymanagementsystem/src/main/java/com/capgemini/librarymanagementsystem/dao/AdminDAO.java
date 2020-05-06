package com.capgemini.librarymanagementsystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public interface AdminDAO {
	Admin auth(String email, String password);

	boolean register(Admin a);

	boolean addBook(Book b);

	boolean issueBooks(User user, Book book);

	boolean removeBook(int bookId);

	boolean updateBook(Book book);

	ArrayList<Book> searchBookByTitle(String bname);

	ArrayList<Book> searchBookByAuthor(String bauthor);

	ArrayList<Book> searchBookByCategory(String category);

	ArrayList<Book> getBooks();

	boolean isBookReceived(User user, Book book);

	List<Request> showRequests();

	List<User> showUsers();

}
