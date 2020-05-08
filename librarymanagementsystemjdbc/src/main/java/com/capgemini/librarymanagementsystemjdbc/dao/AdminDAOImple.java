package com.capgemini.librarymanagementsystemjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.Book;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.User;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemjdbc.utility.Utility;
import com.mysql.jdbc.Statement;

public class AdminDAOImple implements AdminDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement stmt = null;

	@Override
	public boolean addBook(Book book) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.addBookQuery);) {
			pstmt.setInt(1, book.getBookId());
			pstmt.setString(2, book.getBookName());
			pstmt.setString(3, book.getAuthorName());
			pstmt.setString(4, book.getCategory());
			pstmt.setString(5, book.getBookPublications());
			// pstmt.setInt(6, book.getCopies());
			int count = pstmt.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean removeBook(int bookId) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.removeBookQuery);) {
			pstmt.setInt(1, bookId);
			int count = pstmt.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateBook(Book book) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.updateBookQuery);) {
			pstmt.setString(1, book.getBookName());
			pstmt.setInt(2, book.getBookId());
			int count = pstmt.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean bookIssue(int bookId, int userId) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.issueBookQuery1);) {
			pstmt.setInt(1, bookId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				try (PreparedStatement pstmt1 = conn.prepareStatement(QueryMapper.issueBookQuery2);) {
					pstmt1.setInt(1, userId);
					pstmt1.setInt(2, bookId);
					pstmt1.setInt(3, userId);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						try (PreparedStatement pstmt2 = conn.prepareStatement(QueryMapper.issueBookQuery3);) {
							pstmt1.setInt(1, bookId);
							pstmt1.setInt(2, userId);
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Calendar cal = Calendar.getInstance();
							String issueDate = sdf.format(cal.getTime());
							pstmt1.setDate(3, java.sql.Date.valueOf(issueDate));
							cal.add(Calendar.DAY_OF_MONTH, 7);
							String returnDate = sdf.format(cal.getTime());
							pstmt1.setDate(4, java.sql.Date.valueOf(returnDate));
							int count = pstmt2.executeUpdate();
							if (count != 0) {
								try (PreparedStatement pstmt3 = conn.prepareStatement(QueryMapper.issueBookQuery4);) {
									pstmt3.setInt(1, userId);
									pstmt3.setInt(2, bookId);
									pstmt3.setInt(3, userId);
									int isBorrowed = pstmt2.executeUpdate();
									if (isBorrowed != 0) {
										return true;
									} else {
										return false;
									}
								}
							} else {
								throw new LMSException("Book Not issued");
							}
						}
					} else {
						throw new LMSException("The respective user have not placed any request");
					}
				}
			} else {
				throw new LMSException("There is no book exist with bookId" + bookId);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public List<RequestDetails> showRequests() {
		conn = Utility.getConnection();

		try (Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapper.showRequestsQuery);) {
			List<RequestDetails> beans = new ArrayList<RequestDetails>();
			while (rs.next()) {
				RequestDetails bean = new RequestDetails();
				bean.setuId(rs.getInt("uId"));
				bean.setFullName(rs.getString("fullName"));
				bean.setbId(rs.getInt("bId"));
				bean.setBookName(rs.getString("bookName"));
				beans.add(bean);
			}
			return beans;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<BookIssueDetails> showIssuedBooks() {
		conn = Utility.getConnection();

		try (Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapper.showIssuedBooksQuery);) {
			List<BookIssueDetails> beans = new ArrayList<BookIssueDetails>();
			while (rs.next()) {
				BookIssueDetails bean = new BookIssueDetails();
				bean.setBookId(rs.getInt("bookId"));
				bean.setUserId(rs.getInt("userId"));
				bean.setIssueDate(rs.getDate("issueDate"));
				bean.setReturnDate(rs.getDate("returnDate"));
				beans.add(bean);
			}
			return beans;

		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<User> showUsers() {
		conn = Utility.getConnection();

		try (Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapper.showUsersQuery);) {
			List<User> beans = new ArrayList<User>();
			while (rs.next()) {
				User bean = new User();
				bean.setuId(rs.getInt("uId"));
				bean.setFirstName(rs.getString("firstName"));
				bean.setLastName(rs.getString("lastName"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setMobile(rs.getLong("mobile"));
				bean.setRole(rs.getString("role"));
				beans.add(bean);
			}
			return beans;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

}
