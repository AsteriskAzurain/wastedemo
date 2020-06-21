package com.ishang.wastedemo.admin.dao;

import com.ishang.wastedemo.admin.entity.RecycleOrderDetail;
import java.util.List;

public interface RecycleOrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecycleOrderDetail record);

    RecycleOrderDetail selectByPrimaryKey(Integer id);

    List<RecycleOrderDetail> selectAll();

    int updateByPrimaryKey(RecycleOrderDetail record);
    
    List<RecycleOrderDetail> selectByOrderid(int orderid);
}