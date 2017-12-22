package com.liutao.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.liutao.demo.common.cache.RedisCacheUtil;
import com.liutao.demo.domain.User;
import com.liutao.demo.repository.UserMapper;

@Service
@CacheConfig(cacheNames="USER")
//@Transactional
public class UserMapperServiceImpl implements UserMapperService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	RedisCacheUtil<User> redisCacheUtil;

	@Autowired
	RedisCacheManager redisCacheManager;

	@Override
	@Cacheable(key="#id")
	public User getOne(Long id) {
		User user = new User();
		user.setId(id);
		return userMapper.selectOne(user);
//		return userMapper.getOne(id);
	}

	@Override
	public List<User> findAll(Sort pageable) {
		EntityWrapper<User> wrapper = new EntityWrapper<User>();
		wrapper.setSqlSelect("id")
			.orderBy("create_time", false);
		return userMapper.selectList(wrapper);
//		return userMapper.findAll(pageable);
	}

	@Override
	public Integer save(User one) {
		return userMapper.insert(one);
//		userMapper.save(one);		
	}

	@Override
	@CachePut(key="#one.id")
	public Integer updateUserById(User one) {
		return userMapper.updateById(one);
//		return userMapper.saveAndFlush(one);
	}

	@Override
	@CacheEvict(key="#id")
	public Integer delete(Long id) {
		return userMapper.deleteById(id);
//		userMapper.delete(id);
	}

	@Override
	public Integer deleteInBatch(List<Long> idList) {
		Cache cache = redisCacheManager.getCache("USER");
		for (Long id : idList) {
			cache.evict(id);
		}
		return userMapper.deleteBatchIds(idList);
//		userMapper.deleteInBatch(users);
	}
}
