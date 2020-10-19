package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FCaseSkuDao;
import com.resjz.common.dao.zmadmin.entity.FCaseSkuEntity;
import com.resjz.common.service.zmadmin.FCaseSkuService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;




@Service("fCaseSkuService")
public class FCaseSkuServiceImpl extends ServiceImpl<FCaseSkuDao, FCaseSkuEntity> implements FCaseSkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FCaseSkuEntity> page = this.selectPage(
                new Query<FCaseSkuEntity>(params).getPage(),
                new EntityWrapper<FCaseSkuEntity>()
        );

        return new PageUtils(page);
    }

}
