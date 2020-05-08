package com.capgemini.librarymanagementsystemspringrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystemspringrest.dao.AdminDAO;
import com.capgemini.librarymanagementsystemspringrest.dto.Book;
import com.capgemini.librarymanagementsystemspringrest.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemspringrest.dto.RequestDetails;
import com.capgemini.librarymanagementsystemspringrest.dto.User;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDAO dao;
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
