package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FMaterialBrandEntity;
import com.resjz.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

/**
 * 材建-品牌
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FMaterialBrandService extends IService<FMaterialBrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<FMaterialBrandEntity>  selectByBrandId(@Param("materialBrandId")Integer materialBrandId);
}

