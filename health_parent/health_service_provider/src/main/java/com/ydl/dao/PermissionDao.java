package com.ydl.dao;

import com.github.pagehelper.Page;
import com.ydl.pojo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @Author: SayHello
 * @Date: 2023/3/4 16:43
 * @Introduction:
 */
public interface PermissionDao {
    Set<Permission> findByRoleId(@Param("id") Integer roleId);

    Page<Permission> findAll(@Param("queryString") String queryString);
}
