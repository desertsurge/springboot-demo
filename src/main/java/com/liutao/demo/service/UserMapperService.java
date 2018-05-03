package com.liutao.demo.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.baomidou.mybatisplus.service.IService;
import com.liutao.demo.domain.User;

public interface UserMapperService extends IService<User>{

	User getOne(Long id);

	List<User> findAll(Sort pageable);

	Integer save(User one);

	Integer updateUserById(User one);

	Integer delete(Long id);

	Integer deleteInBatch(List<Long> idList);

}
