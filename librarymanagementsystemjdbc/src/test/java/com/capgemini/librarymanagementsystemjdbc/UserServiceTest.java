package com.capgemini.librarymanagementsystemjdbc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.UserDAOImple;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.service.UserService;
import com.capgemini.librarymanagementsystemjdbc.service.UserServiceImple;

public class UserServiceTest {
	private UserService service = new UserServiceImple();
	
	@Test
	public void testRequest() {
		boolean b = service.request(909090, 975310);
		Assertions.assertTrue(b);
	}
	@Test
	public void testBookHistoryDetails() {
		List<BookIssueDetails> info = service.bookHistoryDetails(4);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testBookHistoryDetails1() {
		List<BookIssueDetails> info1 = service.bookHistoryDetails(4);
		Assertions.assertNotNull(info1);
	}
	@Test
	public void testReturnBook() {
		boolean b=service.returnBook(2,909090,"returning");
		Assertions.assertTrue(b);
	}
	@Test
	public void testReturnBook1() {
		boolean b1=service.returnBook(2,909090,"returning");
		Assertions.assertTrue(b1);
	}
	@Test
	public void testBorrowedBook() {
		List<BorrowedBooks> b=service.borrowedBook(2);
		Assertions.assertNotNull(b);
	}
	@Test
	public void testBorrowedBook1() {
		List<BorrowedBooks> b1=service.borrowedBook(2);
		Assertions.assertNotNull(b1);
	}
	
	


}
