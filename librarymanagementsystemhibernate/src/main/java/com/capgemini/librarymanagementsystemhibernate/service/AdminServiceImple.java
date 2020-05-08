package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dao.AdminDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.UserDAO;
import com.capgemini.librarymanagementsystemhibernate.dto.Book;
import com.capgemini.librarymanagementsystemhibernate.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestDetails;
import com.capgemini.librarymanagementsystemhibernate.dto.User;
import com.capgemini.librarymanagementsystemhibernate.factory.LMSFactory;

public class AdminServiceImple implements AdminService {
	private AdminDAO dao = LMSFactory.getAdminDAO();

	@Override
	public boolean addBook(Book book) {
		return dao.addBook(book);
	}

	@Override
	public boolean removeBook(int bId) {
		return dao.removeBook(bId);
	}

	@Override
	public boolean updateBook(Book book) {
		return dao.updateBook(book);
	}

	@Override
	public boolean issueBook(int bId, int uId) {
		return dao.issueBook(bId, uId);
	}

	@Override
	public List<RequestDetails> showRequests() {
		return dao.showRequests();
	}

	@Override
	public List<BookIssueDetails> showIssuedBooks() {
		return dao.showIssuedBooks();
	}

	@Override
	public List<User> showUsers() {
		return dao.showUsers();
	}


}
