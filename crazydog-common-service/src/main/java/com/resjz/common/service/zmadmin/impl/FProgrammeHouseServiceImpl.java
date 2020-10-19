package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.sys.entity.SysUserEntity;
import com.resjz.common.dao.zmadmin.dao.FProgrammeHouseDao;
import com.resjz.common.dao.zmadmin.entity.FProgrammeHouseEntity;
import com.resjz.common.dao.zmadmin.entity.FProgrammeImagesEntity;
import com.resjz.common.dao.zmadmin.entity.FProgrammeVillageEntity;
import com.resjz.common.dao.zmadmin.entity.FProgrammeVrEntity;
import com.resjz.common.service.sys.SysUserService;
import com.resjz.common.service.zmadmin.FProgrammeHouseService;
import com.resjz.common.service.zmadmin.FProgrammeImagesService;
import com.resjz.common.service.zmadmin.FProgrammeVillageService;
import com.resjz.common.service.zmadmin.FProgrammeVrService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("fProgrammeHouseService")
public class FProgrammeHouseServiceImpl extends ServiceImpl<FProgrammeHouseDao, FProgrammeHouseEntity> implements FProgrammeHouseService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private FProgrammeVillageService fProgrammeVillageService;
    @Value("${upload.pathNginx}")
    private String pathNginx;

    @Autowired
    private FProgrammeImagesService fProgrammeImagesService;

    @Autowired
    private FProgrammeVrService fProgrammeVrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FProgrammeHouseEntity> page = this.selectPage(
                new Query<FProgrammeHouseEntity>(params).getPage(),
                new EntityWrapper<FProgrammeHouseEntity>().orderBy("sort", false)
        );

        for (FProgrammeHouseEntity f : page.getRecords()) {
            f.setImage(pathNginx + f.getImage());
            SysUserEntity sysUserEntity = sysUserService.selectById(f.getAddUserid());
            f.setAddUserName(sysUserEntity == null ? "" : sysUserEntity.getUsername() == null ? "" : sysUserEntity.getUsername());
            FProgrammeVillageEntity fProgrammeVillageEntity = fProgrammeVillageService.selectById(f.getProgrammeVillageId());
            f.setProgrammeVillageName(fProgrammeVillageEntity == null ? "" : fProgrammeVillageEntity.getTitle() == null ? "" : fProgrammeVillageEntity.getTitle());

        }

        return new PageUtils(page);
    }

    @Override
    public PageUtils listPage(Map<String, Object> params) {
        Object parm = params.get("parm");
        String  parm1=null;
        boolean  like=false;
        if (parm==null){

        }else {
            parm1=parm.toString();
            like=true;
        }
        Page<FProgrammeHouseEntity> page = this.selectPage(
                new Query<FProgrammeHouseEntity>(params).getPage(),
                new EntityWrapper<FProgrammeHouseEntity>()
                        .eq(params.get("houseType") != null, "house_type", params.get("houseType"))
                        .eq(params.get("programmeVillageId") != null, "programme_village_id", params.get("programmeVillageId"))
                .like(like,"title",parm1)
        );

        for (FProgrammeHouseEntity f : page.getRecords()) {
            f.setImage(pathNginx + f.getImage());
            List<FProgrammeImagesEntity> fProgrammeImagesEntities =
                    fProgrammeImagesService.selectList(new EntityWrapper<FProgrammeImagesEntity>()
                            .eq("programme_house_id", f.getItemid()).orderBy("sort", false).last("limit 3")
                    );
            f.setImgesList(fProgrammeImagesEntities);
            List<FProgrammeVrEntity> fProgrammeVrEntities =
                    fProgrammeVrService.selectList(new EntityWrapper<FProgrammeVrEntity>()
                            .eq("programme_house_id", f.getItemid()).orderBy("sort", false)

                    );
            f.setVrList(fProgrammeVrEntities);


        }

        return new PageUtils(page);
    }

}
