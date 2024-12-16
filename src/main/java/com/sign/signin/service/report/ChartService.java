package com.sign.signin.service.report;

import com.sign.signin.bean.ListChartData;
import com.sign.signin.bean.PieSeries;

import java.util.List;

public interface ChartService {
    List<PieSeries> getPieData(Long reportId);
    ListChartData getListData(Long reportId);
    ListChartData getListDataByTypes(Long reportId, String typesStr);
}
