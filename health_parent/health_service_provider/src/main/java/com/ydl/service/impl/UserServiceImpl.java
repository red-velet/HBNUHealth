package com.ydl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydl.dao.PermissionDao;
import com.ydl.dao.RoleDao;
import com.ydl.dao.UserDao;
import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.pojo.Permission;
import com.ydl.pojo.Role;
import com.ydl.pojo.User;
import com.ydl.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @Author: SayHello
 * @Date: 2023/3/4 16:34
 * @Introduction:
 */
@Service(interfaceClass = UserService.class)
@Transactional
@SuppressWarnings("all")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return null;
        }
        Integer userId = user.getId();
        //根据用户id查询用户角色
        Set<Role> roles = roleDao.findByUserId(userId);
        if (roles != null && roles.size() > 0) {
            for (Role role : roles) {
                Integer roleId = role.getId();
                //根据用户角色id查询用户权限
                Set<Permission> permissions = permissionDao.findByRoleId(roleId);
                if (permissions != null && permissions.size() > 0) {
                    role.setPermissions(permissions);
                }
            }
            user.setRoles(roles);
        }
        return user;
    }

    @Override
    public PageResult findByCase(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<User> list = userDao.findByCase(queryPageBean.getQueryString());
        return new PageResult(list.getTotal(), list.getResult());
    }
}
