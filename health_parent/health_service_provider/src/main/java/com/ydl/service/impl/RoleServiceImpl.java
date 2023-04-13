package com.ydl.service.impl;

import com.ydl.dao.RoleDao;
import com.ydl.pojo.Role;
import com.ydl.service.RoleService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/3/5 17:09
 * @Introduction:
 */
@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
