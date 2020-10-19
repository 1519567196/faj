package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FAppointmentEntity;
import com.resjz.common.service.zmadmin.FAppointmentService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;



/**
 * 预约
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fappointment")
public class FAppointmentController {
    @Autowired
    private FAppointmentService fAppointmentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fappointment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fAppointmentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fappointment:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FAppointmentEntity fAppointment = fAppointmentService.selectById(itemid);

        return R.ok().put("fAppointment", fAppointment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fappointment:save")
    public R save(@RequestBody FAppointmentEntity fAppointment){

        fAppointment.setAddtime(new Date());

        fAppointmentService.insert(fAppointment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fappointment:update")
    public R update(@RequestBody FAppointmentEntity fAppointment){
        fAppointment.setAddtime(new Date());

        ValidatorUtils.validateEntity(fAppointment);
        fAppointmentService.updateAllColumnById(fAppointment);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fappointment:delete")
    public R delete(@RequestBody Integer[] itemids){
        fAppointmentService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
