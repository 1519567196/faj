package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.sys.entity.SysUserEntity;
import com.resjz.common.dao.zmadmin.dao.FMaterialBrandDao;
import com.resjz.common.dao.zmadmin.entity.FMaterialBrandEntity;
import com.resjz.common.dao.zmadmin.entity.FMaterialTypeEntity;
import com.resjz.common.service.sys.SysUserService;
import com.resjz.common.service.zmadmin.FMaterialBrandService;
import com.resjz.common.service.zmadmin.FMaterialTypeService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("fMaterialBrandService")
public class FMaterialBrandServiceImpl extends ServiceImpl<FMaterialBrandDao, FMaterialBrandEntity> implements FMaterialBrandService {
    @Autowired
    private FMaterialTypeService fMaterialTypeService;
    @Autowired
    private SysUserService sysUserService;
    @Value("${upload.pathNginx}")
    private  String pathNginx;
    @Resource
    private FMaterialBrandDao fMaterialBrandDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FMaterialBrandEntity> page = this.selectPage(
                new Query<FMaterialBrandEntity>(params).getPage(),
                new EntityWrapper<FMaterialBrandEntity>()
        );

        for (FMaterialBrandEntity f : page.getRecords()
        ) {

            f.setImage(f.getImage()==null?null:pathNginx+f.getImage());
            f.setLogo(f.getLogo()==null?null:pathNginx+f.getLogo());

            FMaterialTypeEntity fMaterialTypeEntity = fMaterialTypeService.selectById(f.getMaterialTypeId());
            f.setMaterialTypeName(fMaterialTypeEntity==null?"":fMaterialTypeEntity.getType()==null?"":fMaterialTypeEntity.getType());
            SysUserEntity sysUserEntity = sysUserService.selectById(f.getAddUserid());
            f.setAddUserName(sysUserEntity==null?"":sysUserEntity.getUsername()==null?"":sysUserEntity.getUsername());

        }


        return new PageUtils(page);
    }


    @Override
    public List<FMaterialBrandEntity> selectByBrandId(Integer materialBrandId) {

        return fMaterialBrandDao.selectByBrandId(materialBrandId);
    }


}
