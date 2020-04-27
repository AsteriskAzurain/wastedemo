package com.ishang.wastedemo.admin.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ishang.wastedemo.admin.entity.RecycleSite;
import com.ishang.wastedemo.admin.service.RecycleSiteService;
import com.ishang.wastedemo.core.page.PageRequest;
import com.ishang.wastedemo.core.page.PageResult;

@Service("RecycleSiteService")
public class RecycleSiteServiceImpl implements RecycleSiteService {

	@Override
	public int save(RecycleSite record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(RecycleSite record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(List<RecycleSite> records) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RecycleSite findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
