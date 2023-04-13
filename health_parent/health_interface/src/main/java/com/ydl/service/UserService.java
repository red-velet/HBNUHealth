package com.ydl.service;

import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.pojo.User;

/**
 * @Author: SayHello
 * @Date: 2023/3/4 16:33
 * @Introduction:
 */
public interface UserService {
    /**
     * 通过用户名查找用户信息
     *
     * @param username 用户名
     * @return 用户信息实体类
     */
    User findByUsername(String username);

    /**
     * 分页查询用户信息
     *
     * @param queryPageBean 条件
     * @return 用户信息集合
     */
    PageResult findByCase(QueryPageBean queryPageBean);

}
