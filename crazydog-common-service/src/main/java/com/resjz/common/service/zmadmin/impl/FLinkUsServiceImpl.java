package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FLinkUsDao;
import com.resjz.common.dao.zmadmin.entity.FLinkUsEntity;
import com.resjz.common.service.zmadmin.FLinkUsService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("fLinkUsService")
public class FLinkUsServiceImpl extends ServiceImpl<FLinkUsDao, FLinkUsEntity> implements FLinkUsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FLinkUsEntity> page = this.selectPage(
                new Query<FLinkUsEntity>(params).getPage(),
                new EntityWrapper<FLinkUsEntity>()
                .orderBy("itemid",false)
        );

        return new PageUtils(page);
    }

}
