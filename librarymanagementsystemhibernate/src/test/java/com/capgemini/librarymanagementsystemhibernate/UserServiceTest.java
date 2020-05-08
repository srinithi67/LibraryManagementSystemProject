package com.capgemini.librarymanagementsystemhibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemhibernate.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemhibernate.service.UserService;
import com.capgemini.librarymanagementsystemhibernate.service.UserServiceImple;


public class UserServiceTest {
private UserService service = new UserServiceImple();
	
	@Test
	public void testRequest() {
		boolean b = service.request(909090, 975310);
		Assertions.assertTrue(b);
	}
	@Test
	public void testBookHistoryDetails() {
		List<Integer> info = service.bookHistoryDetails(4);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testBookHistoryDetails1() {
		List<Integer> info1 = service.bookHistoryDetails(4);
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
		List<BorrowedBooks> b=service.borrowedBooks(2);
		Assertions.assertNotNull(b);
	}
	@Test
	public void testBorrowedBook1() {
		List<BorrowedBooks> b1=service.borrowedBooks(2);
		Assertions.assertNotNull(b1);
	}
	
	



}
