package com.ishang.wastedemo.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ishang.wastedemo.admin.entity.RecycleOrder;
import com.ishang.wastedemo.admin.entity.RecycleOrderDetail;
import com.ishang.wastedemo.admin.entity.Rubbish;
import com.ishang.wastedemo.admin.entity.RubbishType;
import com.ishang.wastedemo.admin.entity.UserTotalData;
import com.ishang.wastedemo.admin.service.RecycleOrderDetailService;
import com.ishang.wastedemo.admin.service.RecycleOrderService;
import com.ishang.wastedemo.admin.service.RecycleSiteService;
import com.ishang.wastedemo.admin.service.RubbishService;
import com.ishang.wastedemo.admin.service.UserTotalDataService;
import com.ishang.wastedemo.core.http.HttpResult;

@RestController
@RequestMapping("recycle")
public class RecycleController {
	
	private int typeid=2;
	
	@Autowired
	private RecycleOrderService orderservice;
	
	@Autowired
	private RecycleOrderDetailService detailservice;
	
	@Autowired
	private RecycleSiteService siteservice;
	
	@Autowired
	private RubbishService rubbishservice;
	
	@Autowired
	private UserTotalDataService dataservice;
	
	/* todo
	 * 1查找回收点
	 * 2创建回收订单
	 * 	2.1 增加订单
	 * 	2.2 增加垃圾
	 * 	2.3 指定回收点
	 * 3查看业主自己的订单
	 * 4回收订单管理
	 * 	4.1查找所有
	 * 	4.2模糊查找
	 * 	4.3更新订单信息
	 * 	4.4删除
	 * 5回收点管理
	 * 	5.1查找所有
	 * 	5.2模糊查找
	 * 	5.3添加
	 * 	5.4修改
	 * 	5.5删除
	 * */
	
	//2
	@PostMapping(value = "/order/add")
	public HttpResult orderadd(@RequestParam int userid, @RequestParam int siteid,
			@RequestParam int n1, @RequestParam int n2, @RequestParam int n3, 
			@RequestParam int n4, @RequestParam int n5, @RequestParam int n6) {
		
		// order
		RecycleOrder neworder= new RecycleOrder();
		neworder.setUserid(userid);
		neworder.setSiteid(siteid);
		neworder.setPointnumber(0f);
		neworder.setTotalnumber(0);
		java.sql.Date dt= new java.sql.Date(new Date().getTime());
		neworder.setCreatetime(dt);
		
		int[] numarr= {n1,n2,n3,n4,n5,n6};
		List<Rubbish> rubbisharr=rubbishservice.selectbytype(typeid);
		int sn=0; float sp=0f;
		for(int i =0; i<6; i++) {
			int num=numarr[i];
			sn+=num;
			sp+=(numarr[i]*rubbisharr.get(i).getPrice());
			
			//rubbish data
			if(num>0) {
				UserTotalData newdata = new UserTotalData();
				newdata.setUserid(userid);
				newdata.setRubbishid(rubbisharr.get(i).getId());
				newdata.setRubbishtype(typeid);
				newdata.setNumber(num);
				newdata.setCreatetime(dt);
				newdata.setDelFlag(1);
				dataservice.save(newdata);
			}
		}
		neworder.setPointnumber(sp);
		neworder.setTotalnumber(sn);
		int orderid = orderservice.save(neworder);
		
		for(int i =0; i<6; i++) {
			//orderdetail
			RecycleOrderDetail d =new RecycleOrderDetail();
			d.setOrderid(orderid);
			d.setRubbishid(rubbisharr.get(i).getId());
			d.setDelFlag(1);
			detailservice.save(d);
		}	
		
		return HttpResult.ok(dt.toString());
	}
	
	//4.1
	@PostMapping(value = "/order/selectall")
	public HttpResult orderfindall() {
		return HttpResult.ok(orderservice.findall());
	}
	
	// 5.1
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/site/findall")
	public HttpResult sitefindall() {
					return HttpResult.ok(siteservice.findAll());
//			return HttpResult.ok();
		}
	
	
}
