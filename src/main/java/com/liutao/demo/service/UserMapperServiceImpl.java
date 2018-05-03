package com.liutao.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.liutao.demo.domain.User;
import com.liutao.demo.repository.UserMapper;

@Service
// @Transactional
public class UserMapperServiceImpl extends ServiceImpl<BaseMapper<User>, User> implements UserMapperService {

	@Autowired
	UserMapper userMapper;

	// @Autowired
	// RedisCacheManager redisCacheManager;

	@Override
	public User getOne(Long id) {
		User user = new User();
		user.setId(id);
		return userMapper.selectOne(user);
	}

	@Override
	public List<User> findAll(Sort pageable) {
		EntityWrapper<User> wrapper = new EntityWrapper<User>();
		wrapper.setSqlSelect("id").orderBy("create_time", false);
		return userMapper.selectList(wrapper);
	}

	@Override
	public Integer save(User one) {
		return userMapper.insert(one);
	}

	@Override
	public Integer updateUserById(User one) {
		return userMapper.updateById(one);
	}

	@Override
	public Integer delete(Long id) {
		return userMapper.deleteById(id);
	}

	@Override
	public Integer deleteInBatch(List<Long> idList) {
		return userMapper.deleteBatchIds(idList);
	}
}
