package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FHouseCommentEntity;
import com.resjz.common.utils.PageUtils;


import java.util.Map;

/**
 * 楼盘点评表
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-17 17:54:21
 */
public interface FHouseCommentService extends IService<FHouseCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

