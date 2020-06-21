package com.ishang.wastedemo.admin.dao;

import com.ishang.wastedemo.admin.entity.RecycleSite;
import java.util.List;

public interface RecycleSiteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecycleSite record);

    RecycleSite selectByPrimaryKey(Integer id);

    List<RecycleSite> selectAll();

    int updateByPrimaryKey(RecycleSite record);
    
    List<String> selectAreaList();
    
    List<RecycleSite> selectbyarea(String areaname);
}