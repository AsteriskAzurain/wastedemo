package com.ishang.wastedemo.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ishang.wastedemo.admin.entity.PointDetail;
import com.ishang.wastedemo.admin.entity.RecycleOrder;
import com.ishang.wastedemo.admin.entity.RecycleOrderDetail;
import com.ishang.wastedemo.admin.entity.Rubbish;
import com.ishang.wastedemo.admin.entity.RubbishType;
import com.ishang.wastedemo.admin.entity.UserTotalData;
import com.ishang.wastedemo.admin.service.PointDetailService;
import com.ishang.wastedemo.admin.service.RecycleOrderDetailService;
import com.ishang.wastedemo.admin.service.RecycleOrderService;
import com.ishang.wastedemo.admin.service.RecycleSiteService;
import com.ishang.wastedemo.admin.service.RubbishService;
import com.ishang.wastedemo.admin.service.UserService;
import com.ishang.wastedemo.admin.service.UserTotalDataService;
import com.ishang.wastedemo.core.http.HttpResult;
import com.mysql.cj.xdevapi.JsonArray;

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
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private PointDetailService pointdetailservice;
	
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
//	public HttpResult orderadd(@RequestParam int userid, @RequestParam int siteid,
//			@RequestParam int n1, @RequestParam int n2, @RequestParam int n3, 
//			@RequestParam int n4, @RequestParam int n5, @RequestParam int n6) {
	
	@PostMapping(value = "/order/add")
	public HttpResult orderadd(@RequestBody String jsonParam) {
//		System.out.println("json:   "+jsonParam);
		JSONObject obj= JSON.parseObject(jsonParam);
		// order
		RecycleOrder neworder= new RecycleOrder();
		int userid=obj.getInteger("userid");
		neworder.setUserid(userid);
		neworder.setSiteid(obj.getInteger("siteid"));
		neworder.setPointnumber(100f);
		userservice.updatepoint(userid, 100);
		PointDetail pd=new PointDetail();
		pd.setUserid(userid);
		pd.setNum(100);
		pd.setSource("下单奖励");
		neworder.setTotalnumber(obj.getInteger("totalnumber"));
		neworder.setTotalprice(obj.getFloat("totalprice"));
		java.sql.Date dt= new java.sql.Date(new Date().getTime());
		neworder.setCreatetime(dt);
		pd.setCreatetime(dt);
		if(pointdetailservice.save(pd)>0) System.out.println("point detail save succeed.");
		else return HttpResult.error("下单失败，请重试");
		neworder.setDelFlag(1);
		if(orderservice.save(neworder)==0) return HttpResult.error("下单失败，请重试");
		int orderid=neworder.getId();
		JSONArray arr=obj.getJSONArray("detail");
		List<Integer> ridarr= new ArrayList<Integer>();
		Map<Integer,Integer> detailmap=new HashMap<Integer, Integer>();
		for (Object object : arr) {
			// {"subprice":3,"subtotal":9,"rubbishid":4,"count":3,"delFlag":true,"userid":6,"key":1592733281821}
			JSONObject jobj=(JSONObject)object;
			int rubbishid=jobj.getInteger("rubbishid");
			int count=jobj.getInteger("count");
			if(!(null==(detailmap.get(rubbishid)))) count+=detailmap.get(rubbishid);
			else ridarr.add(rubbishid);
			detailmap.put(rubbishid, count);
		}
		
		for (Integer rid : ridarr) {
			int count=detailmap.get(rid);
			for (int i = 0; i < count; i++) {
				RecycleOrderDetail d =new RecycleOrderDetail();
				d.setOrderid(orderid);
				d.setRubbishid(rid);
				d.setDelFlag(1);
				detailservice.save(d);
			}
		}
		return HttpResult.ok("下单成功");
	}
	
	// 3 
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/order/selectownorder")
	public HttpResult orderfindown(@RequestBody String jsonParam) {
		System.out.println(jsonParam);
		JSONObject object = JSON.parseObject(jsonParam);
		int id=object.getInteger("id");
		List<RecycleOrder>  rstlist = orderservice.findbyuserid(id);
		if(rstlist.size()>0) {
			List<Map<String , Object>> maplist= new ArrayList<Map<String,Object>>();
			for (RecycleOrder recycleOrder : rstlist) {
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("order", recycleOrder);
				int oid=recycleOrder.getId();
				List<RecycleOrderDetail> detaillist = detailservice.findbyorderid(oid);
				map.put("detail", detaillist);
				maplist.add(map);
			}
			System.out.println(maplist.toString());
			return HttpResult.ok(maplist);
		}
		else return HttpResult.error("暂无订单信息");
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
	
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/site/findallarea")
	public HttpResult sitefindallarea() {
					return HttpResult.ok(siteservice.findAllArea());
		}
	
	
}
