package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FBusinessEntity;
import com.resjz.common.service.zmadmin.FBusinessService;
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
 * 商务合作
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fbusiness")
public class FBusinessController {
    @Autowired
    private FBusinessService fBusinessService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fbusiness:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fBusinessService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fbusiness:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FBusinessEntity fBusiness = fBusinessService.selectById(itemid);

        return R.ok().put("fBusiness", fBusiness);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fbusiness:save")
    public R save(@RequestBody FBusinessEntity fBusiness){

        fBusiness.setAddtime(new Date());
        fBusinessService.insert(fBusiness);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fbusiness:update")
    public R update(@RequestBody FBusinessEntity fBusiness){

        fBusiness.setAddtime(new Date());
        fBusiness.setUpdatetime(new Date());
        ValidatorUtils.validateEntity(fBusiness);
        fBusinessService.updateAllColumnById(fBusiness);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fbusiness:delete")
    public R delete(@RequestBody Integer[] itemids){
        fBusinessService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
