package com.resjz.common.service.zmadmin.impl;

import com.resjz.common.dao.zmadmin.dao.FHousePriceLogDao;
import com.resjz.common.dao.zmadmin.entity.FHousePriceLogEntity;
import com.resjz.common.dao.zmadmin.entity.FHousesEntity;
import com.resjz.common.service.zmadmin.FHousePriceLogService;
import com.resjz.common.service.zmadmin.FHousesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;


@Service("fHousePriceLogService")
public class FHousePriceLogServiceImpl extends ServiceImpl<FHousePriceLogDao, FHousePriceLogEntity> implements FHousePriceLogService {

    @Autowired
    private FHousesService fHousesService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FHousePriceLogEntity> page = this.selectPage(
                new Query<FHousePriceLogEntity>(params).getPage(),
                new EntityWrapper<FHousePriceLogEntity>()
        );


        for (FHousePriceLogEntity f:page.getRecords()) {
            FHousesEntity fHousesEntity = fHousesService.selectById(f.getHouseid());
            f.setHouseName(fHousesEntity==null?"":fHousesEntity.getTitle()==null?"":fHousesEntity.getTitle());

        }
        return new PageUtils(page);
    }

}
