package com.sign.signin.mapper;

import com.sign.signin.bean.Weight;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface WeightMapper {
    int deleteByPrimaryKey(Integer weightId);

    int insert(Weight record);

    int insertSelective(Weight record);

    Weight selectByPrimaryKey(Integer weightId);

    int updateByPrimaryKeySelective(Weight record);

    int updateByPrimaryKey(Weight record);

    List<Weight> selectAll();
}