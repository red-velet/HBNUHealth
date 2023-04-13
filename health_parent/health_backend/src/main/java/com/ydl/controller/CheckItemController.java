package com.ydl.controller;

import com.ydl.constant.MessageConstant;
import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.entity.Result;
import com.ydl.pojo.CheckItem;
import com.ydl.service.CheckItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/2/27 10:11
 * @Introduction:
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    CheckItemService checkItemService;

    /**
     * 添加检查项
     *
     * @param checkItem 检查项信息
     * @return 添加的结果
     */
    @PostMapping("add")
    public Result add(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.add(checkItem);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            //打印错误日志
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }

    /**
     * 分页查询满足条件的检查项
     *
     * @param queryPageBean 起始页、数据条数、查询条件
     * @return 查询的结果
     */
    @PostMapping("findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkItemService.findPage(queryPageBean);
    }

    /**
     * 根据检查项id,删除检查项
     *
     * @param id 检查项id
     * @return 删除的结果
     */
    @GetMapping("delete")
    public Result delete(@RequestParam Integer id) {
        try {
            checkItemService.delete(id);
            return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            //打印错误日志
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL, e.getMessage());
        }
    }

    /**
     * 修改检查项
     *
     * @param checkItem 重新填写的检查项信息
     * @return 修改的结果
     */
    @PostMapping("edit")
    public Result update(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.update(checkItem);
            return new Result(true, MessageConstant.EDIT_MEMBER_SUCCESS);
        } catch (Exception e) {
            //打印错误日志
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_MEMBER_FAIL, e.getMessage());
        }
    }

    /**
     * 根据检查项id,查找检查项信息
     *
     * @param id 检查项id
     * @return 查询到的检查项结果
     */
    @GetMapping("findById")
    public Result findById(@RequestParam Integer id) {
        try {
            CheckItem checkItem = checkItemService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
        } catch (Exception e) {
            //打印错误日志
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL, e.getMessage());
        }
    }

    /**
     * 查找所有检查项
     *
     * @return 所有检查项集合
     */
    @GetMapping("findAll")
    public Result findAll() {
        try {
            List<CheckItem> list = checkItemService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, list);
        } catch (Exception e) {
            return new Result(true, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    
}
