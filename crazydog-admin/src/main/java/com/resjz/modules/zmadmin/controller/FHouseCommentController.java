package com.resjz.modules.zmadmin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.resjz.common.dao.zmadmin.entity.FHouseCommentEntity;
import com.resjz.common.service.zmadmin.FHouseCommentService;
import com.resjz.common.validator.ValidatorUtils;
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
 * 楼盘点评表
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-17 17:54:21
 */
@RestController
@RequestMapping("sys/fhousecomment")
public class FHouseCommentController {
    @Autowired
    private FHouseCommentService fHouseCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fhousecomment:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = fHouseCommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fhousecomment:info")
    public R info(@PathVariable("itemid") Integer itemid) {
        FHouseCommentEntity fHouseComment = fHouseCommentService.selectById(itemid);

        return R.ok().put("fHouseComment", fHouseComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fhousecomment:save")
    public R save(@RequestBody FHouseCommentEntity fHouseComment) {
        fHouseCommentService.insert(fHouseComment);

        return R.ok();
    }

    /**
     * 修改
     */
//    @RequestMapping("/update")
//    @RequiresPermissions("sys:fhousecomment:update")
//    public R update(@RequestBody FHouseCommentEntity fHouseComment){
//        ValidatorUtils.validateEntity(fHouseComment);
//        fHouseCommentService.updateAllColumnById(fHouseComment);//全部更新
//
//        return R.ok();
//    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fhousecomment:delete")
    public R delete(@RequestBody Integer[] itemids) {
        fHouseCommentService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

    /**
     * 恢复屏蔽的点评
     *
     * @param itemid
     * @return
     */
    @RequestMapping("/setVisible")
    @RequiresPermissions("sys:fhousecomment:delete")
    public R setVisible(@RequestBody Integer itemid) {


        FHouseCommentEntity fHouseCommentEntity = new FHouseCommentEntity();
        fHouseCommentEntity.setIsHot(0);
        fHouseCommentEntity.setItemid(itemid);


        boolean b = fHouseCommentService.updateById(fHouseCommentEntity);
        if (b) {
            return R.ok();
        }
        return R.error("设置失败");
    }






    /**
     * 屏蔽点评
     *
     * @param itemids
     * @return
     */
    @RequestMapping("/setInvisible")
    @RequiresPermissions("sys:fhousecomment:delete")
    public R setInvisible(@RequestBody Integer[] itemids) {
//        fHouseCommentService.deleteBatchIds(Arrays.asList(itemids));
        List<FHouseCommentEntity> list = new ArrayList<>();
        for (Integer i : itemids) {
            FHouseCommentEntity fHouseCommentEntity = new FHouseCommentEntity();
            fHouseCommentEntity.setIsHot(2);
            fHouseCommentEntity.setItemid(i);
            list.add(fHouseCommentEntity);
        }

        boolean b = fHouseCommentService.updateBatchById(list);
        if (b) {
            return R.ok();
        }
        return R.error("设置失败");
    }


    @RequestMapping("/setHot")
    @RequiresPermissions("sys:fhousecomment:delete")
    public R setHot(@RequestBody Integer[] itemids) {
//        fHouseCommentService.deleteBatchIds(Arrays.asList(itemids));
        List<FHouseCommentEntity> list = new ArrayList<>();
        for (Integer i : itemids) {
            FHouseCommentEntity fHouseCommentEntity = fHouseCommentService.selectById(i);
            if (fHouseCommentEntity.getIsHot() == 2) {
/**
 * 如果点评已经设置成不可见，则不能设为热门
 */
            } else {
                fHouseCommentEntity = new FHouseCommentEntity();
                fHouseCommentEntity.setIsHot(1);
                fHouseCommentEntity.setItemid(i);
                list.add(fHouseCommentEntity);
            }

        }
        if (list.size() == 0) {
            return R.error("请先将不可见的点评恢复再设为热门");
        }

        boolean b = fHouseCommentService.updateBatchById(list);
        if (b) {
            return R.ok();
        }
        return R.error("设置失败");
    }


}
