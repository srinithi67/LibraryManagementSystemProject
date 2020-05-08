package com.capgemini.librarymanagementsystemjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemjdbc.utility.Utility;
import com.mysql.jdbc.Statement;

public class UserDAOImple implements UserDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement stmt = null;

	@Override
	public boolean request(int uId, int bookId) {
conn = Utility.getConnection();
		
		try (PreparedStatement statement = conn.prepareStatement(QueryMapper.requestBookQuery1);) {
			statement.setInt(1, uId);
			statement.setInt(2, bookId);
			statement.setInt(3, uId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int isBookExists = rs.getInt("uId");
				if (isBookExists == 0) {
					try (PreparedStatement pstmt1 = conn.prepareStatement(QueryMapper.requestBookQuery2);) {
						pstmt1.setInt(1, uId);
						rs = pstmt1.executeQuery();
						if (rs.next()) {
							int noOfBooksBorrowed = rs.getInt("uId");
							if (noOfBooksBorrowed < 3) {
								try (PreparedStatement pstmt2 = conn
										.prepareStatement(QueryMapper.requestBookQuery3);) {
									pstmt2.setInt(1, uId);
									pstmt2.setInt(2, uId);
									pstmt2.setInt(3, bookId);
									pstmt2.setInt(4, bookId);
									pstmt2.setInt(5, uId);
									int count = pstmt2.executeUpdate();
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

		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
	public List<BookIssueDetails> bookHistoryDetails(int uId) {
		conn = Utility.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(QueryMapper.bookHistoryDetailsQuery);) {
			pstmt.setInt(1, uId);
			rs = pstmt.executeQuery();
			List<BookIssueDetails> beans = new ArrayList<BookIssueDetails>();
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
			List<BorrowedBooks> beans = new ArrayList<BorrowedBooks>();
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

}
