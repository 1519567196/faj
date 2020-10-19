package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FFitupCompanySkuEntity;
import com.resjz.common.dao.zmadmin.entity.FHouseSkuEntity;
import com.resjz.common.utils.PageUtils;


import java.util.List;
import java.util.Map;

/**
 * 装修公司装规格属性-属性值表
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-24 18:13:36
 */
public interface FFitupCompanySkuService extends IService<FFitupCompanySkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    int addSkus(List<FFitupCompanySkuEntity> list);
}

