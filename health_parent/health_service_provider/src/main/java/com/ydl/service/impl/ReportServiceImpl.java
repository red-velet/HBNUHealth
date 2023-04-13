package com.ydl.service.impl;

import com.ydl.dao.MemberDao;
import com.ydl.dao.ReportDao;
import com.ydl.service.ReportService;
import com.ydl.utils.DateUtil;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @Author: SayHello
 * @Date: 2023/3/5 19:07
 * @Introduction:
 */
@Service(interfaceClass = ReportService.class)
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportDao reportDao;

    @Autowired
    MemberDao memberDao;

    @Override
    public List<Integer> findMemberCountByMonth(List<String> months) {
        //[2022-03,2022-04,2022-05.......]
        ArrayList<Integer> list = new ArrayList<>();
        for (String month : months) {
            Integer count = reportDao.findMemberCountByDate(month);
            list.add(count);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> findSetMealCount() {
        return reportDao.findSetMealCount();
    }

    @Override
    public Map<String, Object> getBusinessReportData() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        //获取当天日期字符串
        String reportDate = DateUtil.parseDate2String(new Date());
        resultMap.put("reportDate", reportDate);
        //今日新增会员数
        Integer todayNewNumber = memberDao.findMemberCountByDate(reportDate);
        resultMap.put("todayNewNumber", todayNewNumber);
        //总会员数
        Integer totalNumber = memberDao.findMemberTotalCount();
        resultMap.put("totalNumber", totalNumber);
        //本周新增会员数
        
        return resultMap;
    }
}
