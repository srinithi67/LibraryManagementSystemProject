package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dao.UserDAO;
import com.capgemini.librarymanagementsystemhibernate.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemhibernate.factory.LMSFactory;

public class UserServiceImple implements UserService {

	private UserDAO dao = LMSFactory.getUserDAO();

	@Override
	public boolean request(int uId, int bId) {
		return dao.request(uId, bId);
	}

	@Override
	public List<BorrowedBooks> borrowedBooks(int uId) {
		return dao.borrowedBooks(uId);
	}

	@Override
	public boolean returnBook(int bId, int uId, String status) {
		return dao.returnBook(bId, uId, status);
	}

	@Override
	public List<Integer> bookHistoryDetails(int uId) {
		return dao.bookHistoryDetails(uId);
	}

}
