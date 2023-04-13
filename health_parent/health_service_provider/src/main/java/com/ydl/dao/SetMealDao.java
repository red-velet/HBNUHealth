package com.ydl.dao;

import com.github.pagehelper.Page;
import com.ydl.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: SayHello
 * @Date: 2023/3/1 20:00
 * @Introduction:
 */
public interface SetMealDao {

    void add(Setmeal setmeal);

    void setSetMealAndCheckGroup(Map<String, Integer> map);

    Page<Setmeal> queryByConditionPage(@Param("queryString") String queryString);

    Setmeal findById(@Param("id") Integer id);

    List<Integer> findCheckGroupIdBySetMealId(@Param("id") Integer id);

    void update(Setmeal setmeal);

    void deleteAssociation(@Param("id") Integer id);

    void delete(@Param("id") Integer id);

    List<Setmeal> getAll();

    Setmeal findByIdPro(@Param("id") Integer id);
}
