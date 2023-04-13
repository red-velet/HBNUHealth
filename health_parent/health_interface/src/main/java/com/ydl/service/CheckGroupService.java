package com.ydl.service;

import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.pojo.CheckGroup;

import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/3/1 11:16
 * @Introduction:
 */
public interface CheckGroupService {
    /**
     * 分页+条件查询 查找数据
     *
     * @param queryPageBean 分页+条件查询信息
     * @return 数据集合
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 添加检查组信息
     *
     * @param checkGroup   检查组信息
     * @param checkItemIds 勾选的检查项信息
     */
    void add(CheckGroup checkGroup, Integer[] checkItemIds);

    /**
     * 根据id查询检查组的信息
     *
     * @param id 检查组id
     * @return 检查组的信息实体类
     */
    CheckGroup findById(Integer id);

    /**
     * 通过检查组id，查询该组id包含的检查项id
     *
     * @param id 检查组id
     * @return 包含的检查项id集合
     */
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    /**
     * 修改检查组信息
     *
     * @param checkGroup   检查组信息
     * @param checkItemIds 勾选的检查项信息
     */
    void update(CheckGroup checkGroup, Integer[] checkItemIds);

    /**
     * 删除检查组
     *
     * @param id 检查组id
     */
    void delete(Integer id);

    /**
     * 查询所有检查组
     *
     * @return 检查组集合
     */
    List<CheckGroup> findAll();
}
