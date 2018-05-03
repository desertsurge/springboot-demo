package com.liutao.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liutao.demo.domain.User;
import com.liutao.demo.domain.UserDTO;
import com.liutao.demo.domain.UserVo;
import com.liutao.demo.service.UserMapperService;
import com.liutao.demo.util.response.RespBody;

@Controller
public class UserController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserMapperService userMapperService;

	@GetMapping(value = {"/", "/user"})
	public String toPut(Model model) {
		log.debug("进入put页面");
		Sort pageable = new Sort("id");
		List<User> list = userMapperService.findAll(pageable);
		List<User> result = convertUserList(list);
		model.addAttribute("users", result);
		return "form/index";
	}

	@ResponseBody
	@GetMapping("/user/{id}")
	public RespBody getUser(@PathVariable Long id) {
		log.debug("获取用户：{}", id);
		User user = userMapperService.getOne(id);
		if (user == null) {
			return RespBody.noData();
		}
		return RespBody.success(user);
	}

	@ResponseBody
	@GetMapping("/users")
	public RespBody getUsers() {
		log.debug("获取全部用户");
		Sort pageable = new Sort(Direction.DESC, "id");
		List<User> list = userMapperService.findAll(pageable);
		List<User> result = convertUserList(list);
		return RespBody.success(result);
	}

	private List<User> convertUserList(List<User> list) {
		List<User> result = new ArrayList<>(list.size());
		for (User user : list) {
			User u = userMapperService.getOne(user.getId());
			result.add(u);
		}
		return result;
	}

	@ResponseBody
	@PostMapping("/user")
	public RespBody addUser(UserVo user) {
		log.debug("进入Post处理方法：" + user);
		Date now = new Date();
		user.setCreateTime(now);
		user.setUpdateTime(now);
		User one = new User();
		BeanUtils.copyProperties(user, one);
		return RespBody.success(userMapperService.save(one));
	}

	@ResponseBody
	@PutMapping("/user/{id}")
	public RespBody editUser(@PathVariable Long id, @RequestBody UserVo user) {
		log.info("进入Edit处理方法：{}", user);
		User one = userMapperService.getOne(id);
		if (one == null) {
			return RespBody.noData();
		}
		one.setUsername(user.getUsername());
		one.setUpdateTime(new Date());
		return RespBody.success(userMapperService.updateUserById(one));
	}

	@ResponseBody
	@DeleteMapping("/user/{id}")
	public RespBody deleteUser(@PathVariable Long id) {
		log.debug("进入Delete处理方法：{}", id);
		return RespBody.success(userMapperService.delete(id));
	}

	@ResponseBody
	@DeleteMapping("/users")
	public RespBody deleteUsers(@RequestBody UserDTO dto) {
		log.debug("进入Delete处理方法：{}", dto);
		if(dto.getIds() == null || dto.getIds().isEmpty()) {
			return RespBody.success();
		}
		/*List<User> users = new ArrayList<>(dto.getIds().size());
		for (Long id : dto.getIds()) {
			User user = new User();
			user.setId(id);
			users.add(user);
		}*/
		return RespBody.success(userMapperService.deleteInBatch(dto.getIds()));
	}

}
