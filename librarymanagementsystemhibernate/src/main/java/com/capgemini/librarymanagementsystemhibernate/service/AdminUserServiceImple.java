package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dao.AdminUserDAO;
import com.capgemini.librarymanagementsystemhibernate.dto.Book;
import com.capgemini.librarymanagementsystemhibernate.dto.User;
import com.capgemini.librarymanagementsystemhibernate.factory.LMSFactory;

public class AdminUserServiceImple implements AdminUserService {
	private AdminUserDAO dao = LMSFactory.getAdminUserDAO();

	@Override
	public boolean register(User user) {
		return dao.register(user);
	}

	@Override
	public User authUser(String email, String password) {
		return dao.authUser(email, password);
	}

	@Override
	public List<Book> searchBookById(int bId) {
		return dao.searchBookById(bId);
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
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		return dao.updatePassword(email, password, newPassword, role);
	}

}
