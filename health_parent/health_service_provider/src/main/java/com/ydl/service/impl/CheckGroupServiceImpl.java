package com.ydl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydl.dao.CheckGroupDao;
import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.pojo.CheckGroup;
import com.ydl.service.CheckGroupService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: SayHello
 * @Date: 2023/3/1 11:20
 * @Introduction:
 */
@Service(interfaceClass = CheckGroupService.class)
@Slf4j
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    CheckGroupDao checkGroupDao;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<CheckGroup> page = checkGroupDao.queryByConditionPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    public void update(CheckGroup checkGroup, Integer[] checkItemIds) {
        //1、t_checkgroup表修改数据
        checkGroupDao.update(checkGroup);

        //2、清空t_checkgroup_checkitem关联表内与改组id相关联的数据
        checkGroupDao.deleteAssociation(checkGroup.getId());

        //3、t_checkgroup_checkitem关联表添加数据
        resetContact(checkGroup, checkItemIds);
    }

    @Override
    public void delete(Integer id) {
        //1、清空t_checkgroup_checkitem关联表内与改组id相关联的数据
        checkGroupDao.deleteAssociation(id);

        //2、删除t_checkgroup表数据
        checkGroupDao.delete(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

    private void resetContact(CheckGroup checkGroup, Integer[] checkItemIds) {
        Map<String, Integer> map = null;
        for (Integer checkItemId : checkItemIds) {
            map = new ConcurrentHashMap<>();
            map.put("checkgroupid", checkGroup.getId());
            map.put("checkitemid", checkItemId);
            checkGroupDao.setCheckGroupAndCheckItem(map);
        }
    }

    @Override
    public void add(CheckGroup checkGroup, Integer[] checkItemIds) {
        //1、t_checkgroup表添加数据
        checkGroupDao.add(checkGroup);

        //2、t_checkgroup_checkitem关联表添加数据
        resetContact(checkGroup, checkItemIds);
    }
}
