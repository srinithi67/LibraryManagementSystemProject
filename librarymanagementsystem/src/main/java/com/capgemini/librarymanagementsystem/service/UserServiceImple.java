package com.capgemini.librarymanagementsystem.service;


import java.util.List;

import com.capgemini.librarymanagementsystem.dao.UserDAO;
import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;
import com.capgemini.librarymanagementsystem.factory.LMSFactory;

public class UserServiceImple implements UserService {
	private UserDAO dao = LMSFactory.getUserDAO();

	@Override
	public boolean registerUser(User user) {
		return dao.registerUser(user);

	}

	@Override
	public User authUser(String email, String password) {
		return dao.authUser(email, password);

	}

	@Override
	public List<Book> searchBookByTitle(String bname) {
		return dao.searchBookByTitle(bname);
	}

	@Override
	public List<Book> searchBookByAuthor(String bAuthor) {
		return dao.searchBookByAuthor(bAuthor);
	}

	@Override
	public List<Book> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public Request bookRequest(User user, Book book) {
		return dao.bookRequest(user, book);
	}

	@Override
	public Request bookReturn(User user, Book book) {
		return dao.bookReturn(user, book);
	}

	@Override
	public List<Book> searchBookByCategory(String category) {
		return dao.searchBookByCategory(category);
	}

}
