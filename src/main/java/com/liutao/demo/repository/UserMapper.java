package com.liutao.demo.repository;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.liutao.demo.domain.User;

public interface UserMapper {

	User selectOne(User user);

	List<User> selectList(EntityWrapper<User> wrapper);

	Integer insert(User one);

	Integer deleteById(Long id);

	Integer updateById(User one);

	Integer deleteBatchIds(List<Long> idList);

}
