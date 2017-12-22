package com.liutao.demo.controller;

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
import com.liutao.demo.util.response.CommonResponse;

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
		model.addAttribute("users", list);
		return "form/index";
	}

	@ResponseBody
	@GetMapping("/user/{id}")
	public CommonResponse getUser(@PathVariable Long id) {
		log.debug("获取用户：{}", id);
		User user = userMapperService.getOne(id);
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
		List<User> list = userMapperService.findAll(pageable);
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
		return CommonResponse.success(userMapperService.save(one));
	}

	@ResponseBody
	@PutMapping("/user/{id}")
	public CommonResponse editUser(@PathVariable Long id, @RequestBody UserVo user) {
		log.info("进入Edit处理方法：{}", user);
		User one = userMapperService.getOne(id);
		if (one == null) {
			return CommonResponse.noData();
		}
		one.setUsername(user.getUsername());
		one.setUpdateTime(new Date());
		return CommonResponse.success(userMapperService.updateUserById(one));
	}

	@ResponseBody
	@DeleteMapping("/user/{id}")
	public CommonResponse deleteUser(@PathVariable Long id) {
		log.debug("进入Delete处理方法：{}", id);
		return CommonResponse.success(userMapperService.delete(id));
	}

	@ResponseBody
	@DeleteMapping("/users")
	public CommonResponse deleteUsers(@RequestBody UserDTO dto) {
		log.debug("进入Delete处理方法：{}", dto);
		log.info(dto.toString());
		if(dto.getIds() == null || dto.getIds().isEmpty()) {
			return CommonResponse.success();
		}
		/*List<User> users = new ArrayList<>(dto.getIds().size());
		for (Long id : dto.getIds()) {
			User user = new User();
			user.setId(id);
			users.add(user);
		}*/
		return CommonResponse.success(userMapperService.deleteInBatch(dto.getIds()));
	}

}
