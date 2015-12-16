package com.sttri.dao;

import com.sttri.entity.AdminLog;
import com.sttri.entity.AdminLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminLogMapper {
    int countByExample(AdminLogCriteria example);

    int deleteByExample(AdminLogCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminLog record);

    int insertSelective(AdminLog record);

    List<AdminLog> selectByExample(AdminLogCriteria example);

    AdminLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminLog record, @Param("example") AdminLogCriteria example);

    int updateByExample(@Param("record") AdminLog record, @Param("example") AdminLogCriteria example);

    int updateByPrimaryKeySelective(AdminLog record);

    int updateByPrimaryKey(AdminLog record);
}