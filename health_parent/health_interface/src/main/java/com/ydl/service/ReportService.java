package com.ydl.service;

import java.util.List;
import java.util.Map;

/**
 * @Author: SayHello
 * @Date: 2023/3/5 19:05
 * @Introduction:
 */
public interface ReportService {
    List<Integer> findMemberCountByMonth(List<String> months);

    List<Map<String, Object>> findSetMealCount();

    Map<String, Object> getBusinessReportData() throws Exception;

}
