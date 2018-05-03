package com.liutao.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.liutao.demo.common.SuperMapper;
import com.liutao.demo.common.cache.RedisCacheUtil;
import com.liutao.demo.domain.User;

@CacheConfig(cacheNames = User.CACHE_NAME)
@Repository
public class UserMapperImpl implements UserMapper {

	@Autowired
	private SuperMapper<User> mapper;

	@Autowired
	RedisCacheUtil<User> redisCacheUtil;

	@Override
	@Cacheable(key = "#entity.id")
	public User selectOne(User entity) {
		return mapper.selectOne(entity);
	}

	@Override
	public List<User> selectList(EntityWrapper<User> wrapper) {
		return mapper.selectList(wrapper);
	}

	@Override
	public Integer insert(User one) {
		return mapper.insert(one);
	}

	@Override
	@CacheEvict(key = "#id")
	public Integer deleteById(Long id) {
		return mapper.deleteById(id);
	}

	@Override
	@CachePut(key = "#one.id")
	public Integer updateById(User one) {
		return mapper.updateById(one);
	}

	@Override
	public Integer deleteBatchIds(List<Long> idList) {
		redisCacheUtil.evictBatch(User.CACHE_NAME, idList.toArray(new Long[0]));
		return mapper.deleteBatchIds(idList);
	}
	
}
