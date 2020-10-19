package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FBannersEntity;
import com.resjz.common.service.zmadmin.FBannersService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.Assert;
import com.resjz.common.validator.ValidatorUtils;
import com.resjz.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;



/**
 * banner图
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fbanners")
public class FBannersController extends AbstractController {
    @Autowired
    private FBannersService fBannersService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fbanners:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fBannersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fbanners:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FBannersEntity fBanners = fBannersService.selectById(itemid);

        return R.ok().put("fBanners", fBanners);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fbanners:save")
    public R save(@RequestBody FBannersEntity fBanners){

        Assert.isNull(fBanners.getType(),"类别不能为空");
        fBanners.setAddtime(new Date());
        fBanners.setAddUserid(getUserId());
        fBannersService.insert(fBanners);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fbanners:update")
    public R update(@RequestBody FBannersEntity fBanners){
        Assert.isNull(fBanners.getType(),"类别不能为空");
        fBanners.setUpdatetime(new Date());
        fBanners.setAddUserid(getUserId());

        ValidatorUtils.validateEntity(fBanners);
        fBannersService.updateAllColumnById(fBanners);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fbanners:delete")
    public R delete(@RequestBody Integer[] itemids){
        fBannersService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
