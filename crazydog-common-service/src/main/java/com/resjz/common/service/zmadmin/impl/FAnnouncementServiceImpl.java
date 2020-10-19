package com.resjz.common.service.zmadmin.impl;

import com.resjz.common.dao.zmadmin.dao.FAnnouncementDao;
import com.resjz.common.dao.zmadmin.entity.FAnnouncementEntity;
import com.resjz.common.service.zmadmin.FAnnouncementService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;



@Service("fAnnouncementService")
public class FAnnouncementServiceImpl extends ServiceImpl<FAnnouncementDao, FAnnouncementEntity> implements FAnnouncementService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FAnnouncementEntity> page = this.selectPage(
                new Query<FAnnouncementEntity>(params).getPage(),
                new EntityWrapper<FAnnouncementEntity>()
        );

        return new PageUtils(page);
    }

}
