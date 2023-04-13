package com.ydl.service;

import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;

/**
 * @Author: SayHello
 * @Date: 2023/3/5 17:12
 * @Introduction:
 */
public interface PermissionService {

    /**
     * 查询所有权限信息
     *
     * @param queryPageBean 查询条件
     * @return 所有权限信息集合
     */
    PageResult findAll(QueryPageBean queryPageBean);
}
