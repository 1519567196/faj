package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FAdPlaceEntity;
import com.resjz.common.dao.zmadmin.entity.FAdvertEntity;
import com.resjz.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 广告
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FAdvertService extends IService<FAdvertEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<FAdPlaceEntity> selectAll();
}

