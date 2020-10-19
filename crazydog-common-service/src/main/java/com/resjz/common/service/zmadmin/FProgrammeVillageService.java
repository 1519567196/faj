package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FProgrammeVillageEntity;
import com.resjz.common.utils.PageUtils;

import java.util.Map;

/**
 * 我家方案-小区
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FProgrammeVillageService extends IService<FProgrammeVillageEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils listPage(Map<String, Object> params);
}

