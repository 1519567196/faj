package com.resjz.modules.zmadmin.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrEntity;
import com.resjz.common.service.zmadmin.FCommonAttrService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 属性标签
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fcommonattr")
public class FCommonAttrController {
    @Autowired
    private FCommonAttrService fCommonAttrService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fcommonattr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fCommonAttrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 列表
     */
    @RequestMapping("/onlylist")
    @RequiresPermissions("sys:fcommonattr:list")
    public R onlylist(@RequestBody Map<String, Object> params){
        if (params.get("bj")!=null){
            List<FCommonAttrEntity> fCommonAttrEntities = fCommonAttrService.selectListCopy(params);

            return R.ok().put("list", fCommonAttrEntities);
        }
        List<FCommonAttrEntity> fCommonAttrEntities = fCommonAttrService.selectList(params);

        return R.ok().put("list", fCommonAttrEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fcommonattr:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FCommonAttrEntity fCommonAttr = fCommonAttrService.selectById(itemid);

        return R.ok().put("fCommonAttr", fCommonAttr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fcommonattr:save")
    public R save(@RequestBody FCommonAttrEntity fCommonAttr){
        fCommonAttrService.insert(fCommonAttr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fcommonattr:update")
    public R update(@RequestBody FCommonAttrEntity fCommonAttr){
        ValidatorUtils.validateEntity(fCommonAttr);
        fCommonAttrService.updateAllColumnById(fCommonAttr);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fcommonattr:delete")
    public R delete(@RequestBody Integer[] itemids){
        fCommonAttrService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
