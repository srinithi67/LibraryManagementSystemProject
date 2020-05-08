package com.capgemini.librarymanagementsystemspringrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.librarymanagementsystemspringrest.dto.Book;
import com.capgemini.librarymanagementsystemspringrest.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemspringrest.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemspringrest.dto.RequestDetails;
import com.capgemini.librarymanagementsystemspringrest.dto.Response;
import com.capgemini.librarymanagementsystemspringrest.dto.User;
import com.capgemini.librarymanagementsystemspringrest.service.AdminService;
import com.capgemini.librarymanagementsystemspringrest.service.AdminUserService;
import com.capgemini.librarymanagementsystemspringrest.service.UserService;

@RestController
public class LMSController {

	@Autowired
	private AdminService service1;;
	private AdminUserService service2;
	private UserService service3;

	@PostMapping(path = "/addUser", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })

	public Response addUser(@RequestBody User bean) {
		boolean isAdded = service2.register(bean);
		Response response = new Response();
		if (isAdded) {
			response.setMessage("record  is inserted");
		} else {
			response.setError(true);
			response.setMessage("unable to add");
		}
		return response;
	}

	@PostMapping(path = "/addBook", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public Response addBook(@RequestBody Book bean) {
		boolean isBookAdded = service1.addBook(bean);
		Response response = new Response();
		if (isBookAdded) {
			response.setMessage("Book added succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be added");
		}
		return response;

	}

	@PutMapping(path = "/bookUpdate", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public Response updateBook(@RequestBody Book bean) {
		boolean isBookUpdated = service1.updateBook(bean);
		Response response = new Response();
		if (isBookUpdated) {
			response.setMessage("Book updated succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be updated");
		}
		return response;

	}

	@PostMapping(path = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public Response authentication(@RequestBody String email, String password) {
		User userLogin = service2.auth(email, password);
		Response response = new Response();
		if (userLogin != null) {
			response.setMessage("Login succesfully");
		} else {
			response.setError(true);
			response.setMessage("Invalid credentials,Please try again");
		}
		return response;
	}

	@DeleteMapping(path = "/removeBook/{bookId} ", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Response deleteBook(@PathVariable(name = "bookId") int bookId) {
		boolean isBookDeleted = service1.removeBook(bookId);
		Response response = new Response();
		if (isBookDeleted) {
			response.setMessage("Book deleted succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book not deleted");
		}
		return response;
	}

	@GetMapping(path = "/BooksInfo", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Response getBookInfo() {
		List<Book> getInfo = service2.getBooksInfo();
		Response response = new Response();
		if (getInfo != null && !getInfo.isEmpty()) {
			response.setMessage("Books found");
			response.setBook1(getInfo);
		} else {
			response.setError(true);
			response.setMessage("They are no books in the Library");
		}
		return response;
	}

	@GetMapping(path = "/BooksByName", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Response getBookByName(String bookTitle) {
		List<Book> bean = service2.searchBookByTitle(bookTitle);
		Response response = new Response();
		if (bean != null && !bean.isEmpty()) {
			response.setMessage("Books found");
			response.setBook1(bean);
		} else {
			response.setError(true);
			response.setMessage("They are no books in the Library");
		}
		return response;
	}

	@GetMapping(path = "/BooksByAuthor", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Response getBookByAuthor(String author) {
		List<Book> bean = service2.searchBookByAuthor(author);
		Response response = new Response();
		if (bean != null && !bean.isEmpty()) {
			response.setMessage("Books found");
			response.setBook1(bean);
		} else {
			response.setError(true);
			response.setMessage("They are no books in the Library");
		}
		return response;
	}

	@GetMapping(path = "/BooksById", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Response getBookById(int bId) {
		List<Book> bean = service2.searchBookById(bId);
		Response response = new Response();
		if (bean != null && !bean.isEmpty()) {
			response.setMessage("Books found");
			response.setBook1(bean);
		} else {
			response.setError(true);
			response.setMessage("They are no books in the Library");
		}
		return response;
	}

	@PostMapping(path = "/bookIssue", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public Response issueBook(@RequestBody int userId, int bookId) {
		boolean isBookIssued = service1.issueBook(userId, bookId);
		Response response = new Response();
		if (isBookIssued) {
			response.setMessage("Book issued succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be issued");
		}
		return response;
	}

	@PostMapping(path = "/returnBook", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public Response returnBook(@RequestBody int bId, int uId, String status) {
		boolean isBookReturned = service3.returnBook(bId, uId, status);
		Response response = new Response();
		if (isBookReturned) {
			response.setMessage("Book returned succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be returned");
		}
		return response;
	}

	@PostMapping(path = "/requestBook", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public Response requestBook(@RequestBody int bId, int uId) {
		boolean isBookRequested = service3.request(bId, uId);
		Response response = new Response();
		if (isBookRequested) {
			response.setMessage("Book requested succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book cannot be requested");
		}
		return response;
	}

	@GetMapping(path = "/showRequests", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Response showRequests() {
		List<RequestDetails> requestDetails = service1.showRequests();
		Response response = new Response();

		if (requestDetails != null && !requestDetails.isEmpty()) {
			response.setRequestDetails1(requestDetails);
		} else {
			response.setError(true);
			response.setMessage("They are no requests");
		}
		return response;
	}

	@GetMapping(path = "/showIssuedBooks", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Response showIssuedBooks() {
		List<BookIssueDetails> issueList = service1.showIssuedBooks();
		Response response = new Response();

		if (issueList != null && !issueList.isEmpty()) {
			response.setBookIssueDetails1(issueList);
		} else {
			response.setError(true);
			response.setMessage("No Books are Issued");
		}
		return response;
	}

	@GetMapping(path = "/showUsers", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Response showUsers() {
		List<User> usersList = service1.showUsers();
		Response response = new Response();

		if (usersList != null && !usersList.isEmpty()) {
			response.setUser1(usersList);
		} else {
			response.setError(true);
			response.setMessage("They are no Users");
		}
		return response;
	}

	@PutMapping(path = "/updatePassword", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public Response updatePassord(int id, String password, String newPassword, String role) {
		boolean isUpdated = service2.updatePassword(id, password, newPassword, role);
		Response response = new Response();

		if (isUpdated) {
			response.setMessage("Password updated successfully");
		} else {
			response.setError(true);
			response.setMessage("Password is not updated");
		}
		return response;
	}

	@GetMapping(path = "/getBorrowedBooks", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Response getBorrowedBooks(@RequestBody int id) {
		List<BorrowedBooks> borrowed = service3.borrowedBook(id);
		Response response = new Response();

		if (borrowed != null && !borrowed.isEmpty()) {
			response.setBorrowedBooks1(borrowed);
		} else {
			response.setError(true);
			response.setMessage("There are no borrowed  books");
		}
		return response;
	}

}
