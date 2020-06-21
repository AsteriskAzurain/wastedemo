package com.ishang.wastedemo.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ishang.wastedemo.admin.entity.User;
import com.ishang.wastedemo.core.service.CurdService;

@Service
public interface UserService extends CurdService<User> {

	/**
	 * 查找所有的user
	 *  实际应用时应选择分页插件
	 * */
	List<User> findAll();
	
	/**
	 * 修改现在登录的user的信息
	 * */
	int updateUser(User record);
	
	/**
	 * user的模糊查找 返回list
	 * todo: dao和xml自己写
	 * */
	List<User> findUser(User record);
	
	/**
	 * 下单时 更新用户的积分数
	 */
	int updatepoint(int userid,int point);
}
