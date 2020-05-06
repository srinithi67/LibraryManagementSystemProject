package com.capgemini.librarymanagementsystemjdbc.dao;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.Book;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.User;

public interface UserDAO {
	boolean registerUser(User user);

	User authUser(String email, String password);

	boolean addBook(Book book);

	boolean removeBook(int bookId);

	boolean updateBook(Book book);

	boolean bookIssue(int bookId, int uId);

	boolean request(int uId, int bookId);

	List<BorrowedBooks> borrowedBook(int uId);

	ArrayList<Book> searchBookById(int bookId);

	ArrayList<Book> searchBookByTitle(String bookName);

	ArrayList<Book> searchBookByAuthor(String author);

	ArrayList<Book> getBooksInfo();

	boolean returnBook(int bId, int uId, String status);

	ArrayList<BookIssueDetails> bookHistoryDetails(int uId);

	ArrayList<RequestDetails> showRequests();

	ArrayList<BookIssueDetails> showIssuedBooks();

	ArrayList<User> showUsers();

	boolean updatePassword(String email, String password, String newPassword, String role);
}
