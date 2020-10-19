package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.sys.entity.SysUserEntity;
import com.resjz.common.dao.zmadmin.dao.FProgrammeVrDao;
import com.resjz.common.dao.zmadmin.entity.FProgrammeHouseEntity;
import com.resjz.common.dao.zmadmin.entity.FProgrammeVrEntity;
import com.resjz.common.service.sys.SysUserService;
import com.resjz.common.service.zmadmin.FProgrammeHouseService;
import com.resjz.common.service.zmadmin.FProgrammeVrService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("fProgrammeVrService")
public class FProgrammeVrServiceImpl extends ServiceImpl<FProgrammeVrDao, FProgrammeVrEntity> implements FProgrammeVrService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private FProgrammeHouseService fProgrammeHouseService;
    @Value("${upload.pathNginx}")
    private  String pathNginx;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FProgrammeVrEntity> page = this.selectPage(
                new Query<FProgrammeVrEntity>(params).getPage(),
                new EntityWrapper<FProgrammeVrEntity>()
        );

        for (FProgrammeVrEntity f:page.getRecords()) {
            f.setVrImg(pathNginx+f.getVrImg());
            SysUserEntity sysUserEntity = sysUserService.selectById(f.getAddUserid());
            f.setAddUserName(sysUserEntity==null?"":sysUserEntity.getUsername()==null?"":sysUserEntity.getUsername());
            FProgrammeHouseEntity fProgrammeHouseEntity = fProgrammeHouseService.selectById(f.getProgrammeHouseId());
            f.setProgrammeHouseName(fProgrammeHouseEntity==null?"":fProgrammeHouseEntity.getTitle()==null?"":fProgrammeHouseEntity.getTitle());

        }

        return new PageUtils(page);
    }

}
