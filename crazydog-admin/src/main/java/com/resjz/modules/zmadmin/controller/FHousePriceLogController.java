package com.resjz.modules.zmadmin.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


import com.resjz.common.dao.zmadmin.entity.FHousePriceLogEntity;
import com.resjz.common.service.zmadmin.FHousePriceLogService;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;



/**
 * 房价走势记录表
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-17 17:54:21
 */
@RestController
@RequestMapping("sys/fhousepricelog")
public class FHousePriceLogController {
    @Autowired
    private FHousePriceLogService fHousePriceLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fhousepricelog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fHousePriceLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fhousepricelog:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FHousePriceLogEntity fHousePriceLog = fHousePriceLogService.selectById(itemid);

        return R.ok().put("fHousePriceLog", fHousePriceLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fhousepricelog:save")
    public R save(@RequestBody FHousePriceLogEntity fHousePriceLog){
        fHousePriceLog.setAddtime(new Date());
        fHousePriceLogService.insert(fHousePriceLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fhousepricelog:update")
    public R update(@RequestBody FHousePriceLogEntity fHousePriceLog){
        ValidatorUtils.validateEntity(fHousePriceLog);
        fHousePriceLogService.updateAllColumnById(fHousePriceLog);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fhousepricelog:delete")
    public R delete(@RequestBody Integer[] itemids){
        fHousePriceLogService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
