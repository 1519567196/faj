package com.resjz.common.dao.zmadmin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.resjz.common.dao.zmadmin.entity.FMaterialBrandEntity;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * 材建-品牌
 * 
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FMaterialBrandDao extends BaseMapper<FMaterialBrandEntity> {
   List<FMaterialBrandEntity>  selectByBrandId(@Param("materialBrandId")Integer materialBrandId);
}
