package com.resjz.common.service.zmadmin.impl;

import com.resjz.common.dao.sys.entity.SysUserEntity;
import com.resjz.common.dao.zmadmin.dao.FHouseimgSortDao;
import com.resjz.common.dao.zmadmin.entity.FHouseimgSortEntity;
import com.resjz.common.service.sys.SysUserService;
import com.resjz.common.service.zmadmin.FHouseimgSortService;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;




@Service("fHouseimgSortService")
public class FHouseimgSortServiceImpl extends ServiceImpl<FHouseimgSortDao, FHouseimgSortEntity> implements FHouseimgSortService {
    @Autowired
    private SysUserService sysUserService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FHouseimgSortEntity> page = this.selectPage(
                new Query<FHouseimgSortEntity>(params).getPage(),
                new EntityWrapper<FHouseimgSortEntity>()
                        .eq(StringUtils.isNotBlank(params.get("iaType").toString()),"ia_type",Integer.valueOf(params.get("iaType").toString())
        ));
        for (FHouseimgSortEntity f:page.getRecords()) {
            SysUserEntity sysUserEntity = sysUserService.selectById(f.getAddUserId());
            f.setAddUserName(sysUserEntity==null?"":sysUserEntity.getUsername()==null?"":sysUserEntity.getUsername());

        }
        return new PageUtils(page);
    }

}
