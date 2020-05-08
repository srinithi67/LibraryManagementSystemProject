package com.capgemini.librarymanagementsystemspringrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystemspringrest.dao.AdminUserDAO;
import com.capgemini.librarymanagementsystemspringrest.dto.Book;
import com.capgemini.librarymanagementsystemspringrest.dto.User;

@Service
public class AdminUserServiceImpl implements AdminUserService{

	@Autowired
	private AdminUserDAO dao;
	
	@Override
	public boolean register(User user) {
		return dao.register(user);
	}

	@Override
	public User auth(String email, String password) {
		return dao.auth(email, password);
	}

	@Override
	public List<Book> searchBookById(int bookId) {
		return dao.searchBookById(bookId);
	}

	@Override
	public List<Book> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public List<Book> searchBookByAuthor(String authorName) {
		return dao.searchBookByAuthor(authorName);
	}

	@Override
	public List<Book> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {
		return dao.updatePassword(id, password, newPassword, role);
	}

}
