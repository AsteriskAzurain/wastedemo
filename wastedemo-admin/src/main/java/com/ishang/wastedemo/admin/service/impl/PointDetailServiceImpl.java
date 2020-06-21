package com.ishang.wastedemo.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ishang.wastedemo.admin.dao.PointDetailMapper;
import com.ishang.wastedemo.admin.entity.PointDetail;
import com.ishang.wastedemo.admin.service.PointDetailService;
import com.ishang.wastedemo.core.page.PageRequest;
import com.ishang.wastedemo.core.page.PageResult;

@Service("PointDetailService")
public class PointDetailServiceImpl implements PointDetailService {
	
	@Autowired
	private PointDetailMapper detaildao;

	@Override
	public int save(PointDetail record) {
		return detaildao.insert(record);
	}

	@Override
	public int delete(PointDetail record) {
		//不允许删除
		return 0;
	}

	@Override
	public int delete(List<PointDetail> records) {
		//不允许删除
		return 0;
	}

	@Override
	public PointDetail findById(Long id) {
		return detaildao.selectByPrimaryKey(id.intValue());
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return null;
	}

	@Override
	public List<PointDetail> findall() {
		return detaildao.selectAll();
	}

	@Override
	public List<PointDetail> findbyuserid(int userid) {
		return detaildao.selectByUserid(userid);
	}

}
