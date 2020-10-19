package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FBbsDao;
import com.resjz.common.dao.zmadmin.entity.FBbsEntity;
import com.resjz.common.dao.zmadmin.entity.FMemberEntity;
import com.resjz.common.dao.zmadmin.entity.FRepleyBbsEntity;
import com.resjz.common.service.zmadmin.FBbsService;
import com.resjz.common.service.zmadmin.FMemberService;
import com.resjz.common.service.zmadmin.FRepleyBbsService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service("fBbsService")
public class FBbsServiceImpl extends ServiceImpl<FBbsDao, FBbsEntity> implements FBbsService {

    @Autowired
    private FMemberService fMemberService;

    @Autowired
    private FRepleyBbsService fRepleyBbsService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FBbsEntity> page = this.selectPage(
                new Query<FBbsEntity>(params).getPage(),
                new EntityWrapper<FBbsEntity>()
                .orderBy("itemid",false)
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils listPage(Map<String, Object> params) {

        Page<FBbsEntity> page = this.selectPage(
                new Query<FBbsEntity>(params).getPage(),
                new EntityWrapper<FBbsEntity>().eq(true,"typeid",params.get("type"))
                .orderBy("views",false)

        );
            for (FBbsEntity bbsEntity : page.getRecords()) {
                FMemberEntity fMemberEntity = fMemberService.selectById(bbsEntity.getMemberid());
                bbsEntity.setMemberName(fMemberEntity == null ? "" : fMemberEntity.getMemberName() == null ? "" : fMemberEntity.getMemberName());

                int number = 0;
                Integer itemid = bbsEntity.getItemid();
                List<FRepleyBbsEntity> fRepleyBbsEntities = fRepleyBbsService.selectList(new EntityWrapper<>());
                for (int j = 0; j < fRepleyBbsEntities.size(); j++) {
                    Integer bbsid = fRepleyBbsEntities.get(j).getBbsid();
                    if (bbsid != null && bbsid == itemid) {
                        number++;
                    }
                }
                bbsEntity.setNumber(number);
            }


        return new PageUtils(page);
    }

    @Override
    public PageUtils listPage2(Map<String, Object> params) {
        String title = params.get("title").toString();
        Page<FBbsEntity> page = this.selectPage(
                new Query<FBbsEntity>(params).getPage(),
                new EntityWrapper<FBbsEntity>()
                .like("title",title)

        );

        return new PageUtils(page);
    }

}
