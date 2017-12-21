package com.liutao.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.liutao.demo.domain.User;
import com.liutao.demo.repository.UserMapper;

@Service
@CacheConfig(cacheNames="USER")
//@Transactional
public class UserMapperServiceImpl implements UserMapperService {

	@Autowired
	UserMapper userMapper;

	@Override
	@Cacheable(key="#id")
	public User getOne(Long id) {
		return userMapper.getOne(id);
	}

	@Override
	public List<User> findAll(Sort pageable) {
		return userMapper.findAll(pageable);
	}

	@Override
	public void save(User one) {
		userMapper.save(one);		
	}

	@Override
	@Cacheable(key="#one.id")
	public User saveAndFlush(User one) {
		return userMapper.saveAndFlush(one);
	}

	@Override
	@CacheEvict(key="#id")
	public void delete(Long id) {
		userMapper.delete(id);
	}

	@Override
	public void deleteInBatch(List<User> users) {
		userMapper.deleteInBatch(users);
	}
}
