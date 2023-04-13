package com.ydl.service;

import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.pojo.Setmeal;

import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/3/1 19:57
 * @Introduction:
 */
public interface SetMealService {
    /**
     * 新增套餐信息
     *
     * @param setmeal       套餐的基本信息
     * @param checkGroupIds 改套餐所包含的检查组
     */
    void add(Setmeal setmeal, Integer[] checkGroupIds);

    /**
     * 该方法用于分页+条件查询套餐信息
     *
     * @param queryPageBean 分页+查询条件
     * @return 套餐信息集合
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 通过套餐id查询该套餐信息
     *
     * @param id 套餐id
     * @return 套餐信息实体类
     */
    Setmeal findById(Integer id);

    /**
     * 通过套餐id查询所包含的检查组
     *
     * @param id 套餐id
     * @return 检查组id集合
     */
    List<Integer> findCheckGroupIdBySetMealId(Integer id);

    /**
     * 修改套餐信息
     *
     * @param setmeal       套餐信息
     * @param checkGroupIds 该套餐所包含的检查组
     */
    void update(Setmeal setmeal, Integer[] checkGroupIds);

    /**
     * 删除套餐信息
     *
     * @param id 当前套餐id
     */
    void delete(Integer id);

    /**
     * 查询所有套餐信息
     *
     * @return 套餐列表
     */
    List<Setmeal> getAll();

    /**
     * 通过id查询套餐的详细信息
     *
     * @param id 套餐id
     * @return 套餐基本信息【包含的检查组【包含的检查项】】
     */
    Setmeal findByIdPro(Integer id);
}
