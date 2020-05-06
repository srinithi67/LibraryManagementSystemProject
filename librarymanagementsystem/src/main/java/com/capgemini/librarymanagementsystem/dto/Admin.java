package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Admin implements Serializable {
	private int aId = (int) Math.random();
	private String aName;
	private String aPassword;
	private String aEmail;

	public Admin() {

	}

	
	public Admin(int aId, String aName, String aPassword, String aEmail) {
		super();
		this.aId = aId;
		this.aName = aName;
		this.aPassword = aPassword;
		this.aEmail = aEmail;
	}


	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getaPassword() {
		return aPassword;
	}

	public void setaPassword(String aPassword) {
		this.aPassword = aPassword;
	}

	public String getaEmail() {
		return aEmail;
	}

	public void setaEmail(String aEmail) {
		this.aEmail = aEmail;
	}

}
