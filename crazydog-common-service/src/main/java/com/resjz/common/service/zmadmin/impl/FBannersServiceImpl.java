package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FBannersDao;
import com.resjz.common.dao.zmadmin.entity.FBannersEntity;
import com.resjz.common.service.zmadmin.FBannersService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;





@Service("fBannersService")
public class FBannersServiceImpl extends ServiceImpl<FBannersDao, FBannersEntity> implements FBannersService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FBannersEntity> page = this.selectPage(
                new Query<FBannersEntity>(params).getPage(),
                new EntityWrapper<FBannersEntity>()
                .orderBy("itemid",false)
        );

        return new PageUtils(page);
    }

}
