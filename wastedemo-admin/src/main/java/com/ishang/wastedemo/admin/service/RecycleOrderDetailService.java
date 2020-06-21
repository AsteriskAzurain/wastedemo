package com.ishang.wastedemo.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ishang.wastedemo.admin.entity.RecycleOrderDetail;
import com.ishang.wastedemo.core.service.CurdService;

@Service
public interface RecycleOrderDetailService extends CurdService<RecycleOrderDetail> {
	
	List<RecycleOrderDetail> findbyorderid(int orderid);
}
