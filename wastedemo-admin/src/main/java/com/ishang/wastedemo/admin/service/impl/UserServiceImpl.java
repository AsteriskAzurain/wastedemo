package com.ishang.wastedemo.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ishang.wastedemo.admin.dao.UserMapper;
import com.ishang.wastedemo.admin.entity.User;
import com.ishang.wastedemo.admin.service.UserService;
import com.ishang.wastedemo.core.page.PageRequest;
import com.ishang.wastedemo.core.page.PageResult;

@Service("UserService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper dao;
	
	@Override
	public int save(User record) {
		return dao.insert(record);
	}

	@Override
	public int delete(User record) {
		record.setDelFlag(0);
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public int delete(List<User> records) {
		if (records != null && records.size() > 0) {
			for (User u : records) delete(u);
			return 0;
		}else return 1;
	}

	@Override
	public User findById(Long id) {
		return dao.selectByPrimaryKey(id.intValue());
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return null;
	}

	@Override
	public List<User> findAll() {
		return dao.selectAll();
	}

	@Override
	public int updateUser(User record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public List<User> findUser(User record) {
		return dao.findbyname(record.getName());
	}

	@Override
	public int updatepoint(int userid,int point) {
		User user = findById((long) userid);
		if(null!=user) {
			int oldpoint=user.getPoint();
			point+=oldpoint;
			return dao.updatepoint(userid, point);
		}else return 0;
	}

}
