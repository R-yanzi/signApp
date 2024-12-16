package com.sign.signin.service.task.impl;

import com.sign.signin.mapper.task.TaskMapper;
import com.sign.signin.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Long getTaskCount() {
        return taskMapper.selectAll();
    }
}
