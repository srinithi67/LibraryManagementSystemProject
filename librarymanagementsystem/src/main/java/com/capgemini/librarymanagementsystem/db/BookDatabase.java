package com.capgemini.librarymanagementsystem.db;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public class BookDatabase {
	public static final ArrayList<Book> book = new ArrayList<Book>();
	public static final ArrayList<User> user = new ArrayList<User>();
	public static final ArrayList<Admin> admin = new ArrayList<Admin>();
	public static final ArrayList<Request> REQUEST = new ArrayList<Request>();

	public static void addToDB() {

		admin.add(new Admin(100001, "srinithi", "Srinithi@123", "srinithi@gmail.com"));
		admin.add(new Admin(200001, "nithi", "Nithi@123", "nithi@gmail.com"));

		// user.add(new
		// User(111111,"ramya","ramya@gmail.com","Ramya@123","cse",7788997788,07/08/2020,02/02/2020);
		
		

		
		  book.add(new Book(101010,"java","grp","james gosslig","textBook", null, null));
		  book.add(new Book(101011, "Spring","sia", "williams",  "AllInOne", null, null));
		  book.add(new Book(101012, "angular", "real", "richard", "textBook", null, null));
		  book.add(new Book(101013, "chemistry", "sia", "henry", "AllInOne", null, null));
		 
	}
}
