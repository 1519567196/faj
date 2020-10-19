package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FHouseImagesEntity;
import com.resjz.common.utils.PageUtils;

import java.util.Map;

/**
 * 楼盘相册
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FHouseImagesService extends IService<FHouseImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

