package com.capgemini.librarymanagementsystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystem.db.BookDatabase;
import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;
import com.capgemini.librarymanagementsystem.exception.LMSException;

public class AdminDAOImple implements AdminDAO {

	@Override
	public boolean register(Admin a) {
		for (Admin a1 : BookDatabase.admin) {
			if (a1.getaEmail().equals(a.getaEmail())) {
				return false;
			}
		}
		BookDatabase.admin.add(a);
		return true;
	}

	@Override
	public Admin auth(String email, String password) {
		for (Admin a : BookDatabase.admin) {
			if (a.getaEmail().equals(email) && a.getaPassword().equals(password)) {

				return a;
			}
		}
		throw new LMSException("Invalid Credentials");
	}

	@Override
	public boolean addBook(Book b) {
		for (Book book : BookDatabase.book) {
			if (book.getBookId() == b.getBookId()) {
				return false;
			}
		}
		BookDatabase.book.add(b);
		return true;
	}

	@Override
	public boolean removeBook(int bookId) {
		boolean removeStatus = false;
		for (int i = 0; i <= BookDatabase.book.size() - 1; i++) {
			Book retrievedBook = BookDatabase.book.get(i);
			int retrievedId = retrievedBook.getBookId();
			if (bookId == retrievedId) {
				removeStatus = true;
				BookDatabase.book.remove(i);
				break;
			}
		}
		return removeStatus;
	}

	@Override
	public ArrayList<Book> searchBookByTitle(String bname) {

		ArrayList<Book> searchList = new ArrayList<Book>();
		for (int i = 0; i <= BookDatabase.book.size() - 1; i++) {
			Book retrievedBook = BookDatabase.book.get(i);
			String retrievedBname = retrievedBook.getBookName();
			if (bname.equals(retrievedBname)) {
				searchList.add(retrievedBook);
				return searchList;
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
			return searchList;
		}

	}

	@Override
	public ArrayList<Book> searchBookByAuthor(String bauthor) {
		ArrayList<Book> searchList = new ArrayList<Book>();
		for (int i = 0; i <= BookDatabase.book.size() - 1; i++) {
			Book retrievedBook = BookDatabase.book.get(i);
			String retrievedBAuthor = retrievedBook.getAuthorName();
			if (bauthor.equals(retrievedBAuthor)) {
				searchList.add(retrievedBook);
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
			return searchList;
		}

	}

	@Override
	public ArrayList<Book> getBooks() {

		return BookDatabase.book;
	}

	@Override
	public boolean issueBooks(User user, Book book) {
		boolean isValid = false;

		Request requestInfo = new Request();

		int noOfBooksBorrowed = user.getBooksBorrowed();
		for (Request info : BookDatabase.REQUEST) {
			if (info.getUserInfo().getuId() == user.getuId()) {
				if (info.getBookInfo().getBookId() == book.getBookId()) {
					requestInfo = info;

					isValid = true;

				}
			}
		}

		if (isValid) {
			for (Book info2 : BookDatabase.book) {
				if (info2.getBookId() == book.getBookId()) {
					book = info2;
				}
			}

			for (User userInfo : BookDatabase.user) {
				if (userInfo.getuId() == user.getuId()) {
					user = userInfo;
					noOfBooksBorrowed = user.getBooksBorrowed();

				}
			}

			if (noOfBooksBorrowed < 3) {

				boolean isRemoved = BookDatabase.book.remove(book);
				if (isRemoved) {

					noOfBooksBorrowed++;
					System.out.println(noOfBooksBorrowed);
					user.setBooksBorrowed(noOfBooksBorrowed);
					// DataBase.REQUESTDB.remove(requestInfo);
					requestInfo.setIssued(true);
					return true;
				} else {
					throw new LMSException("Book can't be borrowed");
				}

			} else {
				throw new LMSException("Student Exceeds maximum limit");
			}

		} else {
			throw new LMSException("Book data or Student data is incorrect");
		}
	}

	@Override
	public boolean isBookReceived(User user, Book book) {
		boolean isValid = false;
		Request requestInfo1 = new Request();
		for (Request requestInfo : BookDatabase.REQUEST) {

			if (requestInfo.getBookInfo().getBookId() == book.getBookId()
					&& requestInfo.getUserInfo().getuId() == user.getuId() && requestInfo.isReturned() == true) {
				isValid = true;
				requestInfo1 = requestInfo;
			}
		}
		if (isValid) {

			book.setAuthorName(requestInfo1.getBookInfo().getAuthorName());
			book.setBookName(requestInfo1.getBookInfo().getBookName());
			BookDatabase.book.add(book);
			BookDatabase.REQUEST.remove(requestInfo1);

			for (User userInfo2 : BookDatabase.user) {
				if (userInfo2.getuId() == user.getuId()) {
					user = userInfo2;
				}
			}
			int noOfBooksBorrowed = user.getBooksBorrowed();
			noOfBooksBorrowed--;
			user.setBooksBorrowed(noOfBooksBorrowed);
			return true;
		}
		return false;

	}

	@Override
	public List<Request> showRequests() {
		List<Request> info = new ArrayList<Request>();
		for (Request requestInfo : BookDatabase.REQUEST) {
			requestInfo.getBookInfo();
			requestInfo.getUserInfo();
			requestInfo.isIssued();
			requestInfo.isReturned();
			info.add(requestInfo);
		}
		return info;
	}

	@Override
	public List<User> showUsers() {
		List<User> usersList = new ArrayList<User>();
		for (User userBean : BookDatabase.user) {

			userBean.getuId();
			userBean.getuName();
			userBean.getuEmail();
			userBean.getBooksBorrowed();
			usersList.add(userBean);

		}
		return usersList;

	}

	@Override
	public ArrayList<Book> searchBookByCategory(String category) {
		ArrayList<Book> searchList = new ArrayList<Book>();
		for (int i = 0; i <= BookDatabase.book.size() - 1; i++) {
			Book retrievedBook = BookDatabase.book.get(i);
			String retrievedCategory = retrievedBook.getCategory();
			if (category.equals(retrievedCategory)) {
				searchList.add(retrievedBook);
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
			return searchList;
		}
	}

	@SuppressWarnings("unused")
	@Override
	public boolean updateBook(Book book) {
		for (int i = 0; i <= BookDatabase.book.size() - 1; i++) {
			Book retrievedBook = BookDatabase.book.get(i);
			if (retrievedBook.getBookId() == book.getBookId()) {
				retrievedBook.setBookName(book.getBookName());
				retrievedBook.setAuthorName(book.getAuthorName());
				retrievedBook.setCategory(book.getCategory());
				return true;
			}

			else {
				throw new LMSException("Invalid Book");
			}
		}
		throw new LMSException("Book not updated");

	}

}
