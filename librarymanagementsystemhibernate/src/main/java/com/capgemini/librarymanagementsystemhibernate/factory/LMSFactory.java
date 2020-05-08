package com.capgemini.librarymanagementsystemhibernate.factory;

import com.capgemini.librarymanagementsystemhibernate.dao.AdminDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.AdminDAOImple;
import com.capgemini.librarymanagementsystemhibernate.dao.AdminUserDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.AdminUserDAOImple;
import com.capgemini.librarymanagementsystemhibernate.dao.UserDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.UserDAOImple;
import com.capgemini.librarymanagementsystemhibernate.service.AdminService;
import com.capgemini.librarymanagementsystemhibernate.service.AdminServiceImple;
import com.capgemini.librarymanagementsystemhibernate.service.AdminUserService;
import com.capgemini.librarymanagementsystemhibernate.service.AdminUserServiceImple;
import com.capgemini.librarymanagementsystemhibernate.service.UserService;
import com.capgemini.librarymanagementsystemhibernate.service.UserServiceImple;

public class LMSFactory {
	public static UserDAO getUserDAO() {
		return new UserDAOImple();
	}

	public static AdminDAO getAdminDAO() {
		return new AdminDAOImple();
	}

	public static AdminUserDAO getAdminUserDAO() {
		return new AdminUserDAOImple();
	}

	public static UserService getUserService() {
		return new UserServiceImple();
	}

	public static AdminService getAdminService() {
		return new AdminServiceImple();
	}

	public static AdminUserService getAdminUserService() {
		return new AdminUserServiceImple();
	}

}
