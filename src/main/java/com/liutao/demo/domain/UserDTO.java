package com.liutao.demo.domain;

import java.util.List;

import com.liutao.demo.common.BaseBean;

public class UserDTO extends BaseBean {
	private static final long serialVersionUID = 1L;
	private List<Long> ids;

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
