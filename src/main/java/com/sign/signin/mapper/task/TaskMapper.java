package com.sign.signin.mapper.task;

import com.sign.signin.bean.task.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskMapper {
    int deleteByPrimaryKey(Integer taskid);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer taskid);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    Long selectAll();
}