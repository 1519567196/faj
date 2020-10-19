package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FImagesDao;
import com.resjz.common.dao.zmadmin.entity.FFitupCompanyEntity;
import com.resjz.common.dao.zmadmin.entity.FImagesEntity;
import com.resjz.common.service.zmadmin.FFitupCompanyService;
import com.resjz.common.service.zmadmin.FImagesService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;






@Service("fImagesService")
public class FImagesServiceImpl extends ServiceImpl<FImagesDao, FImagesEntity> implements FImagesService {
    @Autowired
    private FFitupCompanyService fFitupCompanyService;
    @Value("${upload.pathNginx}")
    private  String pathNginx;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FImagesEntity> page = this.selectPage(
                new Query<FImagesEntity>(params).getPage(),
                new EntityWrapper<FImagesEntity>()
        );
        for (FImagesEntity f:page.getRecords()
             ) {
            f.setImage(f.getImage()==null?null:pathNginx+f.getImage());
            FFitupCompanyEntity fFitupCompanyEntity = fFitupCompanyService.selectById(f.getCompanyid());
            f.setCompanyName(fFitupCompanyEntity==null?"":fFitupCompanyEntity.getCompanyName()==null?"":fFitupCompanyEntity.getCompanyName());
        }


        return new PageUtils(page);
    }

}
