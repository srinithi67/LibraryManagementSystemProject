package com.capgemini.librarymanagementsystemspringrest;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.librarymanagementsystemspringrest.dao.AdminDAO;
import com.capgemini.librarymanagementsystemspringrest.dto.Book;
import com.capgemini.librarymanagementsystemspringrest.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemspringrest.dto.RequestDetails;
import com.capgemini.librarymanagementsystemspringrest.dto.User;

public class AdminDAOTest {
	
	@Autowired
	private AdminDAO dao;
	
	@Test
	public void testAddBook() {
		Book book = new Book();
		book.setBookId(1);
		book.setBookName("JDBC");
		book.setAuthorName("James");
		book.setCategory("Java");
		book.setBookPublications("Sia");
		boolean status = dao.addBook(book);
		Assertions.assertTrue(status);
	}

	@Test
	public void testAddBooks() {
		Book books = new Book();
		books.setBookId(1);
		books.setBookName("JDBC");
		books.setAuthorName("James");
		books.setCategory("Java");
		books.setBookPublications("Sia");
		boolean check = dao.addBook(books);
		Assertions.assertTrue(check);
	}

	@Test
	public void testUpdateBook() {
		Book book1 = new Book();
		book1.setBookId(1);
		book1.setBookName("JDBC");
		book1.setAuthorName("williams");
		book1.setCategory("java");
		book1.setBookPublications("Sia");
		boolean msg = dao.updateBook(book1);
		Assertions.assertTrue(msg);

	}

	@Test
	public void testUpdateBooks() {
		Book book = new Book();
		book.setBookId(1);
		book.setBookName("JDBC");
		book.setAuthorName("williams");
		book.setCategory("java");
		book.setBookPublications("Sia");
		boolean check = dao.updateBook(book);
		Assertions.assertTrue(check);
	}

	@Test
	public void testRemoveBook() {
		Book b = new Book();
		b.setBookId(1);
		b.setBookName("JDBC");
		b.setAuthorName("James");
		b.setCategory("Java");
		b.setBookPublications("Sia");
		boolean status = dao.removeBook(1);
		Assertions.assertTrue(status);

	}

	@Test
	public void testRemoveBook1() {
		Book b1 = new Book();
		b1.setBookId(1);
		b1.setBookName("JDBC");
		b1.setAuthorName("James");
		b1.setCategory("Java");
		b1.setBookPublications("Sia");
		boolean check = dao.removeBook(1);
		Assertions.assertTrue(check);

	}

	@Test
	public void testBookIssue() {
		BookIssueDetails bookDetails = new BookIssueDetails();
		bookDetails.setbId(975310);
		bookDetails.setuId(111111);
		bookDetails.setIssueDate(null);
		bookDetails.setReturnDate(null);
		boolean msg = dao.issueBook(975310, 111111);
		Assertions.assertTrue(msg);
	}

	@Test
	public void testBookIssue1() {
		BookIssueDetails bookDetails = new BookIssueDetails();
		bookDetails.setbId(2);
		bookDetails.setuId(111111);
		bookDetails.setIssueDate(null);
		bookDetails.setReturnDate(null);
		boolean msg = dao.issueBook(975310, 111111);
		Assertions.assertTrue(msg);
	}

	@Test
	public void testShowRequests() {
		List<RequestDetails> msg = dao.showRequests();
		Assertions.assertNotNull(msg);

	}

	@Test
	public void testShowRequests1() {
		List<RequestDetails> msg1 = dao.showRequests();
		Assertions.assertNotNull(msg1);

	}

	@Test
	public void testShowIssuedBooks() {
		List<BookIssueDetails> book = dao.showIssuedBooks();
		Assertions.assertNotNull(book);
	}

	@Test
	public void testShowIssuedBooks1() {
		List<BookIssueDetails> book1 = dao.showIssuedBooks();
		Assertions.assertNotNull(book1);
	}

	@Test
	public void testShowUsers() {
		List<User> user = dao.showUsers();
		Assertions.assertNotNull(user);
	}

	@Test
	public void testShowUsers1() {
		List<User> user1 = dao.showUsers();
		Assertions.assertNotNull(user1);
	}


}
