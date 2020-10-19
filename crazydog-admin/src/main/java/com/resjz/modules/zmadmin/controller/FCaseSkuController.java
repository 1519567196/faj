package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FCaseSkuEntity;
import com.resjz.common.service.zmadmin.FCaseSkuService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 案例规格属性表
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fcasesku")
public class FCaseSkuController {
    @Autowired
    private FCaseSkuService fCaseSkuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fcasesku:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fCaseSkuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fcasesku:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FCaseSkuEntity fCaseSku = fCaseSkuService.selectById(itemid);

        return R.ok().put("fCaseSku", fCaseSku);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fcasesku:save")
    public R save(@RequestBody FCaseSkuEntity fCaseSku){
        fCaseSkuService.insert(fCaseSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fcasesku:update")
    public R update(@RequestBody FCaseSkuEntity fCaseSku){
        ValidatorUtils.validateEntity(fCaseSku);
        fCaseSkuService.updateAllColumnById(fCaseSku);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fcasesku:delete")
    public R delete(@RequestBody Integer[] itemids){
        fCaseSkuService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
