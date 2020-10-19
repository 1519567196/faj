package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FPediaTypeEntity;
import com.resjz.common.service.zmadmin.FPediaTypeService;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 百科分类
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fpediatype")
public class FPediaTypeController {
    @Autowired
    private FPediaTypeService fPediaTypeService;

    /**
     * 列表
     */
//    @RequestMapping("/list")
//    @RequiresPermissions("sys:fpediatype:list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = fPediaTypeService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fpediatype:list")
    public Object list() {
        List<FPediaTypeEntity> entityList = fPediaTypeService.selectList(null);
        for (FPediaTypeEntity f : entityList) {
            if (f != null && f.getSubject() != null) {


                if (f.getSubject() == 1) {
                    f.setSubjectDetail("房产百科");
                } else if (f.getSubject() == 2) {
                    if (f.getZxbkType() != null) {


                        if (f.getZxbkType() == 1) {
                            f.setSubjectDetail("装修百科:装修流程");
                        } else if (f.getZxbkType() == 2) {
                            f.setSubjectDetail("装修百科:装修攻略");
                        } else if (f.getZxbkType() == 3) {
                            f.setSubjectDetail("装修百科:建材百科");
                        } else if (f.getZxbkType() == 4) {
                            f.setSubjectDetail("装修百科:搭配指南");
                        }
                    }else {
                        f.setSubjectDetail("装修百科");
                    }
                }
            }
        }

        return entityList;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fpediatype:info")
    public R info(@PathVariable("itemid") Integer itemid) {
        FPediaTypeEntity fPediaType = fPediaTypeService.selectById(itemid);

        return R.ok().put("fPediaType", fPediaType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fpediatype:save")
    public R save(@RequestBody FPediaTypeEntity fPediaType) {


        fPediaTypeService.insert(fPediaType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fpediatype:update")
    public R update(@RequestBody FPediaTypeEntity fPediaType) {
        ValidatorUtils.validateEntity(fPediaType);
        fPediaTypeService.updateAllColumnById(fPediaType);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fpediatype:delete")
    public R delete(@RequestParam(value = "itemids") Integer itemids) {
        System.out.println(itemids);
        FPediaTypeEntity fPediaTypeEntity = fPediaTypeService.selectById(itemids);
        if (fPediaTypeEntity.getParentid() == 0) {
            return R.error("对不起，一级分类没有权限删除");
        }
        fPediaTypeService.deleteById(itemids);

        return R.ok();
    }

}
