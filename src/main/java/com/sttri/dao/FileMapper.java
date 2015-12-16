package com.sttri.dao;

import com.sttri.entity.File;
import com.sttri.entity.FileCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileMapper {
    int countByExample(FileCriteria example);

    int deleteByExample(FileCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(File record);

    int insertSelective(File record);

    List<File> selectByExample(FileCriteria example);

    File selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") File record, @Param("example") FileCriteria example);

    int updateByExample(@Param("record") File record, @Param("example") FileCriteria example);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
}