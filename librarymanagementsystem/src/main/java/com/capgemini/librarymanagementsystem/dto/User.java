package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class User implements Serializable {

	private int uId = (int) Math.random();;
	private String uDept;
	private String uName;
	private String uPassword;
	private String uEmail;
	private long uMobile;
	private Date issueDate;
	private Date returnDate;
	private int booksBorrowed;

	public User() {

	}

	public User(int uId, String uDept, String uName, String uPassword, String uEmail, long uMobile, Date issueDate,
			Date returnDate, int booksBorrowed) {
		super();
		this.uId = uId;
		this.uDept = uDept;
		this.uName = uName;
		this.uPassword = uPassword;
		this.uEmail = uEmail;
		this.uMobile = uMobile;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.booksBorrowed = booksBorrowed;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getuDept() {
		return uDept;
	}

	public void setuDept(String uDept) {
		this.uDept = uDept;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getuEmail() {
		return uEmail;
	}

	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}

	public long getuMobile() {
		return uMobile;
	}

	public void setuMobile(long uMobile) {
		this.uMobile = uMobile;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getBooksBorrowed() {
		return booksBorrowed;
	}

	public void setBooksBorrowed(int booksBorrowed) {
		this.booksBorrowed = booksBorrowed;
	}

}
