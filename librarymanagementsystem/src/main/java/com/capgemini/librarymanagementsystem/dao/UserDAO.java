package com.capgemini.librarymanagementsystem.dao;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public interface UserDAO {
	boolean registerUser(User user);

	User authUser(String email, String password);

	public Request bookRequest(User user, Book book);

	public Request bookReturn(User user, Book book); // Book borrow(int bookId);

	ArrayList<Book> searchBookByTitle(String bname);

	ArrayList<Book> searchBookByAuthor(String bAuthor);

	ArrayList<Book> searchBookByCategory(String category);

	ArrayList<Book> getBooksInfo();

}
