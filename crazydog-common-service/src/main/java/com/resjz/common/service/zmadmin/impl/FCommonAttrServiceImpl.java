package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FCommonAttrDao;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrEntity;
import com.resjz.common.service.zmadmin.FCommonAttrService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("fCommonAttrService")
public class FCommonAttrServiceImpl extends ServiceImpl<FCommonAttrDao, FCommonAttrEntity> implements FCommonAttrService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FCommonAttrEntity> page = this.selectPage(
                new Query<FCommonAttrEntity>(params).getPage(),
                new EntityWrapper<FCommonAttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<FCommonAttrEntity> selectList(Map<String, Object> params) {
        List<FCommonAttrEntity> fCommonAttrEntities = new ArrayList<>();
        if (params.get("type") == null) {
            fCommonAttrEntities = this.selectList(new EntityWrapper<FCommonAttrEntity>());


        } else {
            fCommonAttrEntities = this.selectList(new EntityWrapper<FCommonAttrEntity>().eq(params.get("type") != null, "type",
                    Integer.parseInt(params.get("type").toString())));
        }

        for (FCommonAttrEntity f : fCommonAttrEntities) {
            if (f.getType()==1){
                f.setTitle("百科："+f.getTitle());
            }else if (f.getType()==2){
                f.setTitle("装修公司案例："+f.getTitle());
            }else if (f.getType()==3){
                f.setTitle("楼盘："+f.getTitle());
            }else if (f.getType()==4){
                f.setTitle("装修公司："+f.getTitle());
            }{

            }


        }


        return fCommonAttrEntities;
    }

    @Override
    public List<FCommonAttrEntity> selectListCopy(Map<String, Object> params) {
        List<FCommonAttrEntity> fCommonAttrEntities = new ArrayList<>();

            fCommonAttrEntities = this.selectList(new EntityWrapper<FCommonAttrEntity>()
                    .eq(params.get("type") != null, "type",
                    Integer.parseInt(params.get("type").toString())));



        return fCommonAttrEntities;
    }

}
