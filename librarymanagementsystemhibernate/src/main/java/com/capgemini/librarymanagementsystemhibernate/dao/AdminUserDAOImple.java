package com.capgemini.librarymanagementsystemhibernate.dao;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.capgemini.librarymanagementsystemhibernate.dto.Book;
import com.capgemini.librarymanagementsystemhibernate.dto.User;
import com.capgemini.librarymanagementsystemhibernate.exception.LMSException;

public class AdminUserDAOImple implements AdminUserDAO {
	
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;
	@Override
	public boolean register(User user) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(user);
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
	public User authUser(String email, String password) {
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
	public List<Book> searchBookById(int bId) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from Book b where b.bId=:bId";
			TypedQuery<Book> query = manager.createQuery(jpql, Book.class);
			query.setParameter("bId", bId);
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
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
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
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
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
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select u from User u where u.email=:email and u.role=:role and u.password=:password";
			TypedQuery<User> query = manager.createQuery(jpql,User.class);
			query.setParameter("email", email);
			query.setParameter("role", role);
			query.setParameter("password", password);
			User rs = query.getSingleResult();
			if(rs != null) {
				User record = manager.find(User.class,email);
				record.setPassword(newPassword);
				transaction.commit();
				return true;			
			}else {
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
