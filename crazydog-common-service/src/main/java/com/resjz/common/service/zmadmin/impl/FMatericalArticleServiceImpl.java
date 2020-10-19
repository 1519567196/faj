package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FMatericalArticleDao;
import com.resjz.common.dao.zmadmin.entity.FMatericalArticleEntity;
import com.resjz.common.service.zmadmin.FMatericalArticleService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("fMatericalArticleService")
public class FMatericalArticleServiceImpl extends ServiceImpl<FMatericalArticleDao, FMatericalArticleEntity> implements FMatericalArticleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FMatericalArticleEntity> page = this.selectPage(
                new Query<FMatericalArticleEntity>(params).getPage(),
                new EntityWrapper<FMatericalArticleEntity>()
                .orderBy("itemid",false)
        );

        return new PageUtils(page);
    }

}
