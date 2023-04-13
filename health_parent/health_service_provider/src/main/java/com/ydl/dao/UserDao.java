package com.ydl.dao;

import com.github.pagehelper.Page;
import com.ydl.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: SayHello
 * @Date: 2023/3/4 16:43
 * @Introduction:
 */
public interface UserDao {
    User findByUsername(@Param("username") String username);

    Page<User> findByCase(@Param("username") String queryString);
}
