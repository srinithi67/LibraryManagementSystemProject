package com.capgemini.librarymanagementsystemhibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemhibernate.dto.Book;
import com.capgemini.librarymanagementsystemhibernate.dto.User;
import com.capgemini.librarymanagementsystemhibernate.service.AdminUserService;
import com.capgemini.librarymanagementsystemhibernate.service.AdminUserServiceImple;


public class AdminUserServiceTest {
	
private AdminUserService service = new AdminUserServiceImple();
	
	@Test
	public void testRegisterUser() {
		User user= new User();
		user.setFirstName("myz");
		user.setLastName("abc");
		user.setEmail("myz@gmail.com");
		user.setMobile(987654321);
		user.setPassword("myz@1212");
		user.setUId(13);
		user.setRole("student");
		boolean status=service.register(user);
		Assertions.assertTrue(status);
		
		
	}
	@Test
	public void testRegisterUser1() {
		User user= new User();
		user.setFirstName("myz");
		user.setLastName("myz");
		user.setEmail("myz@gmail.com");
		user.setMobile(987654321);
		user.setPassword("myz@1212");
		user.setUId(12);
		user.setRole("student");
		boolean status1=service.register(user);
		Assertions.assertTrue(status1);
		
		}
	@Test
	public void testAuthUser() {
		User status = service.authUser("myz@gmail.com", "myz@1212");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testAuthUser1() {
		User status1 = service.authUser("myz@gmail.com", "myz@1212");
		Assertions.assertNotNull(status1);
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
	public void testUpdatePassword() {
		boolean msg =service.updatePassword("sri@gmail.com",  "sri@123", "sri@1234", "student");
		Assertions.assertTrue(msg);

	}
	@Test
	public void testUpdatePassword1() {
		boolean msg1 =service.updatePassword("sri@gmail.com",  "sri@123", "sri@1234", "student");
		Assertions.assertTrue(msg1);

	}
	@Test
	public void testSearchBookById() {
		List<Book> b=service.searchBookById(121212);
		Assertions.assertNotNull(b);
	}
	@Test
	public void testSearchBookById1() {
		List<Book> b1=service.searchBookById(121212);
		Assertions.assertNotNull(b1);
	}
	@Test
	public void testSearchBookByAuthor() {
		List<Book> check = service.searchBookByAuthor("williams");
		Assertions.assertNotNull(check);
		
	}
	@Test
	public void testSearchBookByAuthor1() {
		List<Book> check1 = service.searchBookByAuthor("williams");
		Assertions.assertNotNull(check1);
		
	}
	@Test
	public void testSearchBookByTitle() {
		List<Book> info = service.searchBookByTitle("Java");
		Assertions.assertNotNull(info);
		
	}
	@Test
	public void testSearchBookByTitle1() {
		List<Book> info1 = service.searchBookByTitle("Java");
		Assertions.assertNotNull(info1);
		
	}



}
