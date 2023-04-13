package com.ydl.controller;

import com.ydl.constant.MessageConstant;
import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.entity.Result;
import com.ydl.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: SayHello
 * @Date: 2023/3/4 17:11
 * @Introduction:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Reference
    UserService userService;

    /**
     * 查询所有用户信息
     *
     * @return 用户信息集合
     */
    @PostMapping("findByCase")
    public PageResult findByCase(@RequestBody QueryPageBean queryPageBean) {
        return userService.findByCase(queryPageBean);
    }

    @GetMapping("getUsername")
    public Result getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, user.getUsername());
        }
        return new Result(false, MessageConstant.GET_USERNAME_FAIL);
    }

}
