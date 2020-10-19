package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FAreaEntity;


import java.util.List;
import java.util.Map;

/**
 * 地区表
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-11 10:14:18
 */
public interface FAreaService extends IService<FAreaEntity> {

    List<FAreaEntity> query(Map<String, Object> params);

    List<FAreaEntity> treeList();
}

