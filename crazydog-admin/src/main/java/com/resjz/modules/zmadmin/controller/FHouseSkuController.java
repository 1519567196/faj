package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FHouseSkuEntity;
import com.resjz.common.service.zmadmin.FHouseSkuService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 楼盘规格属性
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fhousesku")
public class FHouseSkuController {
    @Autowired
    private FHouseSkuService fHouseSkuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fhousesku:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fHouseSkuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fhousesku:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FHouseSkuEntity fHouseSku = fHouseSkuService.selectById(itemid);

        return R.ok().put("fHouseSku", fHouseSku);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fhousesku:save")
    public R save(@RequestBody FHouseSkuEntity fHouseSku){
        fHouseSkuService.insert(fHouseSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fhousesku:update")
    public R update(@RequestBody FHouseSkuEntity fHouseSku){
        ValidatorUtils.validateEntity(fHouseSku);
        fHouseSkuService.updateAllColumnById(fHouseSku);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fhousesku:delete")
    public R delete(@RequestBody Integer[] itemids){
        fHouseSkuService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
