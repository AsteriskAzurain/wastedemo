package com.ishang.wastedemo.admin.dao;

import com.ishang.wastedemo.admin.entity.RecycleOrder;
import java.util.List;

public interface RecycleOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecycleOrder record);

    RecycleOrder selectByPrimaryKey(Integer id);

    List<RecycleOrder> selectAll();

    int updateByPrimaryKey(RecycleOrder record);
    
    List<RecycleOrder> selectByUserid(int userid);
}