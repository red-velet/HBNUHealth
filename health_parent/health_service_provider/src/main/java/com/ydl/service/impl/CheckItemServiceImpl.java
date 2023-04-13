package com.ydl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydl.dao.CheckItemDao;
import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.pojo.CheckItem;
import com.ydl.service.CheckItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/2/27 10:25
 * @Introduction:
 */
@Service(interfaceClass = CheckItemService.class)
@Slf4j
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //pageHelper分页 threadLocal
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<CheckItem> pages = checkItemDao.queryByConditionPage(queryPageBean.getQueryString());
        return new PageResult(pages.getTotal(), pages.getResult());
    }

    @Override
    public void delete(Integer id) {
        //检查检查项是否已被检查组引用
        long isReference = checkItemDao.findCountByCheckItemId(id);
        if (isReference > 0) {
            throw new RuntimeException("该检查项目被引用,无法删除!!!");
        }
        checkItemDao.delete(id);
    }

    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.queryById(id);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.queryAll();
    }
}
