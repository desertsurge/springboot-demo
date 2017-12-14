package com.liutao.demo.domain;

import java.util.List;

public class UserDTO extends BaseBean{
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
