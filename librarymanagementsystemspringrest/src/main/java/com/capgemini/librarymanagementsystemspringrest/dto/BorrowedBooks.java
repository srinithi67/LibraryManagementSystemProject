package com.capgemini.librarymanagementsystemspringrest.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;


@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "borrowed_books")
@SequenceGenerator(name = "seq2", initialValue = 1, allocationSize = 100)
public class BorrowedBooks implements Serializable {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq2")
	private int id;
	@Column
	private int uId;
	@Column
	private int bId;
	@Column
	private String bookName;

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

}
