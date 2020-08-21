package com.equifax.library.model;


	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
	import javax.persistence.Table;

	@Entity
	@Table(name="USER")
	public class User {
		
		@Id
		@GeneratedValue
		@Column(name = "USER_ID")
		private Integer userId;
		
		@Column(name = "USER_NAME")
		private String userName;
		
		@Column(name = "ROLE")
		private String userRole;
		
		@Column(name="USER_STATUS")
		private String userStatus;
		
		@Column(name="MAIL_ID")
		private String mailId;
		
		public User() {
			
		}
		
		public User(Integer userId, String userName, String userRole, String userStatus,String mailId) {
			this.userId = userId;
			this.userName = userName;
			this.userRole = userRole;
			this.userStatus = userStatus;
			this.mailId=mailId;
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
		public String getUserStatus() {
			return userStatus;
		}
		public void setUserStatus(String userStatus) {
			this.userStatus = userStatus;
		}
		public String getMailId() {
			return mailId;
		}
		public void setMailId(String mailId) {
			this.mailId = mailId;
		}
		
}

