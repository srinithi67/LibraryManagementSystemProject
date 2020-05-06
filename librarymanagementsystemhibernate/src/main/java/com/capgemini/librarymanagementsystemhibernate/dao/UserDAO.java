package com.capgemini.librarymanagementsystemhibernate.dao;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dto.Book;
import com.capgemini.librarymanagementsystemhibernate.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemhibernate.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestDetails;
import com.capgemini.librarymanagementsystemhibernate.dto.User;

public interface UserDAO {
	boolean register(User user);
	User authUser(String email,String password);
	boolean addBook(Book book);
	boolean removeBook(int bId);
	boolean updateBook(Book book);
	boolean issueBook(int bId,int uId);
	boolean request(int uId, int bId);
	List<BorrowedBooks> borrowedBooks(int uId);
	List<Book> searchBookById(int bId);
	List<Book> searchBookByTitle(String bookName);
	List<Book> searchBookByAuthor(String authorName);
	List<Book> getBooksInfo();
	boolean returnBook(int bId,int uId,String status);
	List<Integer> bookHistoryDetails(int uId);
	List<RequestDetails> showRequests();
	List<BookIssueDetails> showIssuedBooks();
	List<User> showUsers();
	boolean updatePassword(String email,String password,String newPassword,String role);
	
}
	