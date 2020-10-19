package com.resjz.modules.zmadmin.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.resjz.common.dao.zmadmin.entity.FAreaEntity;
import com.resjz.common.service.zmadmin.FAreaService;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.resjz.common.utils.R;



/**
 * 地区表
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-11 10:14:18
 */
@RestController
@RequestMapping("sys/farea")
public class FAreaController {
    @Autowired
    private FAreaService fAreaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:farea:list")
    public R list(@RequestParam Map<String, Object> params){
        List<FAreaEntity> query = fAreaService.query(params);



        return R.ok().put("list", query);
    }


    @RequestMapping("/treeList")
    @RequiresPermissions("sys:farea:list")
    public R treeList(@RequestParam Map<String, Object> params){
        List<FAreaEntity> query = fAreaService.treeList();
        R list = R.ok().put("list", query);


        return R.ok().put("list", query);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{areaId}")
    @RequiresPermissions("sys:farea:info")
    public R info(@PathVariable("areaId") Integer areaId){
        FAreaEntity fArea = fAreaService.selectById(areaId);

        return R.ok().put("fArea", fArea);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:farea:save")
    public R save(@RequestBody FAreaEntity fArea){
        fAreaService.insert(fArea);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:farea:update")
    public R update(@RequestBody FAreaEntity fArea){
        ValidatorUtils.validateEntity(fArea);
        fAreaService.updateAllColumnById(fArea);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:farea:delete")
    public R delete(@RequestBody Integer[] areaIds){
        fAreaService.deleteBatchIds(Arrays.asList(areaIds));

        return R.ok();
    }

}
