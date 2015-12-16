package com.sttri.dao;

import com.sttri.entity.Share;
import com.sttri.entity.ShareCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShareMapper {
    int countByExample(ShareCriteria example);

    int deleteByExample(ShareCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Share record);

    int insertSelective(Share record);

    List<Share> selectByExample(ShareCriteria example);

    Share selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Share record, @Param("example") ShareCriteria example);

    int updateByExample(@Param("record") Share record, @Param("example") ShareCriteria example);

    int updateByPrimaryKeySelective(Share record);

    int updateByPrimaryKey(Share record);
}