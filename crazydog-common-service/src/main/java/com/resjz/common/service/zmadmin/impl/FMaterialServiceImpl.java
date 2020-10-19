package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.sys.entity.SysUserEntity;
import com.resjz.common.dao.zmadmin.dao.FMaterialDao;
import com.resjz.common.dao.zmadmin.entity.FMaterialEntity;
import com.resjz.common.service.sys.SysUserService;
import com.resjz.common.service.zmadmin.FMaterialService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("fMaterialService")
public class FMaterialServiceImpl extends ServiceImpl<FMaterialDao, FMaterialEntity> implements FMaterialService {
    @Autowired
    private SysUserService sysUserService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FMaterialEntity> page = this.selectPage(
                new Query<FMaterialEntity>(params).getPage(),
                new EntityWrapper<FMaterialEntity>()
        );
        for (FMaterialEntity f : page.getRecords()
        ) {
            SysUserEntity sysUserEntity = sysUserService.selectById(f.getAddUserid());
            f.setAddUserName(sysUserEntity==null?"":sysUserEntity.getUsername()==null?"":sysUserEntity.getUsername());

        }


        return new PageUtils(page);
    }

}
