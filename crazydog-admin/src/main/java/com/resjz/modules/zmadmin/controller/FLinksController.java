package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FLinksEntity;
import com.resjz.common.service.zmadmin.FLinksService;
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
 * 友情链接
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/flinks")
public class FLinksController extends AbstractController {
    @Autowired
    private FLinksService fLinksService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:flinks:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fLinksService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:flinks:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FLinksEntity fLinks = fLinksService.selectById(itemid);

        return R.ok().put("fLinks", fLinks);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:flinks:save")
    public R save(@RequestBody FLinksEntity fLinks){
        Assert.isBlank(fLinks.getTitle(),"名称不能为空");
        Assert.isNull(fLinks.getUrl(),"链接不能为空");
        fLinks.setAddtime(new Date());
        fLinks.setAddUserid(getUserId());
        fLinksService.insert(fLinks);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:flinks:update")
    public R update(@RequestBody FLinksEntity fLinks){
        Assert.isBlank(fLinks.getTitle(),"标题不能为空");
        Assert.isNull(fLinks.getUrl(),"链接不能为空");
        fLinks.setUpdatetime(new Date());
        fLinks.setAddUserid(getUserId());
        ValidatorUtils.validateEntity(fLinks);
        fLinksService.updateAllColumnById(fLinks);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:flinks:delete")
    public R delete(@RequestBody Integer[] itemids){
        fLinksService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
