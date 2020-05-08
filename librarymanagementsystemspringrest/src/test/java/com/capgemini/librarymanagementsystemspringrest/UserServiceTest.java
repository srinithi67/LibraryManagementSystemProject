package com.capgemini.librarymanagementsystemspringrest;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.librarymanagementsystemspringrest.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemspringrest.service.UserService;

public class UserServiceTest {

	@Autowired
	private UserService service;

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
		boolean b = service.returnBook(2, 909090, "returning");
		Assertions.assertTrue(b);
	}

	@Test
	public void testReturnBook1() {
		boolean b1 = service.returnBook(2, 909090, "returning");
		Assertions.assertTrue(b1);
	}

	@Test
	public void testBorrowedBook() {
		List<BorrowedBooks> b = service.borrowedBook(2);
		Assertions.assertNotNull(b);
	}

	@Test
	public void testBorrowedBook1() {
		List<BorrowedBooks> b1 = service.borrowedBook(2);
		Assertions.assertNotNull(b1);
	}

}
