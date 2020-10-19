package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FBusinessDao;
import com.resjz.common.dao.zmadmin.entity.FBusinessEntity;
import com.resjz.common.service.zmadmin.FBusinessService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;




@Service("fBusinessService")
public class FBusinessServiceImpl extends ServiceImpl<FBusinessDao, FBusinessEntity> implements FBusinessService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FBusinessEntity> page = this.selectPage(
                new Query<FBusinessEntity>(params).getPage(),
                new EntityWrapper<FBusinessEntity>()
                .orderBy("itemid",false)
        );

        return new PageUtils(page);
    }

}
