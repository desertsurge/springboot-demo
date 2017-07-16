package com.liutao.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liutao.demo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
