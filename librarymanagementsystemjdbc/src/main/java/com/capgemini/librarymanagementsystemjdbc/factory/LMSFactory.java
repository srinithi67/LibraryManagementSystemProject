package com.capgemini.librarymanagementsystemjdbc.factory;


import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAOImple;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminUserDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminUserDAOImple;
import com.capgemini.librarymanagementsystemjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.UserDAOImple;
import com.capgemini.librarymanagementsystemjdbc.service.AdminService;
import com.capgemini.librarymanagementsystemjdbc.service.AdminServiceImple;
import com.capgemini.librarymanagementsystemjdbc.service.AdminUserService;
import com.capgemini.librarymanagementsystemjdbc.service.AdminUserServiceImple;
import com.capgemini.librarymanagementsystemjdbc.service.UserService;
import com.capgemini.librarymanagementsystemjdbc.service.UserServiceImple;

public class LMSFactory {
	
	public static AdminDAO getAdminDAO() {
		return new AdminDAOImple();
	}
	
	public static AdminUserDAO getAdminUserDAO() {
		return new AdminUserDAOImple();
	}
	

	public static UserDAO getUserDAO() {
		return new UserDAOImple();
	}

	public static AdminService getAdminService() {
		return new AdminServiceImple();
			
	}
	
	public static AdminUserService getAdminUserService() {
	return new AdminUserServiceImple();
	}
	
	public static UserService getUserService() {
		return new UserServiceImple();
	}

}
