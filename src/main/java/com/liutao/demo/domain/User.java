package com.liutao.demo.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.liutao.demo.common.BaseBean;

@TableName("user")
public class User extends BaseBean {

	private static final long serialVersionUID = 1L;
	public static final String CACHE_NAME = "USER";

	@TableId(type = IdType.ID_WORKER)
	Long id;

	@TableField
	String username;

	@TableField
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date createTime;

	@TableField
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

}
