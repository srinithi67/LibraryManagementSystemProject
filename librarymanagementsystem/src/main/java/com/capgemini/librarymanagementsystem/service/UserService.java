package com.capgemini.librarymanagementsystem.service;

import java.util.List;

import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public interface UserService {
	boolean registerUser(User user);

	User authUser(String email, String password);

	public Request bookRequest(User user, Book book);

	public Request bookReturn(User user, Book book);

	// Book borrow(int bookId);
	List<Book> searchBookByTitle(String bname);

	List<Book> searchBookByAuthor(String bAuthor);

	List<Book> searchBookByCategory(String category);

	List<Book> getBooksInfo();

}
