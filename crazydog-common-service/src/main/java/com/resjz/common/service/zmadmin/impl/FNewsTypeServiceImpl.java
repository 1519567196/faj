package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FNewsTypeDao;
import com.resjz.common.dao.zmadmin.entity.FNewsTypeEntity;
import com.resjz.common.service.zmadmin.FNewsTypeService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("fNewsTypeService")
public class FNewsTypeServiceImpl extends ServiceImpl<FNewsTypeDao, FNewsTypeEntity> implements FNewsTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FNewsTypeEntity> page = this.selectPage(
                new Query<FNewsTypeEntity>(params).getPage(),
                new EntityWrapper<FNewsTypeEntity>()
                .orderBy("typeid",false)
        );

        return new PageUtils(page);
    }

}
