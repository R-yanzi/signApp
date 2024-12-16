package com.sign.signin.mapper.task;

import com.sign.signin.bean.task.Tasklog;
import org.springframework.stereotype.Repository;

@Repository
public interface TasklogMapper {
    int insert(Tasklog record);

    int insertSelective(Tasklog record);
}