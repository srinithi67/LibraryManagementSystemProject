package com.capgemini.librarymanagementsystemjdbc;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemjdbc.dao.AdminUserDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminUserDAOImple;
import com.capgemini.librarymanagementsystemjdbc.dto.Book;
import com.capgemini.librarymanagementsystemjdbc.dto.User;

public class AdminUserDAOTest {
	private AdminUserDAO dao = new AdminUserDAOImple();
	
	@Test
	public void testRegisterUser() {
		User user= new User();
		user.setFirstName("xyz");
		user.setLastName("abc");
		user.setEmail("xyz@gmail.com");
		user.setMobile(987654321);
		user.setPassword("xyz@1212");
		user.setuId(12);
		user.setRole("student");
		boolean status=dao.registerUser(user);
		Assertions.assertTrue(status);
		
		
	}
	@Test
	public void testRegisterUser1() {
		User user= new User();
		user.setFirstName("xyz");
		user.setLastName("abc");
		user.setEmail("xyz@gmail.com");
		user.setMobile(987654321);
		user.setPassword("xyz@1212");
		user.setuId(12);
		user.setRole("student");
		boolean status1=dao.registerUser(user);
		Assertions.assertTrue(status1);
		
		}
	@Test
	public void testAuthUser() {
		User status = dao.authUser("xyz@gmail.com", "xyz@1212");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testAuthUser1() {
		User status1 = dao.authUser("xyz@gmail.com", "xyz@1212");
		Assertions.assertNotNull(status1);
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
	public void testUpdatePassword() {
		boolean msg =dao.updatePassword("sri@gmail.com",  "sri@123", "sri@1234", "student");
		Assertions.assertTrue(msg);

	}
	@Test
	public void testUpdatePassword1() {
		boolean msg1 =dao.updatePassword("sri@gmail.com",  "sri@123", "sri@1234", "student");
		Assertions.assertTrue(msg1);

	}
	@Test
	public void testSearchBookById() {
		List<Book> b=dao.searchBookById(121212);
		Assertions.assertNotNull(b);
	}
	@Test
	public void testSearchBookById1() {
		List<Book> b1=dao.searchBookById(121212);
		Assertions.assertNotNull(b1);
	}
	@Test
	public void testSearchBookByAuthor() {
		List<Book> check = dao.searchBookByAuthor("williams");
		Assertions.assertNotNull(check);
		
	}
	@Test
	public void testSearchBookByAuthor1() {
		List<Book> check1 = dao.searchBookByAuthor("williams");
		Assertions.assertNotNull(check1);
		
	}
	@Test
	public void testSearchBookByTitle() {
		List<Book> info = dao.searchBookByTitle("Java");
		Assertions.assertNotNull(info);
		
	}
	@Test
	public void testSearchBookByTitle1() {
		List<Book> info1 = dao.searchBookByTitle("Java");
		Assertions.assertNotNull(info1);
		
	}




}
