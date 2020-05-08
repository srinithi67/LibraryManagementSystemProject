package com.capgemini.librarymanagementsystemjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.Book;
import com.capgemini.librarymanagementsystemjdbc.dto.User;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemjdbc.utility.Utility;
import com.mysql.jdbc.Statement;

public class AdminUserDAOImple implements AdminUserDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement stmt = null;

	@Override
	public boolean registerUser(User user) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.registerQuery);) {
			pstmt.setInt(1, user.getuId());
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPassword());
			pstmt.setLong(6, user.getMobile());
			pstmt.setString(7, user.getRole());
			int count = pstmt.executeUpdate();
			if (user.getEmail().isEmpty() && count == 0) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public User authUser(String email, String password) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.loginQuery);) {

			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				User bean = new User();
				bean.setuId(rs.getInt("uId"));
				bean.setFirstName(rs.getString("firstName"));
				bean.setLastName(rs.getString("lastName"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setMobile(rs.getLong("mobile"));
				bean.setRole(rs.getString("role"));
				return bean;
			} else {
				return null;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Book> searchBookById(int bookId) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.searchBookByIdQuery);) {
			pstmt.setInt(1, bookId);
			rs = pstmt.executeQuery();
			List<Book> beans = new ArrayList<Book>();
			while (rs.next()) {
				Book bean = new Book();
				bean.setBookId(rs.getInt("bookId"));
				bean.setBookName(rs.getString("bookName"));
				bean.setAuthorName(rs.getString("authorName"));
				bean.setCategory(rs.getString("category"));
				bean.setBookPublications(rs.getString("bookPublications"));
				// bean.setCopies(rs.getInt("copies"));
				beans.add(bean);
			}
			return beans;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Book> searchBookByTitle(String bookName) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.searchBookByTitle);) {
			pstmt.setString(1, bookName);
			rs = pstmt.executeQuery();
			List<Book> beans = new ArrayList<Book>();
			while (rs.next()) {
				Book bean = new Book();
				bean.setBookId(rs.getInt("bookId"));
				bean.setBookName(rs.getString("bookName"));
				bean.setAuthorName(rs.getString("authorName"));
				bean.setCategory(rs.getString("category"));
				bean.setBookPublications(rs.getString("bookPublications"));
				// bean.setCopies(rs.getInt("copies"));
				beans.add(bean);
			}
			return beans;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Book> searchBookByAuthor(String author) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.searchBookByAuthor);) {
			pstmt.setString(1, author);
			rs = pstmt.executeQuery();
			List<Book> beans = new ArrayList<Book>();
			while (rs.next()) {
				Book bean = new Book();
				bean.setBookId(rs.getInt("bookId"));
				bean.setBookName(rs.getString("bookName"));
				bean.setAuthorName(rs.getString("authorName"));
				bean.setCategory(rs.getString("category"));
				bean.setBookPublications(rs.getString("bookPublications"));
				// bean.setCopies(rs.getInt("copies"));
				beans.add(bean);
			}
			return beans;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Book> getBooksInfo() {
		conn = Utility.getConnection();
		try (Statement stmt = (Statement) conn.createStatement();) {
			rs = stmt.executeQuery(QueryMapper.getBooksInfoQuery);
			List<Book> beans = new ArrayList<Book>();
			while (rs.next()) {
				Book bean = new Book();
				bean.setBookId(rs.getInt("bookId"));
				bean.setBookName(rs.getString("bookName"));
				bean.setAuthorName(rs.getString("authorName"));
				bean.setCategory(rs.getString("category"));
				bean.setBookPublications(rs.getString("bookPublications"));
				// bean.setCopies(rs.getInt("copies"));
				beans.add(bean);
			}
			return beans;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		conn = Utility.getConnection();
		try (PreparedStatement pst = conn.prepareStatement(QueryMapper.updatePasswordQuery1)) {
			pst.setString(1, email);
			pst.setString(2, role);
			rs = pst.executeQuery();
			if (rs.next()) {
				try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.updatePasswordQuery2);) {
					pstmt.setString(1, newPassword);
					pstmt.setString(2, email);
					pstmt.setString(3, password);
					int count = pstmt.executeUpdate();
					if (count != 0) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				throw new LMSException("User doesnt exist");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

}
