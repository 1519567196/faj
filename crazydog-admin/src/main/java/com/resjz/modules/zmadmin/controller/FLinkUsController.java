package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FLinkUsEntity;
import com.resjz.common.service.zmadmin.FLinkUsService;
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
 * 联系我们
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/flinkus")
public class FLinkUsController extends AbstractController {
    @Autowired
    private FLinkUsService fLinkUsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:flinkus:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fLinkUsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:flinkus:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FLinkUsEntity fLinkUs = fLinkUsService.selectById(itemid);

        return R.ok().put("fLinkUs", fLinkUs);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:flinkus:save")
    public R save(@RequestBody FLinkUsEntity fLinkUs){
        Assert.isBlank(fLinkUs.getCity(),"地区不能为空");
        Assert.isNull(fLinkUs.getMobile(),"手机号不能为空");
        Assert.isNull(fLinkUs.getAddress(),"地址不能为空");
        fLinkUs.setAddtime(new Date());
        fLinkUs.setAddUserid(getUserId());
        fLinkUsService.insert(fLinkUs);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:flinkus:update")
    public R update(@RequestBody FLinkUsEntity fLinkUs){

        Assert.isBlank(fLinkUs.getCity(),"地区不能为空");
        Assert.isNull(fLinkUs.getMobile(),"手机号不能为空");
        Assert.isNull(fLinkUs.getAddress(),"地址不能为空");
        fLinkUs.setUpdatetime(new Date());
        fLinkUs.setAddUserid(getUserId());
        ValidatorUtils.validateEntity(fLinkUs);
        fLinkUsService.updateAllColumnById(fLinkUs);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:flinkus:delete")
    public R delete(@RequestBody Integer[] itemids){
        fLinkUsService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
