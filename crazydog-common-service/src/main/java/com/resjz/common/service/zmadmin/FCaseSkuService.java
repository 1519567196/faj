package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FCaseSkuEntity;
import com.resjz.common.utils.PageUtils;

import java.util.Map;

/**
 * 案例规格属性表
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FCaseSkuService extends IService<FCaseSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

