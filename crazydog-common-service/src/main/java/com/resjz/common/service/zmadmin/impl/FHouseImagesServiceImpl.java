package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.sys.entity.SysUserEntity;
import com.resjz.common.dao.zmadmin.dao.FHouseImagesDao;
import com.resjz.common.dao.zmadmin.entity.FHouseImagesEntity;
import com.resjz.common.dao.zmadmin.entity.FHousesEntity;
import com.resjz.common.service.sys.SysUserService;
import com.resjz.common.service.zmadmin.FHouseImagesService;
import com.resjz.common.service.zmadmin.FHousesService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;




@Service("fHouseImagesService")
public class FHouseImagesServiceImpl extends ServiceImpl<FHouseImagesDao, FHouseImagesEntity> implements FHouseImagesService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private FHousesService fHousesService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        FHouseImagesEntity  fHouseImagesEntity=new FHouseImagesEntity();

            fHouseImagesEntity.setSortId(Integer.valueOf(params.get("sortId").toString()));


        Page<FHouseImagesEntity> page = this.selectPage(
                new Query<FHouseImagesEntity>(params).getPage(),
                new EntityWrapper<FHouseImagesEntity>().eq("sort_id",Integer.valueOf(params.get("sortId").toString()))
        );
        for (FHouseImagesEntity f:page.getRecords()) {
            SysUserEntity sysUserEntity = sysUserService.selectById(f.getAddUserid());
            f.setAddUserName(sysUserEntity==null?"":sysUserEntity.getUsername()==null?"":sysUserEntity.getUsername());
            FHousesEntity fHousesEntity = fHousesService.selectById(f.getHouseId());
            f.setHouseName(fHousesEntity==null?"":fHousesEntity.getTitle()==null?"":fHousesEntity.getTitle());

        }


        return new PageUtils(page);
    }

}
