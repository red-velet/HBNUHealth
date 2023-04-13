package com.ydl.controller;

import com.ydl.constant.MessageConstant;
import com.ydl.entity.Result;
import com.ydl.pojo.Role;
import com.ydl.service.RoleService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/3/5 17:04
 * @Introduction:
 */
@RestController
@RequestMapping("role")
public class RoleController {
    @Reference
    RoleService roleService;

    @GetMapping("findAll")
    public Result findAll() {
        List<Role> list = roleService.findAll();
        return new Result(true, MessageConstant.GET_ROLE_USER_SUCCESS, list);
    }
}
