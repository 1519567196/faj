package com.resjz.modules.zmadmin.controller;

import java.util.Arrays;
import java.util.Map;


import com.resjz.common.dao.zmadmin.entity.FAnnouncementEntity;
import com.resjz.common.service.zmadmin.FAnnouncementService;
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
 * 个人中心-消息公告
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-30 09:29:09
 */
@RestController
@RequestMapping("sys/fannouncement")
public class FAnnouncementController {
    @Autowired
    private FAnnouncementService fAnnouncementService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fannouncement:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fAnnouncementService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fannouncement:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FAnnouncementEntity fAnnouncement = fAnnouncementService.selectById(itemid);

        return R.ok().put("fAnnouncement", fAnnouncement);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fannouncement:save")
    public R save(@RequestBody FAnnouncementEntity fAnnouncement){
        fAnnouncementService.insert(fAnnouncement);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fannouncement:update")
    public R update(@RequestBody FAnnouncementEntity fAnnouncement){
        ValidatorUtils.validateEntity(fAnnouncement);
        fAnnouncementService.updateAllColumnById(fAnnouncement);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fannouncement:delete")
    public R delete(@RequestBody Integer[] itemids){
        fAnnouncementService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
