package com.ydl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydl.constant.RedisConstant;
import com.ydl.dao.SetMealDao;
import com.ydl.entity.PageResult;
import com.ydl.entity.QueryPageBean;
import com.ydl.pojo.Setmeal;
import com.ydl.service.QiNiuService;
import com.ydl.service.SetMealService;
import com.ydl.utils.QiNiuCloudUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: SayHello
 * @Date: 2023/3/1 19:59
 * @Introduction:
 */
@Service(interfaceClass = SetMealService.class)
@Slf4j
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    SetMealDao setMealDao;

    @Autowired
    JedisPool jedisPool;

    @Reference
    QiNiuService qiNiuService;

    @Autowired
    FreeMarkerConfigurer freemarkerConfig;

    @Value("${out_put_path}")
    String out;

    @Override
    public void add(Setmeal setmeal, Integer[] checkGroupIds) {
        //1、套餐表添加数据
        setMealDao.add(setmeal);

        //2、套餐表和检查组关联表添加数据
        resetContact(setmeal.getId(), checkGroupIds);

        //3、将图片名存入redis中
        Jedis jedis = jedisPool.getResource();
        jedis.sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());

        //生成静态化页面
        generateMobileStaticHtml();
    }

    private void generateMobileStaticHtml() {
        //1、套餐列表页静态化（单个）
        //获取所有套餐信息列表
        List<Setmeal> setMealList = setMealDao.getAll();
        generateMobileSetMealListHtml(setMealList);


        //2、套餐详情页静态化（多个）
        //获取所有套餐信息列表
        generateMobileSetMealDetailHtml(setMealList);
    }


    /**
     * 生成套餐列表静态化页面
     *
     * @param setMealList 套餐列表信息
     */
    public void generateMobileSetMealListHtml(List<Setmeal> setMealList) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("setmealList", setMealList);
        generateHtmlByFreemarker("mobile_setmeal.ftl", "m_setmeal.html", dataMap);
    }

    /**
     * 生成套餐详情静态化页面
     *
     * @param setMealList 套餐列表信息+包含的检查组信息+包含的检查项信息
     */
    public void generateMobileSetMealDetailHtml(List<Setmeal> setMealList) {
        for (Setmeal setmeal : setMealList) {
            Map<String, Object> dataMap = new HashMap<>();
            setmeal = setMealDao.findByIdPro(setmeal.getId());
            dataMap.put("setmeal", setmeal);
            generateHtmlByFreemarker(
                    "mobile_setmeal_detail.ftl",
                    "setmeal_detail_" + setmeal.getId() + ".html",
                    dataMap);
        }
    }


    /**
     * 生成静态化html页面的通用方法
     *
     * @param templateName 模板页面名
     * @param htmlPageName 最终生成的html页面名
     * @param dataMap      数据
     */
    public void generateHtmlByFreemarker(String templateName, String htmlPageName, Map<String, Object> dataMap) {
        //【模板+数据=文本】
        //1、获取模板
        Configuration cfg = freemarkerConfig.getConfiguration();
        Template template = null;
        Writer writer = null;
        try {
            template = cfg.getTemplate(templateName);

            //2、添加数据
            //3、创建静态页面，输出数据
            writer = new FileWriter(new File(out + "\\" + htmlPageName));
            template.process(dataMap, writer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //4、关闭流
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<Setmeal> page = setMealDao.queryByConditionPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public Setmeal findById(Integer id) {
        return setMealDao.findById(id);
    }

    @Override
    public List<Integer> findCheckGroupIdBySetMealId(Integer id) {
        return setMealDao.findCheckGroupIdBySetMealId(id);
    }

    @Override
    public void update(Setmeal setmeal, Integer[] checkGroupIds) {
        //查询原套餐信息
        Setmeal oldSetMeal = setMealDao.findById(setmeal.getId());
        String oldImg = oldSetMeal.getImg();
        if (!setmeal.getImg().equals(oldImg)) {
            //4、将图片名存入redis中
            Jedis jedis = jedisPool.getResource();
            jedis.sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
        }
        //1、修改套餐基本信息
        setMealDao.update(setmeal);

        //2、清空该套餐所包含的检查组映射
        setMealDao.deleteAssociation(setmeal.getId());

        //3、重新添加该套餐所包含的检查组映射
        resetContact(setmeal.getId(), checkGroupIds);

        //生成静态化页面
        generateMobileStaticHtml();
    }

    @Override
    @Transactional()
    public void delete(Integer id) {
        String img = setMealDao.findById(id).getImg();
        //1、删除套餐包含的检查组映射
        setMealDao.deleteAssociation(id);

        //2、删除套餐信息
        setMealDao.delete(id);

        Jedis jedis = jedisPool.getResource();
        jedis.srem(RedisConstant.SETMEAL_PIC_RESOURCES, img);
        jedis.srem(RedisConstant.SETMEAL_PIC_DB_RESOURCES, img);

        //3、删除redis和七牛云包含的信息
        QiNiuCloudUtil.deleteFileFrom2QiNiu(img);

        //生成静态化页面
        generateMobileStaticHtml();
    }

    @Override
    public List<Setmeal> getAll() {
        return setMealDao.getAll();
    }

    @Override
    public Setmeal findByIdPro(Integer id) {
        return setMealDao.findByIdPro(id);
    }

    private void resetContact(Integer setMealId, Integer[] checkGroupIds) {
        Map<String, Integer> map = null;
        for (Integer checkGroupId : checkGroupIds) {
            map = new ConcurrentHashMap<>();
            map.put("setmealid", setMealId);
            map.put("checkgroupid", checkGroupId);
            setMealDao.setSetMealAndCheckGroup(map);
        }
    }
}
