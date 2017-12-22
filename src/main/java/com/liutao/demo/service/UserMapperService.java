package com.liutao.demo.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.liutao.demo.domain.User;

public interface UserMapperService {

	User getOne(Long id);

	List<User> findAll(Sort pageable);

	Integer save(User one);

	Integer updateUserById(User one);

	Integer delete(Long id);

	Integer deleteInBatch(List<Long> idList);

}
