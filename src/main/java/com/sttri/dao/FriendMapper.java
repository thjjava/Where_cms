package com.sttri.dao;

import com.sttri.entity.Friend;
import com.sttri.entity.FriendCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendMapper {
    int countByExample(FriendCriteria example);

    int deleteByExample(FriendCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Friend record);

    int insertSelective(Friend record);

    List<Friend> selectByExample(FriendCriteria example);

    Friend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Friend record, @Param("example") FriendCriteria example);

    int updateByExample(@Param("record") Friend record, @Param("example") FriendCriteria example);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
}