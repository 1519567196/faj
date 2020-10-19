package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FRepleyBbsDao;
import com.resjz.common.dao.zmadmin.entity.FMemberEntity;
import com.resjz.common.dao.zmadmin.entity.FRepleyBbsEntity;
import com.resjz.common.service.zmadmin.FMemberService;
import com.resjz.common.service.zmadmin.FRepleyBbsService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("fRepleyBbsService")
public class FRepleyBbsServiceImpl extends ServiceImpl<FRepleyBbsDao, FRepleyBbsEntity> implements FRepleyBbsService {

    @Autowired
    private FMemberService fMemberService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FRepleyBbsEntity> page = this.selectPage(
                new Query<FRepleyBbsEntity>(params).getPage(),
                new EntityWrapper<FRepleyBbsEntity>()
                .orderBy("itemid",false)
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils listPage(Map<String, Object> params) {
        String bbsid = params.get("bbsid").toString();
        Page<FRepleyBbsEntity> page = this.selectPage(
                new Query<FRepleyBbsEntity>(params).getPage(),
                new EntityWrapper<FRepleyBbsEntity>()
                        .eq("bbsid",Integer.parseInt(bbsid))
        );
        for (FRepleyBbsEntity rebbs : page.getRecords()) {
            FMemberEntity fMemberEntity = fMemberService.selectById(rebbs.getMemberid());
            rebbs.setRepleyname(fMemberEntity==null?"":fMemberEntity.getMemberName()==null?"":fMemberEntity.getMemberName());
            rebbs.setHeadimg(fMemberEntity==null?"":fMemberEntity.getHeadImg()==null?"":fMemberEntity.getHeadImg());
        }

        return new PageUtils(page);
    }

}
