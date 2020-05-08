package com.capgemini.librarymanagementsystemspringrest.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.capgemini.librarymanagementsystemspringrest.exception.LMSException;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@PersistenceUnit
	private EntityManagerFactory factory ;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;

	@Override
	public boolean request(int uId, int bId) {
		int count=0;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from Book b where b.bookId=:bookId";
			TypedQuery<Book> query = manager.createQuery(jpql,Book.class);
			query.setParameter("bookId", bId);
			List rs = query.getResultList();
			if(rs != null) {
				String jpql1 = "select b from BorrowedBooks b where b.uId=:uId and b.bId=:bId";
				TypedQuery<BorrowedBooks> query1 = (TypedQuery<BorrowedBooks>) manager.createQuery(jpql1,BorrowedBooks.class);
				//
				query1.setParameter("userId", uId);
				query1.setParameter("bookId", bId);
				List rs1 = query1.getResultList();
				if( rs1.isEmpty() || rs1==null ) {
					String jpql2 = "select b from BookIssueDetails b where b.uId=:uId";
					TypedQuery<BookIssueDetails> query2 = (TypedQuery<BookIssueDetails>) manager.createQuery(jpql2,BookIssueDetails.class);
					query2.setParameter("userId", uId);
					List<BookIssueDetails> rs2 = query2.getResultList();
					for(BookIssueDetails p : rs2) {
						noOfBooks = count++;
					}
					if(noOfBooks<3) {
						Query bookName = manager.createQuery("select b.bookName from Book b where b.bookId=:bookId");
						bookName.setParameter("bookId", bId);
						List book = bookName.getResultList();
						Query email = manager.createQuery("select u.email from User u where u.uId=:uId");
						email.setParameter("user_Id", uId);
						List userEmail = email.getResultList();
						transaction.begin();
						RequestDetails request = new RequestDetails();
						//
						request.setuId(uId);
						request.setbId(bId);
						request.setEmail(userEmail.get(0).toString());
						request.setBookName(book.get(0).toString());
						manager.persist(request);
						transaction.commit();
						return true;

					}else {
						throw new LMSException("You have crossed the book limit");
					}
				}else {
					throw new LMSException("You have already borrowed the requested book");
				}
			}else {
				throw new LMSException("The book with requested id is not present");
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
	public List<BorrowedBooks> borrowedBook(int uId) {
		
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BorrowedBooks b where b.uId=:uId";
			TypedQuery<BorrowedBooks> query = manager.createQuery(jpql,BorrowedBooks.class);
			//
			query.setParameter("uId", uId);
			List<BorrowedBooks> recordList = query.getResultList();
			return recordList; 
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			manager.close();
			factory.close();
		}
		
	}

	@Override
	public boolean returnBook(int bId, int uId, String status) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from Book b where b.bookId=:bookId";
			TypedQuery<Book> query = manager.createQuery(jpql,Book.class);
			query.setParameter("bId", bId);
			Book rs = query.getSingleResult();
			if(rs != null) {
				String jpql1 = "select b from BookIssueDetails b where b.bId=:bId and b.uId=:uId ";
				TypedQuery<BookIssueDetails> query1 = manager.createQuery(jpql1,BookIssueDetails.class);
				//
				query1.setParameter("bId", bId);
				query1.setParameter("uId", uId);
				BookIssueDetails rs1 = query1.getSingleResult();
				if(rs1 != null) {
					Date issueDate = rs1.getIssueDate();
					Calendar cal = Calendar.getInstance();
					Date returnDate = cal.getTime();
					long difference = issueDate.getTime() - returnDate.getTime();
					float daysBetween = (difference / (1000*60*60*24));
					if(daysBetween>7.0) {
						//transaction.begin();
						float fine = daysBetween*5;
						System.out.println("The user has to pay the fine of the respective book of Rs:"+fine);
						if(status=="yes") {
							transaction.begin();
							
							manager.remove(rs1);
							transaction.commit();


							transaction.begin();
							String jpql3 = "select b from BorrowedBooks b  where b.bId=:bId and b.uId=:uId";
							Query query3 = manager.createQuery(jpql3);
							query3.setParameter("bId", bId);
							query3.setParameter("uId", uId);
							BorrowedBooks bbb = (BorrowedBooks) query3.getSingleResult();
							//int bbb_Id = bbb.getId();
							manager.remove(bbb);
							transaction.commit();

							transaction.begin();
							String jpql4 = "select r from RequestsDetails r where r.bId=:bId and r.uId=:uId";
							Query query4 = manager.createQuery(jpql4);
							query4.setParameter("bId", bId);
							query4.setParameter("uId", uId);
							RequestDetails rdb = (RequestDetails) query4.getSingleResult();
							//int rdb_Id = rdb.getId();
							manager.remove(rdb);
							transaction.commit();
							return true;
						}else {
							throw new LMSException("The User has to pay fine for delaying book return");
						}
					}else {
						transaction.begin();
						
						manager.remove(rs1);
						transaction.commit();


						transaction.begin();
						String jpql3 = "select b from BorrowedBooks b  where b.bId=:bId and b.uId=:uId";
						Query query3 = manager.createQuery(jpql3);
						query3.setParameter("bId", bId);
						//
						query3.setParameter("uId", uId);
						BorrowedBooks bbb = (BorrowedBooks) query3.getSingleResult();
						//int bbb_Id = bbb.getId();
						manager.remove(bbb);
						transaction.commit();

						transaction.begin();
						String jpql4 = "select r from RequestDetails r where r.bId=:bId and r.uId=:uId";
						Query query4 = manager.createQuery(jpql4);
						query4.setParameter("bId", bId);
						//
						query4.setParameter("uId", uId);
						RequestDetails rdb = (RequestDetails) query4.getSingleResult();
						//int rdb_Id = rdb.getId();
						manager.remove(rdb);
						transaction.commit();
						return true;
					}

				}else {
					throw new LMSException("This respective user hasn't borrowed any book");
				}
			}else {
				throw new LMSException("book doesnt exist");
			}

		}catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		}finally {
			manager.close();
			factory.close();
		}
	}
		
	

	@Override
	public List<Integer> bookHistoryDetails(int uId) {
		int count=0;
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from BookIssueDetails b";
		TypedQuery<BookIssueDetails> query = manager.createQuery(jpql,BookIssueDetails.class);
		List<BookIssueDetails> recordList = query.getResultList();
		for(BookIssueDetails p : recordList) {
			noOfBooks = count++;
		}
		List<Integer> list = new ArrayList<Integer>();
		list.add(noOfBooks);
		manager.close();
		factory.close();
		return list;
		
	}
	

}
