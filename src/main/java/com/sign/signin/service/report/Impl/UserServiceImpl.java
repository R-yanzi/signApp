package com.sign.signin.service.report.Impl;

import com.sign.signin.bean.User;
import com.sign.signin.mapper.UserMapper;
import com.sign.signin.service.report.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 返回学生总人数
     * @return 学生总人数
     */
    @Override
    public Long getTotalNum() {
        return userMapper.getTotalCount();
    }

    /**
     * 返回在学号集合里面的学生列表
     * @param idList 学号集合
     * @return 学生列表
     */
    @Override
    public List<User> getUserInIdList(List<String> idList) {
        return userMapper.selectInList(idList);
    }

    /**
     * 返回所有学生
     * @return 学生列表
     */
    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
