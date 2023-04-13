package com.ydl.dao;

import com.ydl.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Author: SayHello
 * @Date: 2023/3/4 16:43
 * @Introduction:
 */
public interface RoleDao {
    Set<Role> findByUserId(@Param("id") Integer userId);

    List<Role> findAll();

}
