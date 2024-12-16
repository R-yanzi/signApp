package com.sign.signin.controller;

import com.sign.signin.bean.Grade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {

    @RequestMapping("/list")
    public List<Grade> getGrades() {
        // 初始化成绩
        return null;
    }
}
