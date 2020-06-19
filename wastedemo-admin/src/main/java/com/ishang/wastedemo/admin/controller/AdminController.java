package com.ishang.wastedemo.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ishang.wastedemo.admin.entity.Admin;
import com.ishang.wastedemo.admin.service.AdminService;
import com.ishang.wastedemo.core.http.HttpResult;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private AdminService service;

	/*
	 * todo 1登录 2修改密码 3添加 4删除 5修改 6查找所有 7模糊查找
	 */

	@CrossOrigin
	@PostMapping(value = "/login")
	@ResponseBody
	public HttpResult adminlogin(@RequestBody String jsonParam) {
		JSONObject jobj = JSON.parseObject(jsonParam);
		String name = jobj.getString("name");
		String passwd = jobj.getString("passwd");
		if (null != name && null != passwd) {
			Admin a = new Admin();
			a.setUsername(name);
			a.setPassword(passwd);
			List<Admin> rst = service.findAdmin(a);
			if (!rst.get(0).getPassword().equals(passwd))
				return HttpResult.error("wrong password");
			else
				return HttpResult.ok(rst.get(0));
		} else
			return HttpResult.error("login failed.");
	}

	@PostMapping(value = "/password")
	public HttpResult adminpasswd(@RequestParam int id, @RequestParam String p1, @RequestParam String p2) {
		if (!p1.equals(p2))
			return HttpResult.error("The two passwords are inconsistent, please re-enter.");
		else {
			Admin a = new Admin();
			a.setId(id);
			a.setPassword(p1);
			return HttpResult.ok(service.updateAdmin(a));
		}
	}

	@PostMapping(value = "/insert")
	public HttpResult adminadd(@RequestBody Admin record) {
		if (record.getUsername() == null || record.getPassword() == null)
			return HttpResult.error("username and password cannot be empty.");
		if (record.getRole() == null)
			record.setRole(0);
		record.setDelFlag(1);
		return HttpResult.ok(service.save(record));
	}

	@PostMapping(value = "/delete")
	public HttpResult admindelete(@RequestParam int id) {
		Admin a = new Admin();
		a.setId(id);
		return HttpResult.ok(service.delete(a));
	}

	@PostMapping(value = "/update")
	public HttpResult adminupdate(@RequestBody Admin record) {
		return HttpResult.ok(service.updateAdmin(record));
	}

	@PostMapping(value = "/findAll")
	public HttpResult adminfindall() {
		return HttpResult.ok(service.findAll());
	}

	@PostMapping(value = "/findLike")
	public HttpResult adminfindlike(@RequestParam String name) {
		Admin record = new Admin();
		record.setUsername(name);
		List<Admin> rst = service.findAdmin(record);
		return HttpResult.ok(rst);
	}
}
