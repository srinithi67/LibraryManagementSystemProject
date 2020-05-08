package com.capgemini.librarymanagementsystem;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.User;
import com.capgemini.librarymanagementsystem.service.UserService;
import com.capgemini.librarymanagementsystem.service.UserServiceImple;

public class UserServiceTest {
	private UserService service = new UserServiceImple();
	
	@Test
	public void testRegisterUser() {
		User u = new User();
		u.setuId(111222);
		u.setuName("paparao");
		u.setuMobile(987654321);
		u.setuPassword("paparao@123");
		u.setuEmail("paparao@gmail.com");
		u.setuDept("cse");
		u.setReturnDate(null);
		u.setIssueDate(null);
		u.setBooksBorrowed(1);
		boolean b = service.registerUser(u);
		Assertions.assertTrue(b);
	}

	@Test
	public void testRegisterUser1() {
		User u = new User();
		u.setuId(111222);
		u.setuName("paparao");
		u.setuMobile(987654321);
		u.setuPassword("paparao@123");
		u.setuEmail("paparao@gmail.com");
		u.setuDept("cse");
		u.setReturnDate(null);
		u.setIssueDate(null);
		u.setBooksBorrowed(1);
		boolean b1 = service.registerUser(u);
		Assertions.assertTrue(b1);
	}

	@Test
	public void testAuthUser() {
		User u = service.authUser("paparao@gmail.com", "paparao@123");
		Assertions.assertNotNull(u);
	}

	@Test
	public void testAuthUser1() {
		User u1 = service.authUser("paparao@gmail.com", "paparao@123");
		Assertions.assertNotNull(u1);
	}

	@Test
	public void testGetBooksInfo() {
		List<Book> b = service.getBooksInfo();
		Assertions.assertNotNull(b);

	}

	@Test
	public void testGetBooksInfo1() {
		List<Book> b1 = service.getBooksInfo();
		Assertions.assertNotNull(b1);

	}

	@Test
	public void testSearchByTitle() {
		List<Book> info = service.searchBookByTitle("hibernate");
		Assertions.assertNotNull(info);
	}

	@Test
	public void testSearchByTitle1() {
		List<Book> info1 = service.searchBookByTitle("hibernate");
		Assertions.assertNotNull(info1);
	}

	@Test
	public void testSearchByAuthor() {
		List<Book> info = service.searchBookByAuthor("rahul");
		Assertions.assertNotNull(info);
	}

	@Test
	public void testSearchByAuthor1() {
		List<Book> info1 = service.searchBookByAuthor("rahul");
		Assertions.assertNotNull(info1);
	}

	@Test
	public void testSearchByCategory() {
		List<Book> info = service.searchBookByCategory("aptitude");
		Assertions.assertNotNull(info);
	}

	@Test
	public void testSearchByCategory1() {
		List<Book> info1 = service.searchBookByCategory("aptitude");
		Assertions.assertNotNull(info1);
	}

}
