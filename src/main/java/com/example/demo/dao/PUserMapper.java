package com.example.demo.dao;

import com.example.demo.entity.PUser;
import com.example.demo.entity.PUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PUserMapper {
    int countByExample(PUserExample example);

    int deleteByExample(PUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PUser record);

    int insertSelective(PUser record);

    List<PUser> selectByExample(PUserExample example);

    PUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PUser record, @Param("example") PUserExample example);

    int updateByExample(@Param("record") PUser record, @Param("example") PUserExample example);

    int updateByPrimaryKeySelective(PUser record);

    int updateByPrimaryKey(PUser record);
}