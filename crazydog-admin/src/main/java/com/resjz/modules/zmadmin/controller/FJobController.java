package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FJobEntity;
import com.resjz.common.service.zmadmin.FJobService;
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
 * 招聘
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fjob")
public class FJobController extends AbstractController {
    @Autowired
    private FJobService fJobService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fjob:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fJobService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{iobId}")
    @RequiresPermissions("sys:fjob:info")
    public R info(@PathVariable("iobId") Integer iobId){
        FJobEntity fJob = fJobService.selectById(iobId);

        return R.ok().put("fJob", fJob);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fjob:save")
    public R save(@RequestBody FJobEntity fJob){
        Assert.isBlank(fJob.getJob(),"职位名称不能为空");
        Assert.isNull(fJob.getCount(),"招聘人数不能为空");
        Assert.isNull(fJob.getWorkplace(),"工作地点不能为空");
        fJob.setAddtime(new Date());
        fJob.setAddUserid(getUserId());

        fJobService.insert(fJob);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fjob:update")
    public R update(@RequestBody FJobEntity fJob){
        Assert.isBlank(fJob.getJob(),"职位名称不能为空");
        Assert.isNull(fJob.getCount(),"招聘人数不能为空");
        Assert.isNull(fJob.getWorkplace(),"工作地点不能为空");
        fJob.setUpdatetime(new Date());
        fJob.setAddUserid(getUserId());

        ValidatorUtils.validateEntity(fJob);
        fJobService.updateAllColumnById(fJob);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fjob:delete")
    public R delete(@RequestBody Integer[] iobIds){
        fJobService.deleteBatchIds(Arrays.asList(iobIds));

        return R.ok();
    }

}
