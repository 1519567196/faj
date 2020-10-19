package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FAdvertDao;
import com.resjz.common.dao.zmadmin.entity.FAdPlaceEntity;
import com.resjz.common.dao.zmadmin.entity.FAdvertEntity;
import com.resjz.common.service.zmadmin.FAdvertService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;





@Service("fAdvertService")
public class FAdvertServiceImpl extends ServiceImpl<FAdvertDao, FAdvertEntity> implements FAdvertService {

    @Resource
    private FAdvertDao fAdvertDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String typeid = (String)params.get("fAdvert.adPlaceId");
        Page<FAdvertEntity> page = this.selectPage(
                new Query<FAdvertEntity>(params).getPage(),
                new EntityWrapper<FAdvertEntity>()
                        .eq(StringUtils.isNotBlank(typeid),"ad_place_id",typeid)
                .orderBy("itemid",false)

        );

        return new PageUtils(page);
    }

    @Override
    public List<FAdPlaceEntity> selectAll() {
        return fAdvertDao.selectAll();
    }

}
