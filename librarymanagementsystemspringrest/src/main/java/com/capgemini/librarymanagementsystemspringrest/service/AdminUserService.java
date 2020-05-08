package com.capgemini.librarymanagementsystemspringrest.service;

import java.util.List;

import com.capgemini.librarymanagementsystemspringrest.dto.Book;
import com.capgemini.librarymanagementsystemspringrest.dto.User;

public interface AdminUserService {
	
	boolean register(User user);

	User auth(String email, String password);

	List<Book> searchBookById(int bookId);

	List<Book> searchBookByTitle(String bookName);

	List<Book> searchBookByAuthor(String authorName);

	List<Book> getBooksInfo();

	boolean updatePassword(int id, String password, String newPassword, String role);

}
