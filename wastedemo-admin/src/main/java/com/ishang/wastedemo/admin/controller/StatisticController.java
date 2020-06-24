package com.ishang.wastedemo.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ishang.wastedemo.admin.entity.ResultData;
import com.ishang.wastedemo.admin.service.PointDetailService;
import com.ishang.wastedemo.admin.service.PointQuestionService;
import com.ishang.wastedemo.admin.service.RecycleOrderDetailService;
import com.ishang.wastedemo.admin.service.RecycleOrderService;
import com.ishang.wastedemo.admin.service.RecycleSiteService;
import com.ishang.wastedemo.admin.service.RubbishService;
import com.ishang.wastedemo.admin.service.UserService;
import com.ishang.wastedemo.admin.service.UserTotalDataService;
import com.ishang.wastedemo.core.http.HttpResult;

@RestController
@RequestMapping("statistic")
public class StatisticController {

	@Autowired
	private RecycleOrderService orderservice;

	@Autowired
	private RecycleOrderDetailService orderdetailservice;

	/*
	 * todo 1根据垃圾种类统计 select sum(number) from usertotaldata group by typeid
	 * 2下单数量业主排行 select count(*) from order group by userid 3回收垃圾总数业主排行 同2
	 * 4被回收垃圾的种类排行 select rubbishid,SUM(number) as sum from user_total_data WHERE
	 * rubbishtype=2 GROUP BY rubbishid ORDER BY sum 5被扔垃圾数量排行 select count(number)
	 * from usertotaldata group by rubbishid
	 */

	// 需要学习图表插件
	// 使用饿了么前端团队的v-charts组件

	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/loaddata1")
	public HttpResult datarubbishtype() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ResultData> datalist = orderdetailservice.getdata1();
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		for (ResultData resultData : datalist) {
			list1.add(resultData.getId());
			list2.add(resultData.getCount());
		}
		map.put("columns", list1);
		map.put("rows", datalist);
//			map.put("list2", list2);
		return HttpResult.ok(map);
	}

	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/loaddata2")
	public HttpResult dataorder() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ResultData> datalist = orderservice.getdata2();
		map.put("rows", datalist);
		return HttpResult.ok(map);
	}

	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/loaddata3")
	public HttpResult datarubbishcount() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ResultData> datalist = orderdetailservice.getdata3();
		map.put("rows", datalist);
		return HttpResult.ok(map);
	}

	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/loaddata4")
	public HttpResult datauser() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ResultData> datalist =orderservice.getdata4();
		map.put("rows", datalist);
		return HttpResult.ok(map);
	}
}
