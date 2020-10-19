package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FHouseSkuDao;
import com.resjz.common.dao.zmadmin.entity.FHouseSkuEntity;
import com.resjz.common.service.zmadmin.FHouseSkuService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("fHouseSkuService")
public class FHouseSkuServiceImpl extends ServiceImpl<FHouseSkuDao, FHouseSkuEntity> implements FHouseSkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FHouseSkuEntity> page = this.selectPage(
                new Query<FHouseSkuEntity>(params).getPage(),
                new EntityWrapper<FHouseSkuEntity>()
        );

        return new PageUtils(page);
    }
    @Transactional
    @Override
    public int addSkus(List<FHouseSkuEntity> list) {
        boolean insert = false;
        List<FHouseSkuEntity> dels = this.selectList(new EntityWrapper<FHouseSkuEntity>().eq("house_id", list.get(0).getHouseId()));
        if (dels.size()>0){
            for (FHouseSkuEntity f:dels) {
                this.deleteById(f.getItemid());

            }
        }


        for (FHouseSkuEntity f : list) {
            insert = this.insert(f);
        }
        if (insert) {
            return 1;
        } else {
            return 0;
        }


    }

}
