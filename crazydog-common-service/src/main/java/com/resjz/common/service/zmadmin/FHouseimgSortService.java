package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FHouseimgSortEntity;
import com.resjz.common.utils.PageUtils;


import java.util.Map;

/**
 * 楼盘相册表（楼盘图片分类表）
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-12 11:27:27
 */
public interface FHouseimgSortService extends IService<FHouseimgSortEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

