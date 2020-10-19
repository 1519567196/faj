package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FMaterialTypeEntity;
import com.resjz.common.utils.PageUtils;

import java.util.Map;

/**
 * 建材分类
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FMaterialTypeService extends IService<FMaterialTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

