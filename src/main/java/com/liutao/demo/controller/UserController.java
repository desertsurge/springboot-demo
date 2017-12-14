package com.liutao.demo.controller;

import com.liutao.demo.domain.User;
import com.liutao.demo.domain.UserDTO;
import com.liutao.demo.domain.UserVo;
import com.liutao.demo.repository.UserRepository;
import com.liutao.demo.util.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public CommonResponse addUser(UserVo user) {
		log.debug("进入Post处理方法：" + user);
		Date now = new Date();
		user.setCreateTime(now);
		user.setUpdateTime(now);
		User one = new User();
		BeanUtils.copyProperties(user, one);
		userRepository.save(one);
		return CommonResponse.success(one);
	}

	@ResponseBody
	@PutMapping("/user/{id}")
	public CommonResponse editUser(@PathVariable Long id, @RequestBody UserVo user) {
		log.info("进入Edit处理方法：{}", user);
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

	@ResponseBody
	@DeleteMapping("/users")
	public CommonResponse deleteUsers(@RequestBody UserDTO dto) {
		log.debug("进入Delete处理方法：{}", dto);
		log.info(dto.toString());
		List<User> users = new ArrayList<>(dto.getIds().size());
		for (Long id : dto.getIds()) {
			User user = new User();
			user.setId(id);
			users.add(user);
		}
		userRepository.deleteInBatch(users);
		return CommonResponse.success();
	}

}
