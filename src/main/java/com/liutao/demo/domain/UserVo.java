package com.liutao.demo.domain;

import java.util.Map;

public class UserVo extends User {

    Map<String, Object> flexAttrs;

    public Map<String, Object> getFlexAttrs() {
        return flexAttrs;
    }

    public void setFlexAttrs(Map<String, Object> flexAttrs) {
        this.flexAttrs = flexAttrs;
    }
}
