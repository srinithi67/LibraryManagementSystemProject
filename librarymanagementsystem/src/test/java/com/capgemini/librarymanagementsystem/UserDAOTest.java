package com.capgemini.librarymanagementsystem;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dao.UserDAO;
import com.capgemini.librarymanagementsystem.dao.UserDAOImple;
import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public class UserDAOTest {
	private UserDAO dao = new UserDAOImple();

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
		boolean b = dao.registerUser(u);
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
		boolean b1 = dao.registerUser(u);
		Assertions.assertTrue(b1);
	}

	@Test
	public void testAuthUser() {
		User u = dao.authUser("paparao@gmail.com", "paparao@123");
		Assertions.assertNotNull(u);
	}

	@Test
	public void testAuthUser1() {
		User u1 = dao.authUser("paparao@gmail.com", "paparao@123");
		Assertions.assertNotNull(u1);
	}

	@Test
	public void testGetBooksInfo() {
		List<Book> b = dao.getBooksInfo();
		Assertions.assertNotNull(b);

	}

	@Test
	public void testGetBooksInfo1() {
		List<Book> b1 = dao.getBooksInfo();
		Assertions.assertNotNull(b1);

	}

	@Test
	public void testSearchByTitle() {
		List<Book> info = dao.searchBookByTitle("hibernate");
		Assertions.assertNotNull(info);
	}

	@Test
	public void testSearchByTitle1() {
		List<Book> info1 = dao.searchBookByTitle("hibernate");
		Assertions.assertNotNull(info1);
	}

	@Test
	public void testSearchByAuthor() {
		List<Book> info = dao.searchBookByAuthor("rahul");
		Assertions.assertNotNull(info);
	}

	@Test
	public void testSearchByAuthor1() {
		List<Book> info1 = dao.searchBookByAuthor("rahul");
		Assertions.assertNotNull(info1);
	}

	@Test
	public void testSearchByCategory() {
		List<Book> info = dao.searchBookByCategory("aptitude");
		Assertions.assertNotNull(info);
	}

	@Test
	public void testSearchByCategory1() {
		List<Book> info1 = dao.searchBookByCategory("aptitude");
		Assertions.assertNotNull(info1);
	}

}
