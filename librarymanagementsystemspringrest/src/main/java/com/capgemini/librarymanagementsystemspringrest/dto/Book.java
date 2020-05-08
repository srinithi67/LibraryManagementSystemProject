package com.capgemini.librarymanagementsystemspringrest.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@SuppressWarnings("serial")
@Entity
@Table(name = "book")
@JsonRootName("Book")
@SequenceGenerator(name = "seq3", initialValue = 101, allocationSize = 100)
public class Book implements Serializable{

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq3")
	private int bookId;
	@Column
	private String bookName;
	@Column
	private String authorName;
	@Column
	private String category;
	@Column
	private String bookPublications;
	@Column
	private int copies;

	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBookPublications() {
		return bookPublications;
	}
	public void setBookPublications(String bookPublications) {
		this.bookPublications = bookPublications;
	}
	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}
	

	

}
