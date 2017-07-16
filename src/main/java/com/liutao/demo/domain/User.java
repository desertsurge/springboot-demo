package com.liutao.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@Column
	String username;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date createTime;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

}
