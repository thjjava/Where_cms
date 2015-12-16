package com.sttri.dao;

import com.sttri.entity.RoleMenus;
import com.sttri.entity.RoleMenusCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RoleMenusMapper {
    int countByExample(RoleMenusCriteria example);

    int deleteByExample(RoleMenusCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleMenus record);

    int insertSelective(RoleMenus record);

    List<RoleMenus> selectByExample(RoleMenusCriteria example);

    RoleMenus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleMenus record, @Param("example") RoleMenusCriteria example);

    int updateByExample(@Param("record") RoleMenus record, @Param("example") RoleMenusCriteria example);

    int updateByPrimaryKeySelective(RoleMenus record);

    int updateByPrimaryKey(RoleMenus record);
    
    List<RoleMenus> queryMenus(Integer id);
}