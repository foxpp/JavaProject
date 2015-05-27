package com.anotation.beans;

import com.anotation.jdbc.JDBCColumAnnotation;
import com.anotation.jdbc.JDBCTableAnnotation;

@JDBCTableAnnotation(tableName="account")
public class User {
	@JDBCColumAnnotation(colName="id")
	public Long id;
	
	@JDBCColumAnnotation(colName="user_name")
	public String userName;
	
	@JDBCColumAnnotation(colName="password")
	public String password;
	
	@JDBCColumAnnotation(colName="tel")
	public String telephone;
	
	@JDBCColumAnnotation(colName="state")
	public Integer state;
	
	@JDBCColumAnnotation(colName="user_id")
	public Integer userId;
	
	@JDBCColumAnnotation(colName="create_time")
	public java.sql.Timestamp cTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public java.sql.Timestamp getcTime() {
		return cTime;
	}

	public void setcTime(java.sql.Timestamp cTime) {
		this.cTime = cTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + ", telephone=" + telephone + ", state=" + state
				+ ", userId=" + userId + ", cTime=" + cTime + "]";
	}

	
	
	
}
