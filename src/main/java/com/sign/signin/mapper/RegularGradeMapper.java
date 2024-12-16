package com.sign.signin.mapper;

import com.sign.signin.bean.RegularGrade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegularGradeMapper {
    int deleteByPrimaryKey(String userid);

    int insert(RegularGrade record);

    int insertSelective(RegularGrade record);

    RegularGrade selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(RegularGrade record);

    int updateByPrimaryKey(RegularGrade record);

    List<RegularGrade> selectAll();
}