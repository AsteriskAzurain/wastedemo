package com.ishang.wastedemo.admin.dao;

import com.ishang.wastedemo.admin.entity.PointDetail;
import java.util.List;

public interface PointDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PointDetail record);

    PointDetail selectByPrimaryKey(Integer id);

    List<PointDetail> selectAll();

    int updateByPrimaryKey(PointDetail record);
    
    List<PointDetail> selectByUserid(int userid);
}