package com.liutao.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.domain.Sort;

import com.liutao.demo.domain.User;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM user WHERE id = #{id}")
	User getOne(@Param("id") Long id);

	@Update("UPDATE user SET username=#{one.username}, update_time=#{one.updateTime} WHERE id=#{one.id}")
	User saveAndFlush(User one);

	@Delete("DELETE FROM user WHERE id=#{id}")
	void delete(@Param("id") Long id);

	void deleteInBatch(List<User> users);

	@Insert("INSERT INTO user (username, create_time) VALUES (#{one.username}, #{one.createTime})")
	void save(User one);

	List<User> findAll(Sort pageable);

}
