package com.capgemini.librarymanagementsystemjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.Book;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.User;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemjdbc.utility.Utility;
import com.mysql.jdbc.Statement;

public class UserDAOImple implements UserDAO {

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
	public boolean removeBook(int bId) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.removeBookQuery);) {
			pstmt.setInt(1, bId);
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
	public boolean bookIssue(int bId, int uId) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.issueBookQuery1);) {
			pstmt.setInt(1, bId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				try (PreparedStatement pstmt1 = conn.prepareStatement(QueryMapper.issueBookQuery2);) {
					pstmt1.setInt(1, uId);
					pstmt1.setInt(2, bId);
					pstmt1.setInt(3, uId);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						try (PreparedStatement pstmt2 = conn.prepareStatement(QueryMapper.issueBookQuery3);) {
							pstmt1.setInt(1, bId);
							pstmt1.setInt(2, uId);
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
									pstmt3.setInt(1, uId);
									pstmt3.setInt(2, bId);
									pstmt3.setInt(3, uId);
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
				throw new LMSException("There is no book exist with bookId" + bId);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean request(int uId, int bId) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.registerQuery);) {
			pstmt.setInt(1, bId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				try (PreparedStatement pstmt1 = conn.prepareStatement(QueryMapper.requestBookQuery2);) {
					pstmt1.setInt(1, uId);
					pstmt1.setInt(2, bId);
					pstmt1.setInt(3, uId);
					rs = pstmt1.executeQuery();
					if (rs.next()) {
						int isBookExists = rs.getInt("uId");
						if (isBookExists == 0) {
							try (PreparedStatement pstmt2 = conn.prepareStatement(QueryMapper.requestBookQuery3);) {
								pstmt2.setInt(1, uId);
								rs = pstmt2.executeQuery();
								if (rs.next()) {
									int noOfBooksBorrowed = rs.getInt("uId");
									if (noOfBooksBorrowed < 3) {
										try (PreparedStatement pstmt3 = conn
												.prepareStatement(QueryMapper.requestBookQuery4);) {
											pstmt3.setInt(1, uId);
											pstmt3.setInt(2, uId);
											pstmt3.setInt(3, bId);
											pstmt3.setInt(4, bId);
											pstmt3.setInt(5, uId);
											int count = pstmt3.executeUpdate();
											if (count != 0) {
												return true;
											} else {
												return false;
											}
										}
									} else {
										throw new LMSException("no Of books limit has crossed");
									}
								} else {
									throw new LMSException("no of books limit has crossed");
								}
							}
						} else {
							throw new LMSException("You have already borrowed the requested book");
						}
					} else {
						throw new LMSException("You have already borrowed the requested book");
					}
				}

			} else {
				throw new LMSException("The book with requested id is not present");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public ArrayList<Book> searchBookByTitle(String bookName) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.searchBookByTitle);) {
			pstmt.setString(1, bookName);
			rs = pstmt.executeQuery();
			ArrayList<Book> beans = new ArrayList<Book>();
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
	public ArrayList<Book> searchBookByAuthor(String author) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.searchBookByAuthor);) {
			pstmt.setString(1, author);
			rs = pstmt.executeQuery();
			ArrayList<Book> beans = new ArrayList<Book>();
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
	public ArrayList<Book> getBooksInfo() {
		conn = Utility.getConnection();
		try (Statement stmt = (Statement) conn.createStatement();) {
			rs = stmt.executeQuery(QueryMapper.getBooksInfoQuery);
			ArrayList<Book> beans = new ArrayList<Book>();
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
	public boolean returnBook(int bId, int uId, String status) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.returnBookQuery1);) {
			pstmt.setInt(1, bId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				try (PreparedStatement pstmt1 = conn.prepareStatement(QueryMapper.returnBookQuery2);) {
					pstmt1.setInt(1, bId);
					pstmt1.setInt(2, uId);
					rs = pstmt1.executeQuery();
					if (rs.next()) {
						Date issueDate = rs.getDate("issueDate");
						Calendar cal = Calendar.getInstance();
						Date returnDate = cal.getTime();
						long difference = issueDate.getTime() - returnDate.getTime();
						float daysBetween = (difference / (1000 * 60 * 60 * 24));
						if (daysBetween > 7) {
							float fine = daysBetween * 5;
							System.out.println("The user has to pay the fine of the respective book of Rs:" + fine);
							if (status == "yes") {
								try (PreparedStatement pstmt2 = conn.prepareStatement(QueryMapper.returnBookQuery3);) {
									pstmt2.setInt(1, bId);
									pstmt2.setInt(2, uId);
									int count = pstmt2.executeUpdate();
									if (count != 0) {
										try (PreparedStatement pstmt3 = conn
												.prepareStatement(QueryMapper.returnBookQuery4);) {
											pstmt3.setInt(1, bId);
											pstmt3.setInt(2, uId);
											int isReturned = pstmt3.executeUpdate();
											if (isReturned != 0) {
												try (PreparedStatement pstmt4 = conn
														.prepareStatement(QueryMapper.returnBookQuery5);) {
													pstmt4.setInt(1, bId);
													pstmt4.setInt(2, uId);
													int isRequestDeleted = pstmt4.executeUpdate();
													if (isRequestDeleted != 0) {
														return true;
													} else {
														return false;
													}
												}
											} else {
												return false;
											}
										}
									} else {
										return false;
									}
								}
							} else {
								throw new LMSException("The User has to pay fine for delaying book return");
							}
						} else {
							try (PreparedStatement pstmt5 = conn.prepareStatement(QueryMapper.returnBookQuery3);) {
								pstmt5.setInt(1, bId);
								pstmt5.setInt(2, uId);
								int count = pstmt5.executeUpdate();
								if (count != 0) {
									try (PreparedStatement pstmt2 = conn
											.prepareStatement(QueryMapper.returnBookQuery4);) {
										pstmt2.setInt(1, bId);
										pstmt2.setInt(2, uId);
										int isReturned = pstmt2.executeUpdate();
										if (isReturned != 0) {
											try (PreparedStatement pstmt3 = conn
													.prepareStatement(QueryMapper.returnBookQuery5);) {
												pstmt3.setInt(1, bId);
												pstmt3.setInt(2, uId);
												int isRequestDeleted = pstmt3.executeUpdate();
												if (isRequestDeleted != 0) {
													return true;
												} else {
													return false;
												}
											}
										} else {
											return false;
										}
									}
								} else {
									return false;
								}
							}
						}
					} else {
						throw new LMSException("This respective user hasn't borrowed any book");
					}
				}

			} else {
				throw new LMSException("No book exist with bookId" + bId);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public ArrayList<BookIssueDetails> bookHistoryDetails(int uId) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.bookHistoryDetailsQuery);) {
			pstmt.setInt(1, uId);
			rs = pstmt.executeQuery();
			ArrayList<BookIssueDetails> beans = new ArrayList<BookIssueDetails>();
			while (rs.next()) {
				BookIssueDetails issueDetails = new BookIssueDetails();
				issueDetails.setUserId(rs.getInt("uId"));
				beans.add(issueDetails);
			}
			return beans;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<BorrowedBooks> borrowedBook(int uId) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.borrowedBookQuery);) {
			pstmt.setInt(1, uId);
			rs = pstmt.executeQuery();
			ArrayList<BorrowedBooks> beans = new ArrayList<BorrowedBooks>();
			while (rs.next()) {
				BorrowedBooks listOfbooksBorrowed = new BorrowedBooks();
				listOfbooksBorrowed.setuId(rs.getInt("uId"));
				listOfbooksBorrowed.setbId(rs.getInt("bId"));
				listOfbooksBorrowed.setEmail(rs.getString("email"));
				beans.add(listOfbooksBorrowed);
			}
			return beans;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<Book> searchBookById(int bookId) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.searchBookByIdQuery);) {
			pstmt.setInt(1, bookId);
			rs = pstmt.executeQuery();
			ArrayList<Book> beans = new ArrayList<Book>();
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
	public ArrayList<RequestDetails> showRequests() {
		conn = Utility.getConnection();

		try (Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapper.showRequestsQuery);) {
			ArrayList<RequestDetails> beans = new ArrayList<RequestDetails>();
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
	public ArrayList<BookIssueDetails> showIssuedBooks() {
		conn = Utility.getConnection();

		try (Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapper.showIssuedBooksQuery);) {
			ArrayList<BookIssueDetails> beans = new ArrayList<BookIssueDetails>();
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
	public ArrayList<User> showUsers() {
		conn = Utility.getConnection();

		try (Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapper.showUsersQuery);) {
			ArrayList<User> beans = new ArrayList<User>();
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
