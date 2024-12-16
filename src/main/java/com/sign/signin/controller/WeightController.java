package com.sign.signin.controller;

import com.sign.signin.bean.Weight;
import com.sign.signin.service.report.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weight")
public class WeightController {

    @Autowired
    private WeightService weightService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Weight> ListWeight() {
        return weightService.getAllWeight();
    }
}
