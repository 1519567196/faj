package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FAdPlaceDao;
import com.resjz.common.dao.zmadmin.entity.FAdPlaceEntity;
import com.resjz.common.service.zmadmin.FAdPlaceService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;




@Service("fAdPlaceService")
public class FAdPlaceServiceImpl extends ServiceImpl<FAdPlaceDao, FAdPlaceEntity> implements FAdPlaceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FAdPlaceEntity> page = this.selectPage(
                new Query<FAdPlaceEntity>(params).getPage(),
                new EntityWrapper<FAdPlaceEntity>()
                .orderBy("itemid",false)
        );

        return new PageUtils(page);
    }

}
