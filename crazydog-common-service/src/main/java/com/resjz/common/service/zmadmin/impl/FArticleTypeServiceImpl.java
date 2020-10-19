package com.resjz.common.service.zmadmin.impl;

import com.resjz.common.dao.zmadmin.dao.FArticleTypeDao;
import com.resjz.common.dao.zmadmin.entity.FArticleTypeEntity;
import com.resjz.common.service.zmadmin.FArticleTypeService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;




@Service("fArticleTypeService")
public class FArticleTypeServiceImpl extends ServiceImpl<FArticleTypeDao, FArticleTypeEntity> implements FArticleTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FArticleTypeEntity> page = this.selectPage(
                new Query<FArticleTypeEntity>(params).getPage(),
                new EntityWrapper<FArticleTypeEntity>()
                .orderBy("typeid",false)
        );

        return new PageUtils(page);
    }

}
