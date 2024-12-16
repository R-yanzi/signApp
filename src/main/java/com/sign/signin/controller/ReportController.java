package com.sign.signin.controller;

import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sign.signin.bean.*;
import com.sign.signin.service.report.*;
import com.sign.signin.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@RestController
@RequestMapping(("/report"))
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserReportService userReportService;

    @Autowired
    private WeightService weightService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private FilterService filterService;

    @Autowired
    private ReportUserService reportUserService;

    @Autowired
    private ChartService chartService;

    /**
     * 返回汇报列表
     * @return 汇报列表
     */
    @RequestMapping(value = "/list/{desc}", method = RequestMethod.GET)
    public List<Report> ListReport(@PathVariable("desc") String desc) {
        return reportService.getAllReport(("true".equals(desc)));
    }

    /**
     * 新增汇报列表
     * @param requestBody 请求参数
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<Report> AddReport(@RequestBody Map<String, Object> requestBody) {
        // 获取当前需要的排序方式
        boolean desc = (boolean) requestBody.getOrDefault("desc", false);
        return reportService.addReport(desc);
    }

    /**
     * 根据reportId删除汇报
     * @param requestBody 请求体
     * @param reportId 要删的id
     * @return 删除之后的汇报列表
     */
    @RequestMapping(value = "/list/{reportId}", method = RequestMethod.DELETE)
    public List<Report> DeleteReport(@RequestBody Map<String, Object> requestBody, @PathVariable("reportId") String reportId) {
        // 获取当前需要的排序方式
        boolean desc = (boolean) requestBody.getOrDefault("desc", false);
        return reportService.deleteReport(desc, Long.parseLong(reportId));
    }

    /**
     * 根据前端发来的搜索联想词语对汇报内容搜索
     * @return 根据汇报id和搜索内容返回搜索关键字列表
     */
    @RequestMapping(value = "/search/{reportId}/{search}", method = RequestMethod.GET)
    public List<Search> searchUserReport(@PathVariable("reportId") String reportId, @PathVariable("search") String search) {
        return searchService.getSearchWithReportId(Long.parseLong(reportId), search);
    }

    /**
     * 根据前端发送的搜索联想关键词，返回搜索成绩列表
     * @param reportId 汇报id
     * @param search 搜索关键词
     * @return 成绩列表
     */
    @RequestMapping(value = "/searched/{reportId}/{search}", method = RequestMethod.GET)
    public List<UserReport> searchUserReportsByString(@PathVariable("reportId") String reportId, @PathVariable("search") String search) {
        return userReportService.getUserReportBySearch(Long.parseLong(reportId), search);
    }

    /**
     * 返回前端学生总人数
     * @return 学生人数
     */
    @RequestMapping(value = "/list/total", method = RequestMethod.GET)
    public Long getTotalNum() {
        return userService.getTotalNum();
    }

    /**
     * 根据汇报id返回学生汇报成绩
     * @param reportId 汇报id
     * @return 汇报成绩列表
     */
    @RequestMapping(value = "/detail/{reportId}", method = RequestMethod.GET)
    public List<UserReport> getUserReportByReportId(@PathVariable("reportId") Long reportId) {
        return userReportService.getUserReport(reportId);
    }

    /**
     * 根据汇报id返回汇报
     * @param reportId 汇报id
     * @return 具体汇报
     */
    @RequestMapping(value = "/{reportId}", method = RequestMethod.GET)
    public Report getReportByReportId(@PathVariable("reportId") Long reportId) {
        return reportService.getReportById(reportId);
    }

    /**
     * 返回所有等级
     * @return 等级列表
     */
    @RequestMapping(value = "/weight", method = RequestMethod.GET)
    public List<Weight> getAllWeight() {
        return weightService.getAllWeight();
    }

    /**
     * 根据前端提供的过滤条件对所有学生成绩进行过滤
     * @return 学生成绩列表
     */
    @RequestMapping(value = "/filter/{reportId}", method = RequestMethod.POST)
    public List<UserReport> getFilterUserReport(@PathVariable("reportId") Long reportId, @RequestBody Map<String, Object> requestBody) {
        try {
            return filterService.filterUserReport(reportId, requestBody.get("filter").toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据前端的汇报id获取对应的筛选条件
     * @param reportId 汇报id
     * @return 对应的筛选条件
     */
    @RequestMapping(value = "/filter/{reportId}", method = RequestMethod.GET)
    public List<FilterCondition> getFilters(@PathVariable("reportId") Long reportId){
        return filterService.getFilters(reportId);
    }

    /**
     * 根据前端发送的汇报id、打分等级、学生对象修改已有等级
     * @param reportId 汇报id
     * @return 是否修改成功
     */
    @RequestMapping(value = "/weight/{reportId}", method = RequestMethod.PUT)
    public Boolean changeWeight(@PathVariable("reportId") Long reportId, @RequestBody Map<String, String> requestBody){
        Weight weight = null;
        User user = null;
        try {
            weight = JacksonUtils.toObject(requestBody.get("weight"), Weight.class);
            user = JacksonUtils.toObject(requestBody.get("user"), User.class);
            return reportUserService.updateReportUser(reportId, user, weight);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据前端发送的汇报id、打分等级、学生对象新增等级汇报
     * @param reportId 汇报id
     * @return 是否修改成功
     */
    @RequestMapping(value = "/weight/{reportId}", method = RequestMethod.POST)
    public Boolean addWeight(@PathVariable("reportId") Long reportId, @RequestBody Map<String, String> requestBody){
        Weight weight = null;
        User user = null;
        try {
            weight = JacksonUtils.toObject(requestBody.get("weight"), Weight.class);
            user = JacksonUtils.toObject(requestBody.get("user"), User.class);
            return reportUserService.addReportUser(reportId, user, weight);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据前端发送的reportId返回饼图数据
     * @param reportId 汇报id
     * @return 饼图数据
     */
    @RequestMapping(value = "/pieChart/{reportId}", method = RequestMethod.GET)
    public List<PieSeries> getPieData(@PathVariable("reportId") Long reportId){
        return chartService.getPieData(reportId);
    }

    /**
     * 根据前端发送的reportId返回柱形图数据
     * @param reportId 汇报
     * @return 柱形图数据
     */
    @RequestMapping(value = "/listChart/{reportId}", method = RequestMethod.GET)
    public ListChartData getListData(@PathVariable("reportId") Long reportId){
        return chartService.getListData(reportId);
    }

    /**
     * 根据前端发送的汇报id和等级类型, 返回有关班级打分情况的柱形图数据
     * @return 柱形图数据
     */
    @RequestMapping(value = "/listChart/{reportId}", method = RequestMethod.POST)
    public ListChartData getListData(@PathVariable("reportId") Long reportId, @RequestBody Map<String, String> requestBody){
        return chartService.getListDataByTypes(reportId, requestBody.get("types"));
    }
}
