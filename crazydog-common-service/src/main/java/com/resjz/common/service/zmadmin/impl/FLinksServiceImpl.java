package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FLinksDao;
import com.resjz.common.dao.zmadmin.entity.FLinksEntity;
import com.resjz.common.service.zmadmin.FLinksService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;




@Service("fLinksService")
public class FLinksServiceImpl extends ServiceImpl<FLinksDao, FLinksEntity> implements FLinksService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FLinksEntity> page = this.selectPage(
                new Query<FLinksEntity>(params).getPage(),
                new EntityWrapper<FLinksEntity>()
                .orderBy("itemid",false)
        );

        return new PageUtils(page);
    }

}
