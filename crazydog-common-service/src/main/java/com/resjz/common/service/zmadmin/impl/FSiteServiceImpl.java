package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FSiteDao;
import com.resjz.common.dao.zmadmin.entity.FSiteEntity;
import com.resjz.common.service.zmadmin.FSiteService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("fSiteService")
public class FSiteServiceImpl extends ServiceImpl<FSiteDao, FSiteEntity> implements FSiteService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FSiteEntity> page = this.selectPage(
                new Query<FSiteEntity>(params).getPage(),
                new EntityWrapper<FSiteEntity>()
                .orderBy("itemid",false)
        );

        return new PageUtils(page);
    }

}
