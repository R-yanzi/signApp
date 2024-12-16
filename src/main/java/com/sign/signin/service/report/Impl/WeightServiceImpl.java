package com.sign.signin.service.report.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sign.signin.bean.Weight;
import com.sign.signin.mapper.WeightMapper;
import com.sign.signin.service.report.WeightService;
import com.sign.signin.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightServiceImpl implements WeightService {

    @Autowired
    private WeightMapper weightMapper;

    /**
     * 获取所有等级
     * @return 等级列表
     */
    @Override
    public List<Weight> getAllWeight() {
        return weightMapper.selectAll();
    }
}
