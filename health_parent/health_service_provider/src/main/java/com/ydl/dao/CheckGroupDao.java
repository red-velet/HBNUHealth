package com.ydl.dao;

import com.github.pagehelper.Page;
import com.ydl.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: SayHello
 * @Date: 2023/3/1 11:21
 * @Introduction:
 */
public interface CheckGroupDao {

    Page<CheckGroup> queryByConditionPage(@Param("queryString") String queryString);

    void add(CheckGroup checkGroup);

    /**
     * @param map
     */
    void setCheckGroupAndCheckItem(Map<String, Integer> map);

    CheckGroup findById(@Param("id") Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(@Param("id") Integer id);

    void update(CheckGroup checkGroup);

    void deleteAssociation(@Param("id") Integer id);

    void delete(@Param("id") Integer id);

    List<CheckGroup> findAll();
}
