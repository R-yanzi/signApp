package com.sign.signin.service.report;

import com.sign.signin.bean.User;

import java.util.List;

public interface UserService {
    Long getTotalNum();

    List<User> getUserInIdList(List<String> idList);

    List<User> selectAll();
}
