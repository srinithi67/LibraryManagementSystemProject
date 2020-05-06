package com.capgemini.librarymanagementsystemjdbc.dao;

public interface QueryMapper {
	String registerQuery = "insert into user values(?,?,?,?,?,?,?)";
	String loginQuery = "select * from user where email=? and password=?";
	String addBookQuery = "insert into bookdetails values(?,?,?,?,?)";
	String removeBookQuery = "delete from bookdetails where bookId=?";
	String updateBookQuery = "update bookdetails set bookName=? where bookId=?";

	String issueBookQuery1 = "select * from bookdetails where bookId=?";
	String issueBookQuery2 = "select * from request_details where uId=? and bId=? and email=(select email from user where uId=?) ";
	String issueBookQuery3 = "insert into book_issue values(?,?,?,?)";
	String issueBookQuery4 = "insert into borrowed_books values(?,?,(select email from user where uId=?))";

	String requestBookQuery1 = "select * from bookdetails where bookId=?";
	String requestBookQuery2 = "select count(*) as uId from borrowed_books where uId=? and bId=? and email=(select email from user where uId=?)";
	String requestBookQuery3 = "select count(*) as uId from book_issue where id=?";
	String requestBookQuery4 = "insert into request_details values(?,(select concat(firstName,'_',lastName) from user where uId=?),?,(select bookName from bookdetails where bookId=?),(select email from user where uId=?))";

	String searchBookByTitle = "select * from bookdetails where bookName=?";
	String searchBookByAuthor = "select * from bookdetails where authorName=?";
	String getBooksInfoQuery = "select * from bookdetails";

	String returnBookQuery1 = "select * from bookdetails where bookId=?";
	String returnBookQuery2 = "select * from book_issue where bookId=? and id=?";
	String returnBookQuery3 = "delete from book_issue where bookId=? and id=?";
	String returnBookQuery4 = "delete from borrowed_books where bId=? and uId=?";
	String returnBookQuery5 = "delete from request_details where bId=? and uId=?";

	String bookHistoryDetailsQuery = "select count(*) as uid from book_issue where id=?";
	String borrowedBookQuery = "select * from borrowed_books where uId=?";

	String searchBookByIdQuery = "select * from bookdetails where bookId=?";
	String showRequestsQuery = "select * from request_details";
	String showIssuedBooksQuery = "select * from book_issue";
	String showUsersQuery = "select * from user";

	String updatePasswordQuery1 = "select * from user where email=? and role=?";
	String updatePasswordQuery2 = "update user set password=? where email=? and password=?";
}
