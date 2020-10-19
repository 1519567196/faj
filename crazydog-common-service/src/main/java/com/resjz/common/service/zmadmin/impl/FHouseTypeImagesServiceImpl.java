package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.sys.entity.SysUserEntity;
import com.resjz.common.dao.zmadmin.dao.FHouseTypeImagesDao;
import com.resjz.common.dao.zmadmin.entity.FHouseTypeImagesEntity;
import com.resjz.common.dao.zmadmin.entity.FHousesEntity;
import com.resjz.common.service.sys.SysUserService;
import com.resjz.common.service.zmadmin.FHouseTypeImagesService;
import com.resjz.common.service.zmadmin.FHousesService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("fHouseTypeImagesService")
public class FHouseTypeImagesServiceImpl extends ServiceImpl<FHouseTypeImagesDao, FHouseTypeImagesEntity> implements FHouseTypeImagesService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private FHousesService fHousesService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FHouseTypeImagesEntity> page = this.selectPage(
                new Query<FHouseTypeImagesEntity>(params).getPage(),
                new EntityWrapper<FHouseTypeImagesEntity>().eq("sort_id",Integer.valueOf(params.get("sortId").toString()))
        );
        for (FHouseTypeImagesEntity  f:page.getRecords()) {
            FHousesEntity fHousesEntity = fHousesService.selectById(f.getHouseId());
            f.setHouseName(fHousesEntity==null?"":fHousesEntity.getTitle()==null?"":fHousesEntity.getTitle());

            SysUserEntity sysUserEntity = sysUserService.selectById(f.getAddUserid());
            f.setAddUserName(sysUserEntity==null?"":sysUserEntity.getUsername()==null?"":sysUserEntity.getUsername());


        }

        return new PageUtils(page);
    }

}
