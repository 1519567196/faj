package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.entity.FAreaEntity;
import com.resjz.common.dao.sys.entity.SysUserEntity;
import com.resjz.common.dao.zmadmin.dao.FHouseArticleDao;
import com.resjz.common.dao.zmadmin.entity.FArticleTypeEntity;
import com.resjz.common.dao.zmadmin.entity.FHouseArticleEntity;
import com.resjz.common.dao.zmadmin.entity.FHousesEntity;
import com.resjz.common.service.sys.SysUserService;
import com.resjz.common.service.vo.FHouseArticleVo;
import com.resjz.common.service.zmadmin.FAreaService;
import com.resjz.common.service.zmadmin.FArticleTypeService;
import com.resjz.common.service.zmadmin.FHouseArticleService;
import com.resjz.common.service.zmadmin.FHousesService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import com.resjz.common.utils.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("fHouseArticleService")
public class FHouseArticleServiceImpl extends ServiceImpl<FHouseArticleDao, FHouseArticleEntity> implements FHouseArticleService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private FHousesService fHousesService;

    @Autowired
    private FAreaService fAreaService;

    @Autowired
    private FArticleTypeService fArticleTypeService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FHouseArticleEntity> page = this.selectPage(
                new Query<FHouseArticleEntity>(params).getPage(),
                new EntityWrapper<FHouseArticleEntity>()
        );
        Page<FHouseArticleVo> pageR = new Page<>();
        pageR.setSize(page.getSize());
        pageR.setCurrent(page.getCurrent());
        pageR.setTotal(page.getTotal());
        pageR.getPages();

        if (page.getRecords() != null) {
            List<FHouseArticleVo> list = new ArrayList<>();

            for (FHouseArticleEntity fHouseArticleEntity : page.getRecords()) {
                FHouseArticleVo fHouseArticleVo = new FHouseArticleVo();
                FArticleTypeEntity fArticleTypeEntity = fArticleTypeService.selectById(fHouseArticleEntity.getArticleTypeId());
                fHouseArticleVo.setArticleTypeName(fArticleTypeEntity==null?"无":fArticleTypeEntity.getTypeName()==null?"无":fArticleTypeEntity.getTypeName());
                SpringUtil.copyPropertiesIgnoreNull(fHouseArticleEntity, fHouseArticleVo);
                SysUserEntity sysUserEntity = sysUserService.selectById(fHouseArticleEntity.getAddUserid());
                fHouseArticleVo.setAddUserName(sysUserEntity == null ? "" : sysUserEntity.getUsername());

                FHousesEntity fHousesEntity = fHousesService.selectById(fHouseArticleEntity.getHouseId());

                fHouseArticleVo.setHouseName(fHousesEntity == null ? "" : fHousesEntity.getTitle());
                FAreaEntity fAreaEntity1 = fAreaService.selectById(fHouseArticleEntity.getAreaid());
                FAreaEntity fAreaEntity2 = new FAreaEntity();
                FAreaEntity fAreaEntity3 = new FAreaEntity();
                if (fAreaEntity1 != null) {
                    if (fAreaEntity1.getAreaParentId() != null) {
                        fAreaEntity2 = fAreaService.selectById(fAreaEntity1.getAreaParentId());
                    } else {
                        fHouseArticleVo.setAreaName(fAreaEntity1.getAreaName());

                    }


                    if (fAreaEntity2 != null) {
                        if (fAreaEntity2.getAreaParentId() != null) {
                            fAreaEntity3 = fAreaService.selectById(fAreaEntity2.getAreaParentId());
                            if (fAreaEntity3!=null){
                                fHouseArticleVo.setAreaName(fAreaEntity3.getAreaName()+fAreaEntity2.getAreaName()+fAreaEntity1.getAreaName());

                            }else {
                                fHouseArticleVo.setAreaName(fAreaEntity2.getAreaName() + fAreaEntity1.getAreaName());

                            }


                        } else {
                            fHouseArticleVo.setAreaName(fAreaEntity2.getAreaName() + fAreaEntity1.getAreaName());

                        }


                    } else {
                        fHouseArticleVo.setAreaName(fAreaEntity1.getAreaName());

                    }

                } else {
                    fHouseArticleVo.setAreaName("");

                }


                list.add(fHouseArticleVo);
            }

            pageR.setRecords(list);
        }

        return new PageUtils(pageR);
    }

}
