package com.resjz.common.service.zmadmin;

import com.baomidou.mybatisplus.service.IService;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrValueEntity;
import com.resjz.common.dao.zmadmin.entity.FFitupCompanyCasesEntity;
import com.resjz.common.utils.PageUtils;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 装修公司案例
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
public interface FFitupCompanyCasesService extends IService<FFitupCompanyCasesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void  getIndexCase3(Model model,List<FCommonAttrValueEntity> list);

    PageUtils listPage(Map<String, Object> params);
    PageUtils listPage2(Map<String, Object> params);

}

