package com.ishang.wastedemo.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ishang.wastedemo.admin.entity.RecycleOrderDetail;
import com.ishang.wastedemo.admin.entity.ResultData;
import com.ishang.wastedemo.core.service.CurdService;

@Service
public interface RecycleOrderDetailService extends CurdService<RecycleOrderDetail> {
	
	List<RecycleOrderDetail> findbyorderid(int orderid);
	
	List<ResultData> getdata1();
	
	List<ResultData> getdata3();
}
