package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrEntity;
import com.resjz.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 属性标签
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FCommonAttrService extends IService<FCommonAttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<FCommonAttrEntity> selectList(Map<String, Object> params);
    List<FCommonAttrEntity> selectListCopy(Map<String, Object> params);
//    PageUtils onlylist(Map<String, Object> params);
}

