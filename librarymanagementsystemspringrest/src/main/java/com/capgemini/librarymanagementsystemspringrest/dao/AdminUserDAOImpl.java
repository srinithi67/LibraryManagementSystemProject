package com.capgemini.librarymanagementsystemspringrest.dao;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagementsystemspringrest.dto.Book;
import com.capgemini.librarymanagementsystemspringrest.dto.User;
import com.capgemini.librarymanagementsystemspringrest.exception.LMSException;

@Repository
public class AdminUserDAOImpl implements AdminUserDAO {
	@PersistenceUnit
	private EntityManagerFactory factory;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@Override
	public boolean register(User user) {
		boolean isRegistered = false;
		try {

			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(user);
			transaction.commit();
			isRegistered = true;
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			// return false;
		}
		return isRegistered;
	}

	@Override
	public User auth(String email, String password) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select u from User u where u.email=:email and u.password=:password";
			TypedQuery<User> query = manager.createQuery(jpql, User.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			User bean = query.getSingleResult();
			return bean;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<Book> searchBookById(int bookId) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from Book b where b.bookId=:bookId";
			TypedQuery<Book> query = manager.createQuery(jpql, Book.class);
			query.setParameter("bookId", bookId);
			List<Book> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<Book> searchBookByTitle(String bookName) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from Book b where b.bookName=:bookName";
			TypedQuery<Book> query = manager.createQuery(jpql, Book.class);
			query.setParameter("bookName", bookName);
			List<Book> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<Book> searchBookByAuthor(String authorName) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from Book b where b.authorName=:authorName";
			TypedQuery<Book> query = manager.createQuery(jpql, Book.class);
			query.setParameter("authorName", authorName);
			List<Book> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<Book> getBooksInfo() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from Book b";
		TypedQuery<Book> query = manager.createQuery(jpql, Book.class);
		List<Book> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select u from User u where u.uId=:uId and u.role=:role and u.password=:password";
			TypedQuery<User> query = manager.createQuery(jpql, User.class);
			query.setParameter("uId", id);
			query.setParameter("role", role);
			query.setParameter("password", password);
			User rs = query.getSingleResult();
			if (rs != null) {
				User record = manager.find(User.class, id);
				record.setPassword(newPassword);
				transaction.commit();
				return true;
			} else {
				throw new LMSException("User doesnt exist");
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
}
