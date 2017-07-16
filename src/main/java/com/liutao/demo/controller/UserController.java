package com.liutao.demo.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liutao.demo.domain.User;
import com.liutao.demo.repository.UserRepository;
import com.liutao.demo.util.response.CommonResponse;

@Controller
public class UserController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = {"/", "/user"})
	public String toPut(Model model) {
		log.debug("进入put页面");
		Sort pageable = new Sort("id");
		List<User> list = userRepository.findAll(pageable);
		model.addAttribute("users", list);
		return "form/index";
	}

	@ResponseBody
	@GetMapping("/user/{id}")
	public CommonResponse getUser(@PathVariable Long id) {
		log.debug("获取用户：{}", id);
		User user = userRepository.getOne(id);
		if (user == null) {
			return CommonResponse.noData();
		}
		return CommonResponse.success(user);
	}

	@ResponseBody
	@GetMapping("/users")
	public CommonResponse getUsers() {
		log.debug("获取全部用户");
		Sort pageable = new Sort(Direction.DESC, "id");
		List<User> list = userRepository.findAll(pageable);
		return CommonResponse.success(list);
	}

	@ResponseBody
	@PostMapping("/user")
	public CommonResponse addUser(User user) {
		log.debug("进入Post处理方法：" + user);
		Date now = new Date();
		user.setCreateTime(now);
		user.setUpdateTime(now);
		userRepository.save(user);
		return CommonResponse.success(user);
	}

	@ResponseBody
	@PutMapping("/user/{id}")
	public CommonResponse editUser(@PathVariable Long id, User user) {
		log.debug("进入Edit处理方法：{}", user);
		User one = userRepository.getOne(id);
		if (one == null) {
			return CommonResponse.noData();
		}
		one.setUsername(user.getUsername());
		one.setUpdateTime(new Date());
		User user1 = userRepository.saveAndFlush(one);
		return CommonResponse.success(user1);
	}

	@ResponseBody
	@DeleteMapping("/user/{id}")
	public CommonResponse deleteUser(@PathVariable Long id) {
		log.debug("进入Delete处理方法：{}", id);
		userRepository.delete(id);
		return CommonResponse.success();
	}

}
