package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FProgrammeHouseEntity;
import com.resjz.common.utils.PageUtils;

import java.util.Map;

/**
 * 我加方案-户型表
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FProgrammeHouseService extends IService<FProgrammeHouseEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils listPage(Map<String, Object> params);
}

