package com.ydl.controller;

import com.ydl.constant.MessageConstant;
import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.entity.Result;
import com.ydl.pojo.CheckGroup;
import com.ydl.service.CheckGroupService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/3/1 11:15
 * @Introduction:
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    CheckGroupService checkGroupService;

    /**
     * 分页条件查询检查组
     *
     * @param queryPageBean 分页信息和查询条件
     * @return 查询结果
     */
    @PostMapping("findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkGroupService.findPage(queryPageBean);
    }

    /**
     * 添加检查组信息
     *
     * @param checkGroup   填写的检查组信息
     * @param checkItemIds 勾选的检查项信息
     * @return 添加结果
     */
    @PostMapping("add")
    public Result add(@RequestBody CheckGroup checkGroup, @RequestParam("checkitemIds") Integer[] checkItemIds) {
        try {
            if (checkItemIds == null) {
                checkItemIds = new Integer[]{};
            }
            checkGroupService.add(checkGroup, checkItemIds);
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            //打印错误日志
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    @GetMapping("findById")
    public Result findById(@RequestParam("id") Integer id) {
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
        } catch (Exception e) {
            //打印错误日志
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @GetMapping("findCheckItemIdsByCheckGroupId")
    public List<Integer> findCheckItemIdsByCheckGroupId(@RequestParam("id") Integer id) {
        try {
            List<Integer> list = checkGroupService.findCheckItemIdsByCheckGroupId(id);
            return list;
        } catch (Exception e) {
            //打印错误日志
            e.printStackTrace();
            ArrayList<Integer> arrayList = new ArrayList<>();
            return arrayList;
        }
    }

    /**
     * 修改检查组信息
     *
     * @param checkGroup   填写的检查组信息
     * @param checkItemIds 勾选的检查项信息
     * @return 添加结果
     */
    @PostMapping("edit")
    public Result update(@RequestBody CheckGroup checkGroup, @RequestParam("checkitemIds") Integer[] checkItemIds) {
        try {
            checkGroupService.update(checkGroup, checkItemIds);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            //打印错误日志
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    @GetMapping("delete")
    public Result delete(@RequestParam("id") Integer id) {
        try {
            checkGroupService.delete(id);
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS, e.getMessage());
        }
    }

    /**
     * 查找所有检查组
     *
     * @return 所有检查组集合
     */
    @GetMapping("findAll")
    public Result findAll() {
        try {
            List<CheckGroup> list = checkGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, list);
        } catch (Exception e) {
            return new Result(true, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }


}
