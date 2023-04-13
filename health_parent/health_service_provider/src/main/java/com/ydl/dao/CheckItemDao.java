package com.ydl.dao;

import com.github.pagehelper.Page;
import com.ydl.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/2/27 10:28
 * @Introduction:
 */
public interface CheckItemDao {
    /**
     * 添加一条条检查项到数据库表中
     *
     * @param checkItem 检查项实体
     */
    void add(CheckItem checkItem);

    Page<CheckItem> queryByConditionPage(@Param("queryString") String queryString);

    void delete(@Param("id") Integer id);

    long findCountByCheckItemId(@Param("id") Integer id);

    void update(CheckItem checkItem);

    CheckItem queryById(@Param("id") Integer id);

    List<CheckItem> queryAll();
}
