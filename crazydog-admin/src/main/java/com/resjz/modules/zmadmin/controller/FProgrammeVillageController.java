package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FProgrammeVillageEntity;
import com.resjz.common.service.zmadmin.FProgrammeVillageService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import com.resjz.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 我家方案-小区
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fprogrammevillage")
public class FProgrammeVillageController extends AbstractController {
    @Autowired
    private FProgrammeVillageService fProgrammeVillageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fprogrammevillage:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = fProgrammeVillageService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/onlyList")
    @RequiresPermissions("sys:fprogrammevillage:list")
    public R onlyList(@RequestParam Map<String, Object> params) {
        List<FProgrammeVillageEntity> fProgrammeVillageEntities = fProgrammeVillageService.selectList(null);

        return R.ok().put("list", fProgrammeVillageEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fprogrammevillage:info")
    public R info(@PathVariable("itemid") Integer itemid) {
        FProgrammeVillageEntity fProgrammeVillage = fProgrammeVillageService.selectById(itemid);
        Integer[] value=new  Integer[3];
        if (fProgrammeVillage.getProvinceid() != null) {
      value[0]=fProgrammeVillage.getProvinceid();
        }

        if (fProgrammeVillage.getCityid() != null) {
            value[1]=fProgrammeVillage.getCityid();
        }
        if (fProgrammeVillage.getTownid() != null) {
            value[2]=fProgrammeVillage.getTownid();
        }

        return R.ok().put("fProgrammeVillage", fProgrammeVillage).put("value",value);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fprogrammevillage:save")
    public R save(@RequestBody FProgrammeVillageEntity fProgrammeVillage) {
        fProgrammeVillage.setAddUserid(getUserId());
        fProgrammeVillage.setAddtime(new Date());
        fProgrammeVillageService.insert(fProgrammeVillage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fprogrammevillage:update")
    public R update(@RequestBody FProgrammeVillageEntity fProgrammeVillage) {
        fProgrammeVillage.setUpdatetime(new Date());
        ValidatorUtils.validateEntity(fProgrammeVillage);
        fProgrammeVillageService.updateAllColumnById(fProgrammeVillage);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fprogrammevillage:delete")
    public R delete(@RequestBody Integer[] itemids) {
        fProgrammeVillageService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
