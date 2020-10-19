package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.sys.entity.SysUserEntity;
import com.resjz.common.dao.zmadmin.dao.FFitupCompanyDao;
import com.resjz.common.dao.zmadmin.entity.FFitupCompanyEntity;
import com.resjz.common.service.sys.SysUserService;
import com.resjz.common.service.zmadmin.FFitupCompanyService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;




@Service("fFitupCompanyService")
public class FFitupCompanyServiceImpl extends ServiceImpl<FFitupCompanyDao, FFitupCompanyEntity> implements FFitupCompanyService {
    @Autowired
    private SysUserService sysUserService;

    @Value("${upload.pathNginx}")
    private  String pathNginx;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FFitupCompanyEntity> page = this.selectPage(
                new Query<FFitupCompanyEntity>(params).getPage(),
                new EntityWrapper<FFitupCompanyEntity>()
        );


        for (FFitupCompanyEntity f:page.getRecords()) {
            f.setImage(f.getImage()==null?null:pathNginx+f.getImage());
            SysUserEntity sysUserEntity = sysUserService.selectById(f.getAddUserid());
            f.setAddUserName(sysUserEntity==null?"":sysUserEntity.getUsername()==null?"":sysUserEntity.getUsername());


        }
        return new PageUtils(page);
    }

}
