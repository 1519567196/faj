package com.resjz.common.service.zmadmin.impl;

import com.resjz.common.dao.zmadmin.dao.FFitupCompanySkuDao;
import com.resjz.common.dao.zmadmin.entity.FFitupCompanySkuEntity;
import com.resjz.common.dao.zmadmin.entity.FHouseSkuEntity;
import com.resjz.common.service.zmadmin.FFitupCompanySkuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.transaction.annotation.Transactional;


@Service("fFitupCompanySkuService")
public class FFitupCompanySkuServiceImpl extends ServiceImpl<FFitupCompanySkuDao, FFitupCompanySkuEntity> implements FFitupCompanySkuService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }

    @Transactional
    @Override
    public int addSkus(List<FFitupCompanySkuEntity> list) {
        boolean insert = false;
        List<FFitupCompanySkuEntity> dels = this.selectList(new EntityWrapper<FFitupCompanySkuEntity>().eq("company_id", list.get(0).getCompanyId()));
        if (dels.size()>0){
            for (FFitupCompanySkuEntity f:dels) {
                this.deleteById(f.getItemid());

            }
        }


        for (FFitupCompanySkuEntity f : list) {
            insert = this.insert(f);
        }
        if (insert) {
            return 1;
        } else {
            return 0;
        }


    }

}
