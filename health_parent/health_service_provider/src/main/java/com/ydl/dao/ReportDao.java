package com.ydl.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: SayHello
 * @Date: 2023/3/5 19:24
 * @Introduction:
 */
public interface ReportDao {
    Integer findMemberCountByDate(@Param("date") String month);

    List<Map<String, Object>> findSetMealCount();
    
}
