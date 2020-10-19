package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FSiteEntity;
import com.resjz.common.service.zmadmin.FSiteService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.Assert;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;



/**
 * 企业站点信息
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fsite")
public class FSiteController {
    @Autowired
    private FSiteService fSiteService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fsite:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fSiteService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fsite:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FSiteEntity fSite = fSiteService.selectById(itemid);

        return R.ok().put("fSite", fSite);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fsite:save")
    public R save(@RequestBody FSiteEntity fSite){
        Assert.isBlank(fSite.getCompany(),"企业名称不能为空");
        Assert.isNull(fSite.getIcp(),"ICP不能为空");
        Assert.isNull(fSite.getMobile(),"联系电话不能为空");
        Assert.isNull(fSite.getAddress(),"公司地址不能为空");
        fSite.setUpdatetime(new Date());
        fSiteService.insert(fSite);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fsite:update")
    public R update(@RequestBody FSiteEntity fSite){
        Assert.isBlank(fSite.getCompany(),"企业名称不能为空");
        Assert.isNull(fSite.getIcp(),"ICP不能为空");
        Assert.isNull(fSite.getMobile(),"联系电话不能为空");
        Assert.isNull(fSite.getAddress(),"公司地址不能为空");
        fSite.setUpdatetime(new Date());


        ValidatorUtils.validateEntity(fSite);
        fSiteService.updateAllColumnById(fSite);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fsite:delete")
    public R delete(@RequestBody Integer[] itemids){
        fSiteService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
