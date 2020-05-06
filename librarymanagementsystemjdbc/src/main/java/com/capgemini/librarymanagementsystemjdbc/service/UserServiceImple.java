package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemjdbc.dto.Book;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.User;
import com.capgemini.librarymanagementsystemjdbc.factory.LMSFactory;

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
	public boolean addBook(Book book) {
		return dao.addBook(book);
	}

	@Override
	public boolean removeBook(int bookId) {
		return dao.removeBook(bookId);
	}

	@Override
	public boolean updateBook(Book book) {
		return dao.updateBook(book);
	}

	@Override
	public boolean bookIssue(int bookId, int uId) {
		return dao.bookIssue(bookId, uId);
	}

	@Override
	public boolean request(int uId, int bookId) {
		return dao.request(uId, bookId);
	}

	@Override
	public List<BorrowedBooks> borrowedBook(int uId) {
		return dao.borrowedBook(uId);
	}

	@Override
	public boolean returnBook(int bId, int uId, String status) {
		return dao.returnBook(bId, uId, status);
	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		return dao.updatePassword(email, password, newPassword, role);
	}

	@Override
	public ArrayList<Book> searchBookById(int bookId) {
		return dao.searchBookById(bookId);
	}

	@Override
	public ArrayList<Book> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public ArrayList<Book> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public ArrayList<Book> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public ArrayList<BookIssueDetails> bookHistoryDetails(int uId) {
		return dao.bookHistoryDetails(uId);
	}

	@Override
	public ArrayList<RequestDetails> showRequests() {
		return dao.showRequests();
	}

	@Override
	public ArrayList<BookIssueDetails> showIssuedBooks() {
		return dao.showIssuedBooks();
	}

	@Override
	public ArrayList<User> showUsers() {
		return dao.showUsers();
	}

}
