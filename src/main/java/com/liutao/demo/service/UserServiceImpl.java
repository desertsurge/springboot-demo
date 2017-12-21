package com.liutao.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.liutao.demo.domain.User;
import com.liutao.demo.repository.UserRepository;

@Service
@CacheConfig(cacheNames="USER")
//@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Cacheable(key="#id")
	public User getOne(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public List<User> findAll(Sort pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public void save(User one) {
		userRepository.save(one);		
	}

	@Override
	@Cacheable(key="#one.id")
	public User saveAndFlush(User one) {
		return userRepository.saveAndFlush(one);
	}

	@Override
	@CacheEvict(key="#id")
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public void deleteInBatch(List<User> users) {
		userRepository.deleteInBatch(users);
	}
}
