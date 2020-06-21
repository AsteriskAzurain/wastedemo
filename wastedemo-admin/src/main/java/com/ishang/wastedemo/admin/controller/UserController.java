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
import com.ishang.wastedemo.admin.entity.User;
import com.ishang.wastedemo.admin.service.UserService;
import com.ishang.wastedemo.core.http.HttpResult;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService service;
	
	/* todo
	 * 1 登录 1
	 * 2修改密码  1
	 * 3修改个人信息
	 * 4添加业主 1
	 * 5删除
	 * 6查找所有 1
	 * 7模糊查找
	 * */
	
	// public HttpResult userlogin(@RequestParam String name,@RequestParam String passwd)
	@CrossOrigin
	@ResponseBody
	@PostMapping(value="/login")
	public HttpResult userlogin(@RequestBody String jsonParam) {
		JSONObject obj = JSON.parseObject(jsonParam);
		String name = obj.getString("name");
		String passwd = obj.getString("passwd");
		if (null != name && null != passwd) {
			User u = new User();
			u.setName(name);
			u.setPassword(passwd);
			List<User> rst = service.findUser(u);
			System.out.println(rst.toString());
			if(!rst.get(0).getPassword().equals(passwd)) return HttpResult.error("wrong password");
			else return HttpResult.ok(rst.get(0));
		}else {
			return HttpResult.error("用户名或密码不能为空");
		}
		
	}
	
	// public HttpResult userpasswd(@RequestParam int id, @RequestParam String p1, @RequestParam String p2) {
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/password")
	public HttpResult userpasswd(@RequestBody String jsonParam) {
		System.out.println(jsonParam);
		JSONObject obj =JSON.parseObject(jsonParam);
		int id = obj.getInteger("id");
		String p1=obj.getString("pswd1"); 
		String p2=obj.getString("oldpswd");
		if(p1.equals(p2)) return HttpResult.error("新旧密码相同，请重新输入");
		else {
			User u = new User();
			u.setId(id);
			u.setPassword(p1);
			service.updateUser(u);
			return HttpResult.ok(service.findById((long) id));
		}
	}
	
	// public HttpResult userupdate(@RequestBody User record)
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/update")
	public HttpResult userupdate(@RequestBody String jsonParam) {
		System.out.println(jsonParam);
		JSONObject obj =JSON.parseObject(jsonParam);
		// jsonParam: {"id":6,"name":"user","password":"user","realname":"update","tel":"138254564","role":1,"point":0,"delFlag":1}
		User record = new User();
		record.setId(obj.getInteger("id"));
		record.setName(obj.getString("name"));
		record.setRole(obj.getInteger("role"));
		record.setRealname(obj.getString("realname"));
		record.setTel(obj.getString("tel"));
		/*
		 * String realname = obj.getString("realname"); String tel =
		 * obj.getString("tel"); if(!realname.equals("")) record.setRealname(realname);
		 * if(!tel.equals("")) record.setTel(tel);
		 */
		if(service.updateUser(record)==1) {
			System.out.println("update:"+service.findById(obj.getLong("id")).toString());
			return HttpResult.ok(service.findById(obj.getLong("id")));
		}else {
			return HttpResult.error();
		}
	}

	// public HttpResult useradd(@RequestBody User record) {
	@CrossOrigin
	@PostMapping(value = "/insert")
	@ResponseBody
	public HttpResult useradd(@RequestBody String jsonParam) {
		System.out.println(jsonParam);
		JSONObject obj = JSON.parseObject(jsonParam);
		
		String username = obj.getString("username");
		String password =obj.getString("password");
		if(username==null || password==null) 
			return HttpResult.error("username and password cannot be empty.");
		else {
			User record = new User();
			record.setName(username);
			record.setPassword(password);
			record.setRole(obj.getInteger("role"));
			record.setTel(obj.getString("tel"));
			
			String realname = obj.getString("realname");
			if(realname!="") record.setRealname(realname);
			record.setPoint(0);
			record.setDelFlag(1);
			return HttpResult.ok(service.save(record));
		}
	}
	
	@PostMapping(value = "/delete")
	public HttpResult userdelete(@RequestParam int id) {
		User u = new User();
		u.setId(id);
		return HttpResult.ok(service.delete(u));
	}
	
	@PostMapping(value="/findAll")
	public HttpResult userfindall() {
		return HttpResult.ok(service.findAll());
	}
	
	@PostMapping(value="/findLike")
	public HttpResult userfindlike(@RequestParam String name) {
		User u = new User();
		u.setName(name);
		return HttpResult.ok(service.findUser(u));
	}
	
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/loaduserinfo")
		public HttpResult userloadinfo(@RequestBody String jsonParam) {
			JSONObject object = JSON.parseObject(jsonParam);
			long id = object.getLong("id");
			return HttpResult.ok(service.findById(id));
//			return HttpResult.ok();
		}
	
}
