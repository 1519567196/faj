package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FMaterialBrandEntity;
import com.resjz.common.dao.zmadmin.entity.FMaterialTypeEntity;
import com.resjz.common.service.zmadmin.FMaterialBrandService;
import com.resjz.common.service.zmadmin.FMaterialTypeService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import com.resjz.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;



/**
 * 材建-品牌
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fmaterialbrand")
public class FMaterialBrandController extends AbstractController {
    @Autowired
    private FMaterialBrandService fMaterialBrandService;

    @Autowired
    private FMaterialTypeService fMaterialTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fmaterialbrand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fMaterialBrandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fmaterialbrand:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FMaterialBrandEntity fMaterialBrand = fMaterialBrandService.selectById(itemid);
        FMaterialTypeEntity fMaterialTypeEntity = fMaterialTypeService.selectById(fMaterialBrand.getMaterialTypeId());
        Integer mainMateriaId = fMaterialTypeEntity.getMainMateriaId();
        fMaterialBrand.setMaterialTypeName(fMaterialTypeEntity.getType());


        return R.ok().put("fMaterialBrand", fMaterialBrand).put("mainMateriaId",mainMateriaId);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fmaterialbrand:save")
    public R save(@RequestBody FMaterialBrandEntity fMaterialBrand){
        fMaterialBrand.setAddtime(new Date());
        fMaterialBrand.setAddUserid(getUserId());
        fMaterialBrandService.insert(fMaterialBrand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fmaterialbrand:update")
    public R update(@RequestBody FMaterialBrandEntity fMaterialBrand){
        fMaterialBrand.setUpdatetime(new Date());
        ValidatorUtils.validateEntity(fMaterialBrand);
        fMaterialBrandService.updateAllColumnById(fMaterialBrand);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fmaterialbrand:delete")
    public R delete(@RequestBody Integer[] itemids){
        fMaterialBrandService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
