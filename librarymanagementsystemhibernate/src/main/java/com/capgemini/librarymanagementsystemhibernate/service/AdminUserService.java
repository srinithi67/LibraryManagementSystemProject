package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dto.Book;
import com.capgemini.librarymanagementsystemhibernate.dto.User;

public interface AdminUserService {
	boolean register(User user);

	User authUser(String email, String password);

	List<Book> searchBookById(int bId);

	List<Book> searchBookByTitle(String bookName);

	List<Book> searchBookByAuthor(String authorName);

	List<Book> getBooksInfo();

	boolean updatePassword(String email, String password, String newPassword, String role);

}
