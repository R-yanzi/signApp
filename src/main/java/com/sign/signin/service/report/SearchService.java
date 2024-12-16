package com.sign.signin.service.report;

import com.sign.signin.bean.Search;

import java.util.List;

public interface SearchService {
    List<Search> getSearchWithReportId(Long reportId, String search);
}
