package com.capgemini.librarymanagementsystem;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;
import com.capgemini.librarymanagementsystem.service.AdminService;
import com.capgemini.librarymanagementsystem.service.AdminServiceImple;

public class AdminServiceTest {
	private AdminService service = new AdminServiceImple();
	
	@Test
	public void testAddBook() {
		Book book = new Book();
		book.setBookId(989898);
		book.setBookName("java");
		book.setAuthorName("gossling");
		book.setCategory("java");
		book.setBookPublications("sunmicrosystems");
		boolean status = service.addBook(book);
		Assertions.assertTrue(status);
	}

	@Test
	public void testAddBook1() {
		Book book = new Book();
		book.setBookId(989898);
		book.setBookName("java");
		book.setAuthorName("gossling");
		book.setCategory("java");
		book.setBookPublications("sunmicrosystems");
		boolean status1 = service.addBook(book);
		Assertions.assertTrue(status1);
	}

	@Test
	public void testRegister() {
		Admin a = new Admin();
		a.setaId(989898);
		a.setaName("ghi");
		a.setaEmail("ghi@gmail.com");
		a.setaPassword("ghi@123");
		boolean status = service.register(a);
		Assertions.assertTrue(status);
	}

	@Test
	public void testRegister1() {
		Admin a = new Admin();
		a.setaId(989898);
		a.setaName("ghi");
		a.setaEmail("ghi@gmail.com");
		a.setaPassword("ghi@123");
		boolean status1 = service.register(a);
		Assertions.assertTrue(status1);
	}

	@Test
	public void testRemoveBook() {
		Book b = new Book();
		b.setBookId(989898);
		b.setBookName("java");
		b.setAuthorName("gosling");
		b.setCategory("java");
		b.setBookPublications("sunmicrosystems");
		boolean status = service.removeBook(989898);
		Assertions.assertTrue(status);
	}

	@Test
	public void testRemoveBook1() {
		Book b = new Book();
		b.setBookId(989898);
		b.setBookName("java");
		b.setAuthorName("gosling");
		b.setCategory("java");
		b.setBookPublications("sunmicrosystems");
		boolean status1 = service.removeBook(989898);
		Assertions.assertTrue(status1);
	}

	@Test
	public void testUpdateBook() {
		Book b = new Book();
		b.setBookId(11111);
		b.setBookName("hibernate");
		b.setAuthorName("rahul");
		b.setCategory("aptitude");
		b.setBookPublications("sia");
		boolean status = service.addBook(b);
		Assertions.assertTrue(status);
	}

	@Test
	public void testUpdateBook1() {
		Book b = new Book();
		b.setBookId(11111);
		b.setBookName("hibernate");
		b.setAuthorName("rahul");
		b.setCategory("aptitude");
		b.setBookPublications("sia");
		boolean status1 = service.addBook(b);
		Assertions.assertTrue(status1);
	}

	@Test
	public void testAuth() {
		Admin status = service.auth("ghi@gmail.com", "ghi@123");
		Assertions.assertNotNull(status);
	}

	@Test
	public void testAuthenticateBook() {
		Admin status = service.auth("ghi@gmail.com", "ghi@123");
		Assertions.assertNotNull(status);
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

	@Test
	public void testGetBooks() {
		List<Book> info = service.getBooks();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testGetBooks1() {
		List<Book> info1 = service.getBooks();
		Assertions.assertNotNull(info1);
	}

	@Test
	public void testShowUsera() {
		List<User> info = service.showUsers();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowUsers1() {
		List<User> info1 = service.showUsers();
		Assertions.assertNotNull(info1);
	}

	@Test
	public void testShowRequests() {
		List<Request> info = service.showRequests();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowRequests1() {
		List<Request> info1 = service.showRequests();
		Assertions.assertNotNull(info1);
	}

}
