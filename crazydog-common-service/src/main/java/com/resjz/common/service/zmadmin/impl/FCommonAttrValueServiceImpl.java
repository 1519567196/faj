package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FCommonAttrValueDao;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrEntity;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrValueEntity;
import com.resjz.common.service.zmadmin.FCommonAttrService;
import com.resjz.common.service.zmadmin.FCommonAttrValueService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("fCommonAttrValueService")
public class FCommonAttrValueServiceImpl extends ServiceImpl<FCommonAttrValueDao, FCommonAttrValueEntity> implements FCommonAttrValueService {
    @Autowired
    private FCommonAttrService fCommonAttrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Integer attrId = null;
        if (params.get("attrId") != null) {


            try {
                attrId = Integer.valueOf(params.get("attrId").toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        String str = (String) params.get("attrId");

        Page<FCommonAttrValueEntity> page = this.selectPage(
                new Query<FCommonAttrValueEntity>(params).getPage(),
                new EntityWrapper<FCommonAttrValueEntity>().eq(StringUtils.isNotBlank(str), "attr_id", attrId)
        );
        for (FCommonAttrValueEntity f : page.getRecords()) {
            FCommonAttrEntity fCommonAttrEntity = fCommonAttrService.selectById(f.getAttrId());
            f.setAttrName(fCommonAttrEntity == null ? "" : fCommonAttrEntity.getTitle() == null ? ""
                    : fCommonAttrEntity.getType()==1?"百科:"+fCommonAttrEntity.getTitle():
                    fCommonAttrEntity.getType()==2?"装修公司案例:"+fCommonAttrEntity.getTitle()
                            : fCommonAttrEntity.getType()==3?"楼盘:"+fCommonAttrEntity.getTitle()
                            :fCommonAttrEntity.getType()==4?"装修公司:"+fCommonAttrEntity.getTitle():""
                    );


        }

        return new PageUtils(page);
    }

}
