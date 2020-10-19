package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FJobDao;
import com.resjz.common.dao.zmadmin.entity.FJobEntity;
import com.resjz.common.service.zmadmin.FJobService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("fJobService")
public class FJobServiceImpl extends ServiceImpl<FJobDao, FJobEntity> implements FJobService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FJobEntity> page = this.selectPage(
                new Query<FJobEntity>(params).getPage(),
                new EntityWrapper<FJobEntity>()
                .orderBy("iobId",false)
        );

        return new PageUtils(page);
    }

}
