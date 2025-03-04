package com.kh.mvc.model.dto;

import java.sql.Date;

public class UserDTO {
	private int userNo;
	private String userId;
	private String userPW;
	private String userName;
	private Date enrollDate;
	
	public UserDTO() { 
		super(); 
	}

	public UserDTO(int userNo, String userId, String userPW, String userName, Date enrollDate) {
		super();
		
		this.userNo = userNo;
		this.userId = userId;
		this.userPW = userPW;
		this.userName = userName;
		this.enrollDate = enrollDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "UserDTO [userNo=" + userNo + ", userId=" + userId + ", userPW=" + userPW + ", userName=" + userName
				+ ", enrollDate=" + enrollDate + "]";
	}
}
