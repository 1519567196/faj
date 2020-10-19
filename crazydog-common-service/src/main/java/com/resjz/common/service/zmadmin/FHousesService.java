package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FHousesEntity;
import com.resjz.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 楼盘
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FHousesService extends IService<FHousesEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils listPage(Map<String, Object> params);
    List<FHousesEntity> query();

    FHousesEntity getIndex(Map<String, Object> params);
}

