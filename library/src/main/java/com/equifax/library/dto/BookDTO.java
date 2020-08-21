package com.equifax.library.dto;

public class BookDTO {

	private Integer bookId;
	private String bookName;
	private String bookStatus;
	private Integer userId;
	
	public BookDTO()
	{
		
	}
	public BookDTO(Integer bookId, String bookName, String bookStatus, Integer userId) {
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
