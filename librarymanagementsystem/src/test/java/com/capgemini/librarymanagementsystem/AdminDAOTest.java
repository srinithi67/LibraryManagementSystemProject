package com.capgemini.librarymanagementsystem;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dao.AdminDAO;
import com.capgemini.librarymanagementsystem.dao.AdminDAOImple;
import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public class AdminDAOTest {

	private AdminDAO dao = new AdminDAOImple();

	@Test
	public void testAddBook() {
		Book book = new Book();
		book.setBookId(989898);
		book.setBookName("java");
		book.setAuthorName("gossling");
		book.setCategory("java");
		book.setBookPublications("sunmicrosystems");
		boolean status = dao.addBook(book);
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
		boolean status1 = dao.addBook(book);
		Assertions.assertTrue(status1);
	}

	@Test
	public void testRegister() {
		Admin a = new Admin();
		a.setaId(989898);
		a.setaName("ghi");
		a.setaEmail("ghi@gmail.com");
		a.setaPassword("ghi@123");
		boolean status = dao.register(a);
		Assertions.assertTrue(status);
	}

	@Test
	public void testRegister1() {
		Admin a = new Admin();
		a.setaId(989898);
		a.setaName("ghi");
		a.setaEmail("ghi@gmail.com");
		a.setaPassword("ghi@123");
		boolean status1 = dao.register(a);
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
		boolean status = dao.removeBook(989898);
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
		boolean status1 = dao.removeBook(989898);
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
		boolean status = dao.addBook(b);
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
		boolean status1 = dao.addBook(b);
		Assertions.assertTrue(status1);
	}

	@Test
	public void testAuth() {
		Admin status = dao.auth("ghi@gmail.com", "ghi@123");
		Assertions.assertNotNull(status);
	}

	@Test
	public void testAuthenticateBook() {
		Admin status = dao.auth("ghi@gmail.com", "ghi@123");
		Assertions.assertNotNull(status);
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

	@Test
	public void testGetBooks() {
		List<Book> info = dao.getBooks();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testGetBooks1() {
		List<Book> info1 = dao.getBooks();
		Assertions.assertNotNull(info1);
	}

	@Test
	public void testShowUsera() {
		List<User> info = dao.showUsers();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowUsers1() {
		List<User> info1 = dao.showUsers();
		Assertions.assertNotNull(info1);
	}

	@Test
	public void testShowRequests() {
		List<Request> info = dao.showRequests();
		Assertions.assertNotNull(info);
	}

	@Test
	public void testShowRequests1() {
		List<Request> info1 = dao.showRequests();
		Assertions.assertNotNull(info1);
	}

}
