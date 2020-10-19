package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FNewsDao;
import com.resjz.common.dao.zmadmin.entity.FNewsEntity;
import com.resjz.common.service.zmadmin.FNewsService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("fNewsService")
public class FNewsServiceImpl extends ServiceImpl<FNewsDao, FNewsEntity> implements FNewsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FNewsEntity> page = this.selectPage(
                new Query<FNewsEntity>(params).getPage(),
                new EntityWrapper<FNewsEntity>()
                .orderBy("itemid",false)
        );

        return new PageUtils(page);
    }

}
