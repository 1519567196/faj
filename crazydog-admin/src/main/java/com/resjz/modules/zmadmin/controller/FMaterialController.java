package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FMaterialEntity;
import com.resjz.common.service.zmadmin.FMaterialService;
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
 * 建材
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fmaterial")
public class FMaterialController  extends AbstractController {
    @Autowired
    private FMaterialService fMaterialService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fmaterial:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fMaterialService.queryPage(params);

        return R.ok().put("data", page.getList()).put("count",page.getList().size());
    }

    @RequestMapping("/onlyList")
    @RequiresPermissions("sys:fmaterial:list")
    public R onlyList(){
        List<FMaterialEntity> fMaterialEntities = fMaterialService.selectList(null);

        return R.ok().put("list", fMaterialEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fmaterial:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FMaterialEntity fMaterial = fMaterialService.selectById(itemid);

        return R.ok().put("fMaterial", fMaterial);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fmaterial:save")
    public R save(@RequestBody FMaterialEntity fMaterial){
        fMaterial.setAddtime(new Date());
        fMaterial.setAddUserid(getUserId());
        fMaterialService.insert(fMaterial);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fmaterial:update")
    public R update(@RequestBody FMaterialEntity fMaterial){
        fMaterial.setUpdatetime(new Date());
        ValidatorUtils.validateEntity(fMaterial);
        fMaterialService.updateAllColumnById(fMaterial);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fmaterial:delete")
    public R delete(@RequestBody Integer[] itemids){
        fMaterialService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
