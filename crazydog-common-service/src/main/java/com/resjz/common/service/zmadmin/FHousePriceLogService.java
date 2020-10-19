package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FHousePriceLogEntity;
import com.resjz.common.utils.PageUtils;


import java.util.Map;

/**
 * 房价走势记录表
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-17 17:54:21
 */
public interface FHousePriceLogService extends IService<FHousePriceLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

