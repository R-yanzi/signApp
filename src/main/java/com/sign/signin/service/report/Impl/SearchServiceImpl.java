package com.sign.signin.service.report.Impl;

import com.sign.signin.bean.Search;
import com.sign.signin.bean.User;
import com.sign.signin.bean.UserReport;
import com.sign.signin.service.report.SearchService;
import com.sign.signin.service.report.UserReportService;
import com.sign.signin.service.report.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserReportService userReportService;

    /**
     * 根据关键词和汇报id查找有关关键词列表
     * @param reportId 汇报id
     * @param search 搜索关键词
     * @return 关键词列表
     */
    @Override
    public List<Search> getSearchWithReportId(Long reportId, String search) {
        // 首先获取所有该次报告已打分的学生成绩列表
        List<UserReport> userReports = userReportService.getUserReport(reportId);
        // 学号集合
        List<String> ids = new ArrayList<>();
        // 等级集合
        HashSet<String> typeSet = new HashSet<>();
        // 存放最终搜索到的内容
        Deque<Search> answer = new LinkedList<>();
        // 去重集合
        HashSet<String> usernameSet = new HashSet<>();
        HashSet<String> classnameSet = new HashSet<>();
        HashSet<String> useridSet = new HashSet<>();
        // 遍历获得有关学号集合和等级集合
        for (UserReport userReport : userReports) {
            ids.add(userReport.getUser().getUserid());
            String type = userReport.getType();
            if (type != null && !type.isEmpty())
                typeSet.add(type);
        }
        // 查到的所有有关学生列表
        List<User> userInIdList = userService.getUserInIdList(ids);
        for (User user : userInIdList) {
            String username = user.getUsername();
            String classname = user.getClassname();
            String userid = user.getUserid();
            // 查名字
            if (!usernameSet.contains(username)) {
                addAnswer(search, answer, username);
                usernameSet.add(username);
            }
            // 查班级
            if (!classnameSet.contains(classname)) {
                addAnswer(search, answer, classname);
                classnameSet.add(classname);
            }
            // 查学号
            if (!useridSet.contains(userid)) {
                addAnswer(search, answer, userid);
                useridSet.add(userid);
            }
        }
        // 查成绩
        for (String type : typeSet)
            addAnswer(search, answer, type);

        //  控制数量
        List<Search> result = new ArrayList<>(answer.stream().toList());
        // 排序
        result.sort((o1, o2) -> {
            if (o1.getPrefix().length() == o2.getPrefix().length()) {
                String a1 = o1.getPrefix() + o1.getHighlight() + o1.getSuffix();
                String a2 = o2.getPrefix() + o2.getHighlight() + o2.getSuffix();
                return a1.compareTo(a2);
            }
            return o1.getPrefix().length() - o2.getPrefix().length();
        });
        if (result.size() > 10) {
            List<Search> searches = new ArrayList<>();
            for (int i = 0; i < 10; i++)
                searches.add(result.get(i));
            return searches;
        }
        return result;
    }

    /**
     * 添加关键词列表工具方法
     */
    private void addAnswer(@PathVariable("search") String search, Deque<Search> answer, String ami) {
        if (ami.contains(search)) {
            // 包含关键字
            int idx = ami.indexOf(search);
            String prefix = "";
            String suffix = "";
            // 有前缀
            if (idx != 0)
                prefix = ami.substring(0, idx);
            // 有后缀
            if (idx + search.length() < ami.length())
                suffix = ami.substring(idx + search.length());
            if (prefix.isEmpty())
                answer.addFirst(new Search(prefix, search, suffix));
            else
                answer.addLast(new Search(prefix, search, suffix));
        }
    }

}
