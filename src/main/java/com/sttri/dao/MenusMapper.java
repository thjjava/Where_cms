package com.sttri.dao;

import com.sttri.entity.Menus;
import com.sttri.entity.MenusCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenusMapper {
    int countByExample(MenusCriteria example);

    int deleteByExample(MenusCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Menus record);

    int insertSelective(Menus record);

    List<Menus> selectByExample(MenusCriteria example);

    Menus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Menus record, @Param("example") MenusCriteria example);

    int updateByExample(@Param("record") Menus record, @Param("example") MenusCriteria example);

    int updateByPrimaryKeySelective(Menus record);

    int updateByPrimaryKey(Menus record);
}