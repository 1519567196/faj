package com.resjz.modules.zmadmin.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FMaterialEntity;
import com.resjz.common.dao.zmadmin.entity.FMaterialTypeEntity;
import com.resjz.common.service.zmadmin.FMaterialService;
import com.resjz.common.service.zmadmin.FMaterialTypeService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 * 建材分类
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fmaterialtype")
public class FMaterialTypeController {
    @Autowired
    private FMaterialTypeService fMaterialTypeService;

    @Autowired
    private FMaterialService fMaterialService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fmaterialtype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fMaterialTypeService.queryPage(params);

        return R.ok().put("data", page.getList()).put("count",page.getList().size());
    }



    /**
     * 列表
     */
    @RequestMapping("/onlyListByMainId")
    @RequiresPermissions("sys:fmaterialtype:list")
    public R onlyListByMainId(@RequestBody Map<String, Object> params){
        List<FMaterialTypeEntity> fMaterialTypeEntities = fMaterialTypeService.selectList(new EntityWrapper<FMaterialTypeEntity>().eq(params.get("mainId") != null, "main_materia_id", params.get("mainId")));

        return R.ok().put("list", fMaterialTypeEntities);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fmaterialtype:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FMaterialTypeEntity fMaterialType = fMaterialTypeService.selectById(itemid);
        FMaterialEntity fMaterialEntity = fMaterialService.selectById(fMaterialType.getMainMateriaId());
        fMaterialType.setMainMateriaName(fMaterialEntity==null?"":fMaterialEntity.getTitle()==null?"":fMaterialEntity.getTitle());
        return R.ok().put("fMaterialType", fMaterialType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fmaterialtype:save")
    public R save(@RequestBody FMaterialTypeEntity fMaterialType){
        fMaterialType.setAddtime(new Date());
        fMaterialTypeService.insert(fMaterialType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fmaterialtype:update")
    public R update(@RequestBody FMaterialTypeEntity fMaterialType){
        fMaterialType.setUpdatetime(new Date());
        ValidatorUtils.validateEntity(fMaterialType);
        fMaterialTypeService.updateAllColumnById(fMaterialType);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fmaterialtype:delete")
    public R delete(@RequestBody Integer[] itemids){
        fMaterialTypeService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
