package com.ydl.service;

import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.pojo.CheckItem;

import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/2/27 10:14
 * @Introduction:
 */
public interface CheckItemService {
    /**
     * 添加检查项
     *
     * @param checkItem 检查项信息
     */
    public void add(CheckItem checkItem);

    /**
     * 分页+条件查询 查找数据
     *
     * @param queryPageBean 分页+条件查询信息
     * @return 数据集合
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 根据检查项id，删除检查项信息
     *
     * @param id 检查项id
     */
    void delete(Integer id);

    /**
     * 修改检查项内容
     *
     * @param checkItem 重新填写的检查项内容
     */
    void update(CheckItem checkItem);

    /**
     * 根据id查找检查项信息
     *
     * @param id 检查项id
     * @return 查询到的检查项信息
     */
    CheckItem findById(Integer id);

    /**
     * 查询所有检查项
     *
     * @return 所有检查项集合
     */
    List<CheckItem> findAll();
}
