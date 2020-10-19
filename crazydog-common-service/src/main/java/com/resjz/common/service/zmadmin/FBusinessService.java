package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FBusinessEntity;
import com.resjz.common.utils.PageUtils;

import java.util.Map;

/**
 * 商务合作
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FBusinessService extends IService<FBusinessEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

