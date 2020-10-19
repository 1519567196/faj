package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FAppointmentDao;
import com.resjz.common.dao.zmadmin.entity.FAppointmentEntity;
import com.resjz.common.service.zmadmin.FAppointmentService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;





@Service("fAppointmentService")
public class FAppointmentServiceImpl extends ServiceImpl<FAppointmentDao, FAppointmentEntity> implements FAppointmentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FAppointmentEntity> page = this.selectPage(
                new Query<FAppointmentEntity>(params).getPage(),
                new EntityWrapper<FAppointmentEntity>()
                .orderBy("itemid",false)
        );

        return new PageUtils(page);
    }

}
