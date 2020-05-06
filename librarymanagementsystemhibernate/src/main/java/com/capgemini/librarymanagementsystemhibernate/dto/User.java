package com.capgemini.librarymanagementsystemhibernate.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
@SequenceGenerator(name = "seq4", initialValue = 100001, allocationSize = 100)
public class User implements Serializable {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq4")
	private int uId;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private long mobile;
	@Column
	private String role;

	/*
	 * 
	 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "user") private
	 * List<BookIssueDetails> issueDetails;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "user") private
	 * List<RequestDetails> requests;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "usera") private
	 * List<BorrowedBooks> borrowed;
	 */

}
