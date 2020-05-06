package com.capgemini.librarymanagementsystemhibernate.factory;

import com.capgemini.librarymanagementsystemhibernate.dao.UserDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.UserDAOImple;
import com.capgemini.librarymanagementsystemhibernate.service.UserService;
import com.capgemini.librarymanagementsystemhibernate.service.UserServiceImple;

public class LMSFactory {
	public static UserDAO getUserDAO() {
		return new UserDAOImple();
	}

	public static UserService getUserService() {
		return new UserServiceImple();
	}

}
