package com.capgemini.librarymanagementsystemjdbc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.UserDAOImple;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;

public class UserDAOTest {
	
	private UserDAO dao = new UserDAOImple();
	@Test
	public void testRequest() {
		boolean b = dao.request(909090, 975310);
		Assertions.assertTrue(b);
	}
	@Test
	public void testBookHistoryDetails() {
		List<BookIssueDetails> info = dao.bookHistoryDetails(4);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testBookHistoryDetails1() {
		List<BookIssueDetails> info1 = dao.bookHistoryDetails(4);
		Assertions.assertNotNull(info1);
	}
	@Test
	public void testReturnBook() {
		boolean b=dao.returnBook(2,909090,"returning");
		Assertions.assertTrue(b);
	}
	@Test
	public void testReturnBook1() {
		boolean b1=dao.returnBook(2,909090,"returning");
		Assertions.assertTrue(b1);
	}
	@Test
	public void testBorrowedBook() {
		List<BorrowedBooks> b=dao.borrowedBook(2);
		Assertions.assertNotNull(b);
	}
	@Test
	public void testBorrowedBook1() {
		List<BorrowedBooks> b1=dao.borrowedBook(2);
		Assertions.assertNotNull(b1);
	}
	
	

}
