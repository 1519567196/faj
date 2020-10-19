package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.sys.entity.SysUserEntity;
import com.resjz.common.dao.zmadmin.dao.FProgrammeImagesDao;
import com.resjz.common.dao.zmadmin.entity.FProgrammeHouseEntity;
import com.resjz.common.dao.zmadmin.entity.FProgrammeImagesEntity;
import com.resjz.common.service.sys.SysUserService;
import com.resjz.common.service.zmadmin.FProgrammeHouseService;
import com.resjz.common.service.zmadmin.FProgrammeImagesService;
import com.resjz.common.service.zmadmin.FProgrammeVillageService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("fProgrammeImagesService")
public class FProgrammeImagesServiceImpl extends ServiceImpl<FProgrammeImagesDao, FProgrammeImagesEntity> implements FProgrammeImagesService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private FProgrammeHouseService fProgrammeHouseService;
    @Value("${upload.pathNginx}")
    private  String pathNginx;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FProgrammeImagesEntity> page = this.selectPage(
                new Query<FProgrammeImagesEntity>(params).getPage(),
                new EntityWrapper<FProgrammeImagesEntity>()
        );
        for (FProgrammeImagesEntity f:page.getRecords()) {
            f.setImage(pathNginx+f.getImage());
            SysUserEntity sysUserEntity = sysUserService.selectById(f.getAddUserid());
            f.setAddUserName(sysUserEntity==null?"":sysUserEntity.getUsername()==null?"":sysUserEntity.getUsername());
            FProgrammeHouseEntity fProgrammeHouseEntity = fProgrammeHouseService.selectById(f.getProgrammeHouseId());
            f.setProgrammeHouseName(fProgrammeHouseEntity==null?"":fProgrammeHouseEntity.getTitle()==null?"":fProgrammeHouseEntity.getTitle());
        }
        
        return new PageUtils(page);
    }

}
