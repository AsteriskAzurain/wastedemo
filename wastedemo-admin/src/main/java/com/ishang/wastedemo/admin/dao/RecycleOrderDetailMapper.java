package com.ishang.wastedemo.admin.dao;

import com.ishang.wastedemo.admin.entity.RecycleOrderDetail;
import com.ishang.wastedemo.admin.entity.ResultData;

import java.util.List;
import java.util.Map;

public interface RecycleOrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecycleOrderDetail record);

    RecycleOrderDetail selectByPrimaryKey(Integer id);

    List<RecycleOrderDetail> selectAll();

    int updateByPrimaryKey(RecycleOrderDetail record);
    
    List<RecycleOrderDetail> selectByOrderid(int orderid);
    
    List<ResultData> getDataRubbishType();
    
   Integer getUserRubbishCount(Integer userid);
    
    List<ResultData> getDataRubbishCount();
}