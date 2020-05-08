package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.Book;
import com.capgemini.librarymanagementsystemjdbc.dto.User;

public interface AdminUserService {
	
	boolean registerUser(User user);

	User authUser(String email, String password);

	List<Book> searchBookById(int bookId);

	List<Book> searchBookByTitle(String bookName);

	List<Book> searchBookByAuthor(String author);

	List<Book> getBooksInfo();

	boolean updatePassword(String email, String password, String newPassword, String role);

}


