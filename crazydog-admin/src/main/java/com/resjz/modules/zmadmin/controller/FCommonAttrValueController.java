package com.resjz.modules.zmadmin.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrEntity;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrValueEntity;
import com.resjz.common.service.zmadmin.FCommonAttrService;
import com.resjz.common.service.zmadmin.FCommonAttrValueService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 * 属性规格值
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fcommonattrvalue")
public class FCommonAttrValueController {
    @Autowired
    private FCommonAttrValueService fCommonAttrValueService;
    @Autowired
    private FCommonAttrService fCommonAttrService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fcommonattrvalue:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fCommonAttrValueService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/onlylist")
    @RequiresPermissions("sys:fcommonattrvalue:list")
    public R onlylist(@RequestBody Map<String, Object> params){
        Integer attrId = null;
        if (params.get("attrId") != null) {


            try {
                attrId = Integer.valueOf(params.get("attrId").toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        List<FCommonAttrValueEntity> fCommonAttrValueEntities = fCommonAttrValueService.selectList(new EntityWrapper<FCommonAttrValueEntity>().eq(params.get("attrId")!=null, "attr_id", attrId));

        return R.ok().put("list", fCommonAttrValueEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{valueId}")
    @RequiresPermissions("sys:fcommonattrvalue:info")
    public R info(@PathVariable("valueId") Integer valueId){
        FCommonAttrValueEntity fCommonAttrValue = fCommonAttrValueService.selectById(valueId);
        FCommonAttrEntity fCommonAttrEntity = fCommonAttrService.selectById(fCommonAttrValue.getAttrId());
        fCommonAttrValue.setType(fCommonAttrEntity==null?0:fCommonAttrEntity.getType());
        return R.ok().put("fCommonAttrValue", fCommonAttrValue);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fcommonattrvalue:save")
    public R save(@RequestBody FCommonAttrValueEntity fCommonAttrValue){
        fCommonAttrValue.setAddtime(new Date());
        fCommonAttrValueService.insert(fCommonAttrValue);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fcommonattrvalue:update")
    public R update(@RequestBody FCommonAttrValueEntity fCommonAttrValue){
        ValidatorUtils.validateEntity(fCommonAttrValue);
        fCommonAttrValueService.updateAllColumnById(fCommonAttrValue);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fcommonattrvalue:delete")
    public R delete(@RequestBody Integer[] valueIds){
        fCommonAttrValueService.deleteBatchIds(Arrays.asList(valueIds));

        return R.ok();
    }

}
