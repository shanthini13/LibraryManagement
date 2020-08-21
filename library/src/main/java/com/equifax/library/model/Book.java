package com.equifax.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Book {
	@Id
	@GeneratedValue
	@Column(name="BOOK_ID")
	private Integer bookId;
	@Column(name="BOOK_NAME")
	private String bookName;
	@Column(name="BOOK_STATUS")
	private String bookStatus;
	@Column(name="USER_ID")
	private Integer userId;
	
	public Book() {
		
	}

	public Book(Integer bookId, String bookName, String bookStatus, Integer userId) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookStatus = bookStatus;
		this.userId = userId;
	}

	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
