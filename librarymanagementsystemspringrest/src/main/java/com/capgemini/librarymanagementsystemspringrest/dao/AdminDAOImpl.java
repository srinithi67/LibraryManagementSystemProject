package com.capgemini.librarymanagementsystemspringrest.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagementsystemspringrest.dto.Book;
import com.capgemini.librarymanagementsystemspringrest.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemspringrest.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemspringrest.dto.RequestDetails;
import com.capgemini.librarymanagementsystemspringrest.dto.User;
import com.capgemini.librarymanagementsystemspringrest.exception.LMSException;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@PersistenceUnit
	private EntityManagerFactory factory;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@Override
	public boolean addBook(Book book) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(book);
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public boolean removeBook(int bookId) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Book record = manager.find(Book.class, bookId);
			manager.remove(record);
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}

	}

	@Override
	public boolean updateBook(Book book) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Book record = manager.find(Book.class, book.getBookId());
			record.setBookName(book.getBookName());
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}

	}

	@Override
	public boolean issueBook(int bookId, int uId) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from Book b where b.bookId=:bookId";
			TypedQuery<Book> query = manager.createQuery(jpql, Book.class);
			query.setParameter("bookId", bookId);
			Book rs = query.getSingleResult();
			if (rs != null) {
				String jpql1 = "select r from RequestDetails r where r.uId=:uId and r.bId=:bId";
				TypedQuery<RequestDetails> query1 = manager.createQuery(jpql1, RequestDetails.class);
				//
				query1.setParameter("uId", uId);
				query1.setParameter("bookId", bookId);
				List<RequestDetails> rs1 = query1.getResultList();
				if (!rs1.isEmpty() && rs1 != null) {
					transaction.begin();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					String issueDate = sdf.format(cal.getTime());
					cal.add(Calendar.DAY_OF_MONTH, 7);
					String returnDate = sdf.format(cal.getTime());
					BookIssueDetails issueBook = new BookIssueDetails();
					issueBook.setuId(uId);
					issueBook.setbId(bookId);
					issueBook.setIssueDate(java.sql.Date.valueOf(issueDate));
					issueBook.setReturnDate(java.sql.Date.valueOf(returnDate));
					manager.persist(issueBook);
					transaction.commit();
					if (!rs1.isEmpty() && rs1 != null) {
						transaction.begin();
						Query bookName = manager.createQuery("select b.bookName from Book b where b.bookId=:bookId");
						bookName.setParameter("bookId", bookId);
						List book = bookName.getResultList();
						BorrowedBooks borrowedBooks = new BorrowedBooks();
						//
						borrowedBooks.setuId(uId);
						borrowedBooks.setbId(bookId);
						borrowedBooks.setBookName(book.get(0).toString());
						manager.persist(borrowedBooks);
						transaction.commit();
						return true;
					} else {
						throw new LMSException("Book Not issued");
					}
				} else {
					throw new LMSException("The respective user have not placed any request");
				}
			} else {
				throw new LMSException("There is no book exist with bookId" + bookId);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}

	}

	@Override
	public List<RequestDetails> showRequests() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select r from RequestDetails r";
		TypedQuery<RequestDetails> query = manager.createQuery(jpql, RequestDetails.class);
		List<RequestDetails> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;

	}

	@Override
	public List<BookIssueDetails> showIssuedBooks() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from BookIssueDetails b";
		TypedQuery<BookIssueDetails> query = manager.createQuery(jpql, BookIssueDetails.class);
		List<BookIssueDetails> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;

	}

	@Override
	public List<User> showUsers() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select u from User u";
		TypedQuery<User> query = manager.createQuery(jpql, User.class);
		List<User> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;

	}

}
