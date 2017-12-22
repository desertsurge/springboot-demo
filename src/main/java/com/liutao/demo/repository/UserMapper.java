package com.liutao.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.liutao.demo.common.SuperMapper;
import com.liutao.demo.domain.User;

@Mapper
public interface UserMapper extends SuperMapper<User>{

}
