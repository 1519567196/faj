package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.entity.FAreaEntity;
import com.resjz.common.dao.sys.entity.SysUserEntity;
import com.resjz.common.dao.zmadmin.dao.FProgrammeVillageDao;
import com.resjz.common.dao.zmadmin.entity.FProgrammeVillageEntity;
import com.resjz.common.service.sys.SysUserService;
import com.resjz.common.service.zmadmin.FAreaService;
import com.resjz.common.service.zmadmin.FProgrammeVillageService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("fProgrammeVillageService")
public class FProgrammeVillageServiceImpl extends ServiceImpl<FProgrammeVillageDao, FProgrammeVillageEntity> implements FProgrammeVillageService {
    @Autowired
    private FAreaService fAreaService;


//    @Autowired
//    private FProgrammeVillageDao fProgrammeVillageDao;


    @Value("${upload.pathNginx}")
    private String pathNginx;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FProgrammeVillageEntity> page = this.selectPage(
                new Query<FProgrammeVillageEntity>(params).getPage(),
                new EntityWrapper<FProgrammeVillageEntity>()
        );


        for (FProgrammeVillageEntity f : page.getRecords()) {
            f.setImage(pathNginx + f.getImage());
            SysUserEntity sysUserEntity = sysUserService.selectById(f.getAddUserid());
            f.setAddUserName(sysUserEntity == null ? "" : sysUserEntity.getUsername() == null ? "" : sysUserEntity.getUsername());

            StringBuffer s = new StringBuffer();
            if (f.getProvinceid() != null) {
                FAreaEntity fAreaEntityP = fAreaService.selectById(f.getProvinceid());
                if (fAreaEntityP != null && fAreaEntityP.getAreaName() != null) {
                    s.append(fAreaEntityP.getAreaName());
                }

            }

            if (f.getCityid() != null) {
                FAreaEntity fAreaEntityC = fAreaService.selectById(f.getCityid());
                if (fAreaEntityC != null && fAreaEntityC.getAreaName() != null) {
                    s.append(fAreaEntityC.getAreaName());
                }

            }


            if (f.getTownid() != null) {
                FAreaEntity fAreaEntityT = fAreaService.selectById(f.getTownid());
                if (fAreaEntityT != null && fAreaEntityT.getAreaName() != null) {
                    s.append(fAreaEntityT.getAreaName());
                }

            }

            f.setTownName(s.toString());


        }

        return new PageUtils(page);
    }

    @Override
    public PageUtils listPage(Map<String, Object> params) {

//        params.put("_search", false);
        params.put("nd", new Date().getTime());
//        params.put("sidx", "");
        params.put("_", new Date().getTime());
//        params.put("order", "asc");

        Page<FProgrammeVillageEntity> page = this.selectPage(
                new Query<FProgrammeVillageEntity>(params).getPage(),
                new EntityWrapper<FProgrammeVillageEntity>()
                        .eq("areaid", Integer.parseInt(params.get("areaid").toString()))
        );


        for (FProgrammeVillageEntity f : page.getRecords()) {
            f.setImage(pathNginx + f.getImage());
            SysUserEntity sysUserEntity = sysUserService.selectById(f.getAddUserid());
            f.setAddUserName(sysUserEntity == null ? "" : sysUserEntity.getUsername() == null ? "" : sysUserEntity.getUsername());

            StringBuffer s = new StringBuffer();
            if (f.getProvinceid() != null) {
                FAreaEntity fAreaEntityP = fAreaService.selectById(f.getProvinceid());
                if (fAreaEntityP != null && fAreaEntityP.getAreaName() != null) {
                    s.append(fAreaEntityP.getAreaName());
                }

            }

            if (f.getCityid() != null) {
                FAreaEntity fAreaEntityC = fAreaService.selectById(f.getCityid());
                if (fAreaEntityC != null && fAreaEntityC.getAreaName() != null) {
                    s.append(fAreaEntityC.getAreaName());
                }

            }


            if (f.getTownid() != null) {
                FAreaEntity fAreaEntityT = fAreaService.selectById(f.getTownid());
                if (fAreaEntityT != null && fAreaEntityT.getAreaName() != null) {
                    s.append(fAreaEntityT.getAreaName());
                }

            }

            f.setTownName(s.toString());


        }

        return new PageUtils(page);
    }

}
