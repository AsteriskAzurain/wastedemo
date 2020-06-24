package com.ishang.wastedemo.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ishang.wastedemo.admin.dao.UserTotalDataMapper;
import com.ishang.wastedemo.admin.entity.UserTotalData;
import com.ishang.wastedemo.admin.service.UserTotalDataService;
import com.ishang.wastedemo.core.page.PageRequest;
import com.ishang.wastedemo.core.page.PageResult;

@Service("UserTotalDataService")
public class UserTotalDataServiceImpl implements UserTotalDataService {

	@Autowired
	private UserTotalDataMapper dao;
	
	@Override
	public int save(UserTotalData record) {
		return dao.insert(record);
	}

	@Override
	public int delete(UserTotalData record) {
		// 不允许删除
		return 0;
	}

	@Override
	public int delete(List<UserTotalData> records) {
		// 不允许删除
		return 0;
	}

	@Override
	public UserTotalData findById(Long id) {
		return dao.selectByPrimaryKey(id.intValue());
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return null;
	}

}
