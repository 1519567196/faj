package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FProgrammeHouseEntity;
import com.resjz.common.service.zmadmin.FProgrammeHouseService;
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
 * 我加方案-户型表
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fprogrammehouse")
public class FProgrammeHouseController  extends AbstractController {
    @Autowired
    private FProgrammeHouseService fProgrammeHouseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fprogrammehouse:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fProgrammeHouseService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/onlyList")
    @RequiresPermissions("sys:fprogrammehouse:list")
    public R onlyList(){
        List<FProgrammeHouseEntity> fProgrammeHouseEntities = fProgrammeHouseService.selectList(null);

        return R.ok().put("list", fProgrammeHouseEntities);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fprogrammehouse:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FProgrammeHouseEntity fProgrammeHouse = fProgrammeHouseService.selectById(itemid);

        return R.ok().put("fProgrammeHouse", fProgrammeHouse);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fprogrammehouse:save")
    public R save(@RequestBody FProgrammeHouseEntity fProgrammeHouse){
        fProgrammeHouse.setAddtime(new Date());
        fProgrammeHouse.setAddUserid(getUserId());
        fProgrammeHouseService.insert(fProgrammeHouse);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fprogrammehouse:update")
    public R update(@RequestBody FProgrammeHouseEntity fProgrammeHouse){
        fProgrammeHouse.setUpdatetime(new Date());
        ValidatorUtils.validateEntity(fProgrammeHouse);
        fProgrammeHouseService.updateAllColumnById(fProgrammeHouse);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fprogrammehouse:delete")
    public R delete(@RequestBody Integer[] itemids){
        fProgrammeHouseService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
