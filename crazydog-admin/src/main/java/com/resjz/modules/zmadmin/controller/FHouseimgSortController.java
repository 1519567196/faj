package com.resjz.modules.zmadmin.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FHouseImagesEntity;
import com.resjz.common.dao.zmadmin.entity.FHouseimgSortEntity;
import com.resjz.common.service.zmadmin.FHouseImagesService;
import com.resjz.common.service.zmadmin.FHouseimgSortService;
import com.resjz.common.validator.ValidatorUtils;
import com.resjz.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;



/**
 * 楼盘相册表（楼盘图片分类表）
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-12 11:27:27
 */
@RestController
@RequestMapping("sys/fhouseimgsort")
public class FHouseimgSortController extends AbstractController {
    @Autowired
    private FHouseimgSortService fHouseimgSortService;

    @Autowired
    private FHouseImagesService fHouseImagesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fhouseimgsort:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fHouseimgSortService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{iaId}")
    @RequiresPermissions("sys:fhouseimgsort:info")
    public R info(@PathVariable("iaId") Integer iaId){
        FHouseimgSortEntity fHouseimgSort = fHouseimgSortService.selectById(iaId);

        return R.ok().put("fHouseimgSort", fHouseimgSort);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fhouseimgsort:save")
    public R save(@RequestBody FHouseimgSortEntity fHouseimgSort){
        fHouseimgSort.setAddTime(new Date());
        fHouseimgSort.setAddUserId(getUserId());
        fHouseimgSortService.insert(fHouseimgSort);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fhouseimgsort:update")
    public R update(@RequestBody FHouseimgSortEntity fHouseimgSort){
        ValidatorUtils.validateEntity(fHouseimgSort);
        fHouseimgSortService.updateAllColumnById(fHouseimgSort);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fhouseimgsort:delete")
    public R delete(@RequestBody Integer[] iaIds){
        int sort_id = fHouseImagesService.selectCount(new EntityWrapper<FHouseImagesEntity>().in("sort_id", iaIds));
if (sort_id>0){

    return   R.error("请先删除相册内照片");
}

        fHouseimgSortService.deleteBatchIds(Arrays.asList(iaIds));

        return R.ok();
    }

}
