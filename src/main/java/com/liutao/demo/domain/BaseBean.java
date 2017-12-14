package com.liutao.demo.domain;


import com.alibaba.fastjson.JSONObject;

public class BaseBean {

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
