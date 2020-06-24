package com.ishang.wastedemo.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ishang.wastedemo.admin.dao.RecycleOrderDetailMapper;
import com.ishang.wastedemo.admin.dao.RecycleOrderMapper;
import com.ishang.wastedemo.admin.entity.RecycleOrder;
import com.ishang.wastedemo.admin.entity.ResultData;
import com.ishang.wastedemo.admin.service.RecycleOrderService;
import com.ishang.wastedemo.core.page.PageRequest;
import com.ishang.wastedemo.core.page.PageResult;

@Service("RecycleOrderService")
public class RecycleOrderServiceImpl implements RecycleOrderService {

	@Autowired
	private RecycleOrderMapper dao;
	
	@Autowired
	private RecycleOrderDetailMapper detaildao;
	
	@Override
	public int save(RecycleOrder record) {
		return dao.insert(record);
	}

	@Override
	public int delete(RecycleOrder record) {
		record.setDelFlag(0);
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public int delete(List<RecycleOrder> records) {
		for(RecycleOrder o : records) delete(o);
		return 0;
	}

	@Override
	public RecycleOrder findById(Long id) {
		return dao.selectByPrimaryKey(id.intValue());
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return null;
	}

	@Override
	public List<RecycleOrder> findall() {
		return dao.selectAll();
	}

	@Override
	public List<RecycleOrder> findbyuserid(int userid) {
		return dao.selectByUserid(userid);
	}

	@Override
	public List<ResultData> getdata2() {
		return dao.getDataordercount();
	}

	@Override
	public List<ResultData> getdata4() {
		List<ResultData> rstlist=new ArrayList<ResultData>();
		List<Integer> peoplelist=dao.getUserId();
		System.out.println(peoplelist.toString());
		if(peoplelist.size()>0) {
			for (Integer userid : peoplelist) {
				ResultData data=new ResultData();
				data.setId(userid);
				data.setCount(detaildao.getUserRubbishCount(userid));
				rstlist.add(data);
			}
			System.out.println(rstlist.toString());
			return rstlist;
		}else {
			return new ArrayList<ResultData>();
		}
		
	}

}
