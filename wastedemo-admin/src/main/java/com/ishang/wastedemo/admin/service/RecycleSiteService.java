package com.ishang.wastedemo.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ishang.wastedemo.admin.entity.RecycleSite;
import com.ishang.wastedemo.core.service.CurdService;

@Service
public interface RecycleSiteService extends CurdService<RecycleSite> {

	List<RecycleSite> findAll();
	
	List<String> findAllArea();
	
	List<RecycleSite> findByArea(String area);
}