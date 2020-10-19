package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FHouseSkuEntity;
import com.resjz.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 楼盘规格属性
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FHouseSkuService extends IService<FHouseSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);


    int addSkus(List<FHouseSkuEntity>  list);
}

