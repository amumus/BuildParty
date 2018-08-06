package com.mumu.dao;

import com.mumu.bean.Theory;
import com.mumu.bean.TheoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TheoryMapper {
    long countByExample(TheoryExample example);

    int deleteByExample(TheoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Theory record);

    int insertSelective(Theory record);

    List<Theory> selectByExampleWithBLOBs(TheoryExample example);

    List<Theory> selectByExample(TheoryExample example);

    Theory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Theory record, @Param("example") TheoryExample example);

    int updateByExampleWithBLOBs(@Param("record") Theory record, @Param("example") TheoryExample example);

    int updateByExample(@Param("record") Theory record, @Param("example") TheoryExample example);

    int updateByPrimaryKeySelective(Theory record);

    int updateByPrimaryKeyWithBLOBs(Theory record);

    int updateByPrimaryKey(Theory record);
    
    List<Theory> selectList(@Param("start")Integer start,@Param("rows")Integer rows);
}