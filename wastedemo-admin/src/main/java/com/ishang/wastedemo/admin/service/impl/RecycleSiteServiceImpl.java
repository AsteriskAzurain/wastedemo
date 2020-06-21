package com.ishang.wastedemo.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ishang.wastedemo.admin.dao.RecycleSiteMapper;
import com.ishang.wastedemo.admin.entity.RecycleSite;
import com.ishang.wastedemo.admin.service.RecycleSiteService;
import com.ishang.wastedemo.core.page.PageRequest;
import com.ishang.wastedemo.core.page.PageResult;

@Service("RecycleSiteService")
public class RecycleSiteServiceImpl implements RecycleSiteService {

	@Autowired
	private RecycleSiteMapper dao;
	
	@Override
	public int save(RecycleSite record) {
		return dao.insert(record);
	}

	@Override
	public int delete(RecycleSite record) {
		record.setDelFlag(0);
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public int delete(List<RecycleSite> records) {
		return 0;
	}

	@Override
	public RecycleSite findById(Long id) {
		int shortid=id.intValue();
		return dao.selectByPrimaryKey(shortid);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return null;
	}

	@Override
	public List<RecycleSite> findAll() {
		return dao.selectAll();
	}

	@Override
	public List<String> findAllArea() {
		return dao.selectAreaList();
	}

	@Override
	public List<RecycleSite> findByArea(String area) {
		return dao.selectbyarea(area);
	}

}
