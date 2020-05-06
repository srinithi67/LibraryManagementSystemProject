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
@Table(name = "book")
@SequenceGenerator(name = "seq3", initialValue = 101, allocationSize = 100)
public class Book implements Serializable {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq3")
	private int bId;
	@Column
	private String bookName;
	@Column
	private String bookPublications;
	@Column
	private String authorName;
	@Column
	private String category;

	/*
	 * 
	 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "books") private
	 * List<BookIssueDetails> issueDetails;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "books") private
	 * List<RequestDetails> requests;
	 * 
	 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "books") private
	 * List<BorrowedBooks> borrowed;
	 * 
	 */

}
