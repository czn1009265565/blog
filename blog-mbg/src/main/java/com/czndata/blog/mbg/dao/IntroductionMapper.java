package com.czndata.blog.mbg.dao;

import com.czndata.blog.mbg.entity.Introduction;
import com.czndata.blog.mbg.entity.IntroductionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntroductionMapper {
    long countByExample(IntroductionExample example);

    int deleteByExample(IntroductionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Introduction record);

    int insertSelective(Introduction record);

    List<Introduction> selectByExampleWithBLOBs(IntroductionExample example);

    List<Introduction> selectByExample(IntroductionExample example);

    Introduction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Introduction record, @Param("example") IntroductionExample example);

    int updateByExampleWithBLOBs(@Param("record") Introduction record, @Param("example") IntroductionExample example);

    int updateByExample(@Param("record") Introduction record, @Param("example") IntroductionExample example);

    int updateByPrimaryKeySelective(Introduction record);

    int updateByPrimaryKeyWithBLOBs(Introduction record);

    int updateByPrimaryKey(Introduction record);
}