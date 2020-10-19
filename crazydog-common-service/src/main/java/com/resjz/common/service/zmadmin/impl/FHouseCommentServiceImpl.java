package com.resjz.common.service.zmadmin.impl;

import com.resjz.common.dao.zmadmin.dao.FHouseCommentDao;
import com.resjz.common.dao.zmadmin.entity.FHouseCommentEntity;
import com.resjz.common.dao.zmadmin.entity.FHousesEntity;
import com.resjz.common.dao.zmadmin.entity.FMemberEntity;
import com.resjz.common.service.zmadmin.FHouseCommentService;
import com.resjz.common.service.zmadmin.FHousesService;
import com.resjz.common.service.zmadmin.FMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;



@Service("fHouseCommentService")
public class FHouseCommentServiceImpl extends ServiceImpl<FHouseCommentDao, FHouseCommentEntity> implements FHouseCommentService {
    @Autowired
    private FMemberService fMemberService;

    @Autowired
    private FHousesService fHousesService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FHouseCommentEntity> page = this.selectPage(
                new Query<FHouseCommentEntity>(params).getPage(),
                new EntityWrapper<FHouseCommentEntity>()
        );
        for (FHouseCommentEntity f:page.getRecords()) {

            FMemberEntity fMemberEntity = fMemberService.selectById(f.getMemberid());
            f.setMemberName(fMemberEntity==null?"":fMemberEntity.getMemberName()==null?"":fMemberEntity.getMemberName());
            FHousesEntity fHousesEntity = fHousesService.selectById(f.getHouseid());
            f.setHouseName(fHousesEntity==null?"":fHousesEntity.getTitle()==null?"":fHousesEntity.getTitle());
        }

        return new PageUtils(page);
    }

}
