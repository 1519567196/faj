package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FNewsTypeEntity;
import com.resjz.common.service.zmadmin.FNewsTypeService;
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
 * 房产资讯分类
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fnewstype")
public class FNewsTypeController {
    @Autowired
    private FNewsTypeService fNewsTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fnewstype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fNewsTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{typeid}")
    @RequiresPermissions("sys:fnewstype:info")
    public R info(@PathVariable("typeid") Integer typeid){
        FNewsTypeEntity fNewsType = fNewsTypeService.selectById(typeid);

        return R.ok().put("fNewsType", fNewsType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fnewstype:save")
    public R save(@RequestBody FNewsTypeEntity fNewsType){
        Assert.isBlank(fNewsType.getTypeName(),"分类名称不能为空");
        Assert.isNull(fNewsType.getSort(),"排序不能为空");
        fNewsType.setAddtime(new Date());

        fNewsTypeService.insert(fNewsType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fnewstype:update")
    public R update(@RequestBody FNewsTypeEntity fNewsType){
        Assert.isBlank(fNewsType.getTypeName(),"分类名称不能为空");
        Assert.isNull(fNewsType.getSort(),"排序不能为空");
        fNewsType.setUpdatetime(new Date());
        fNewsType.setAddtime(new Date());

        ValidatorUtils.validateEntity(fNewsType);
        fNewsTypeService.updateAllColumnById(fNewsType);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fnewstype:delete")
    public R delete(@RequestBody Integer[] typeids){
        fNewsTypeService.deleteBatchIds(Arrays.asList(typeids));

        return R.ok();
    }

}
