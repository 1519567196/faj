package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FProgrammeVrEntity;
import com.resjz.common.utils.PageUtils;

import java.util.Map;

/**
 * 我家方案-全景
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FProgrammeVrService extends IService<FProgrammeVrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

