package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FMaterialTypeDao;
import com.resjz.common.dao.zmadmin.entity.FMaterialTypeEntity;
import com.resjz.common.service.zmadmin.FMaterialTypeService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("fMaterialTypeService")
public class FMaterialTypeServiceImpl extends ServiceImpl<FMaterialTypeDao, FMaterialTypeEntity> implements FMaterialTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FMaterialTypeEntity> page = this.selectPage(
                new Query<FMaterialTypeEntity>(params).getPage(),
                new EntityWrapper<FMaterialTypeEntity>().eq(params.get("mainId")!=null,"main_materia_id",params.get("mainId"))
        );

        return new PageUtils(page);
    }

}
