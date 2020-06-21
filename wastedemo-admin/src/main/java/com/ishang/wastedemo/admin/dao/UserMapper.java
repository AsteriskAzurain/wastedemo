package com.ishang.wastedemo.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ishang.wastedemo.admin.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

	int insert(User record);

	User selectByPrimaryKey(Integer id);

	List<User> selectAll();

	int updateByPrimaryKey(User record);

	int deleteByPrimaryKey(String user);
	
	//--------------------
	
	List<User> findbyentity(User record);
	
	List<User> findbyname(String name);
	
	int updatepoint(@Param(value = "userid")int userid, @Param(value = "point")int point);
	
}