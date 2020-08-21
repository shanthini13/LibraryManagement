package com.equifax.library.dto;

public class UserDTO {
	
	private Integer userId;
	private String userName;
	private String userRole;
	private String userStatus;
	private String mailId;
	
	public UserDTO() {
		
	}
	
	public UserDTO(Integer userId, String userName, String userRole, String userStatus,String mailId) {
		this.userId = userId;
		this.userName = userName;
		this.userRole = userRole;
		this.userStatus = userStatus;	
		this.mailId=mailId;
	}
	
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	

}
