package com.ishang.wastedemo.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ishang.wastedemo.admin.entity.RecycleOrder;
import com.ishang.wastedemo.core.service.CurdService;

@Service
public interface RecycleOrderService extends CurdService<RecycleOrder> {

	List<RecycleOrder> findall();

	List<RecycleOrder> findbyuserid(int userid);
}
