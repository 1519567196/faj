package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FArticleTypeEntity;
import com.resjz.common.utils.PageUtils;
//import com.resjz.sys.entity.FArticleTypeEntity;

import java.util.Map;

/**
 * 材建资讯分类
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-18 08:05:45
 */
public interface FArticleTypeService extends IService<FArticleTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

