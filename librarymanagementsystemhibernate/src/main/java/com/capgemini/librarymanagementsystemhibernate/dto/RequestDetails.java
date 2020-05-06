package com.capgemini.librarymanagementsystemhibernate.dto;

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
@Table(name = "request_details")
@SequenceGenerator(name = "seq1", initialValue = 1, allocationSize = 100)
public class RequestDetails {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq1")
	private int id;
	@Column
	private int uId;
	@Column
	private int bId;
	@Column
	private String email;
	@Column
	private String bookName;

}
