package com.ishang.wastedemo.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ishang.wastedemo.admin.service.PointDetailService;
import com.ishang.wastedemo.core.http.HttpResult;

@RestController
@RequestMapping("user/point")
public class UserPointController {

	@Autowired
	private PointDetailService detailservice;

	/*
	 * todo 1查询积分(按userid) 2积分提现(num为负 source="提现") 3查询积分变更记录(按时间) 1 4查询提现记录
	 */

	// 1
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/findbyuser")
	public HttpResult detailfindown(@RequestBody String jsonParam) {
		JSONObject obj=JSON.parseObject(jsonParam);
		int id=obj.getInteger("id");
		return HttpResult.ok(detailservice.findbyuserid(id));
	}

	// 3 按时间排序&筛选还没做
	@PostMapping(value = "/history")
	public HttpResult pointselectall() {
		return HttpResult.ok(detailservice.findall());
	}

}
