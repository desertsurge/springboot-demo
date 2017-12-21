package com.liutao.demo.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.liutao.demo.domain.User;

public interface UserService {

	User getOne(Long id);

	List<User> findAll(Sort pageable);

	void save(User one);

	User saveAndFlush(User one);

	void delete(Long id);

	void deleteInBatch(List<User> users);

}
