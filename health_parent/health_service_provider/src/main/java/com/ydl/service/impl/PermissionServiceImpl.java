package com.ydl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydl.dao.PermissionDao;
import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.pojo.Permission;
import com.ydl.service.PermissionService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: SayHello
 * @Date: 2023/3/5 17:13
 * @Introduction:
 */
@Service(interfaceClass = PermissionService.class)
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionDao permissionDao;

    @Override
    public PageResult findAll(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<Permission> list = permissionDao.findAll(queryPageBean.getQueryString());
        return new PageResult(list.getTotal(), list.getResult());
    }
}
