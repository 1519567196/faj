package com.resjz.modules.zmadmin.controller;

import cn.hutool.core.date.DateTime;
import com.resjz.common.dao.zmadmin.entity.FAdPlaceEntity;
import com.resjz.common.service.zmadmin.FAdPlaceService;
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
 * 广告位
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fadplace")
public class FAdPlaceController extends AbstractController {
    @Autowired
    private FAdPlaceService fAdPlaceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fadplace:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fAdPlaceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fadplace:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FAdPlaceEntity fAdPlace = fAdPlaceService.selectById(itemid);

        return R.ok().put("fAdPlace", fAdPlace);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fadplace:save")
    public R save(@RequestBody FAdPlaceEntity fAdPlace){

        fAdPlace.setTitle(fAdPlace.getTitle());
        fAdPlace.setStatus(fAdPlace.getStatus());
        fAdPlace.setAddtime(new Date());
        fAdPlace.setAdduserid(getUserId());
        fAdPlace.setUpdatetime(fAdPlace.getUpdatetime());
        fAdPlace.setAreaid(fAdPlace.getAreaid());

        fAdPlaceService.insert(fAdPlace);


        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fadplace:update")
    public R update(@RequestBody FAdPlaceEntity fAdPlace){
        ValidatorUtils.validateEntity(fAdPlace);
        fAdPlaceService.updateAllColumnById(fAdPlace);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fadplace:delete")
    public R delete(@RequestBody Integer[] itemids){
        fAdPlaceService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

    /**
     * 状态：禁用或启用
     */
    @RequestMapping("/recommend/{itemid}")
    //   @RequiresPermissions("sys:tcase:recommend")
    public R recommend(@PathVariable("itemid") Integer itemid){

        FAdPlaceEntity fAdPlaces = fAdPlaceService.selectById(itemid);
        if(fAdPlaces!=null){

            if(fAdPlaces.getStatus()!=null&&fAdPlaces.getStatus()==0){
                fAdPlaces.setStatus(1);
            }else{
                fAdPlaces.setStatus(0);
            }
            fAdPlaceService.updateById(fAdPlaces);
        }

        return R.ok().put("msg","操作成功");
    }

}
