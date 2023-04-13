package com.ydl.controller;

import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.service.PermissionService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: SayHello
 * @Date: 2023/3/5 17:11
 * @Introduction:
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
    @Reference
    PermissionService permissionService;

    @PostMapping("findByName")
    public PageResult findByName(@RequestBody QueryPageBean queryPageBean) {
        return permissionService.findAll(queryPageBean);
    }
}
