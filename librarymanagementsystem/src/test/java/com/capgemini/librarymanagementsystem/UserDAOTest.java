package com.capgemini.librarymanagementsystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dao.UserDAO;
import com.capgemini.librarymanagementsystem.dao.UserDAOImple;
import com.capgemini.librarymanagementsystem.dto.User;

public class UserDAOTest {
	private UserDAO dao = new UserDAOImple();
	
	@Test
	public void testRegisterUser() {
		User u = new User();
		u.setuId(111222);
		u.setuName("paparao");
		u.setuMobile(987654321);
		u.setuPassword("paparao@123");
		u.setuEmail("paparao@gmail.com");
		u.setuDept("cse");
		u.setReturnDate(null);
		u.setIssueDate(null);
		u.setBooksBorrowed(1);
		boolean b=dao.registerUser(u);
		Assertions.assertTrue(b);
	}
	@Test
	public void testRegisterUser1() {
		User u = new User();
		u.setuId(111222);
		u.setuName("paparao");
		u.setuMobile(987654321);
		u.setuPassword("paparao@123");
		u.setuEmail("paparao@gmail.com");
		u.setuDept("cse");
		u.setReturnDate(null);
		u.setIssueDate(null);
		u.setBooksBorrowed(1);
		boolean b1=dao.registerUser(u);
		Assertions.assertTrue(b1);
	}
	@Test
	public void testAuthUser() {
		User u=dao.authUser("paparao@gmail.com", "paparao@123");
		Assertions.assertNotNull(u);
	}
	@Test
	public void testAuthUser1() {
		User u1=dao.authUser("paparao@gmail.com", "paparao@123");
		Assertions.assertNotNull(u1);
	}


}
