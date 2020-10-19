package com.resjz.modules.zmadmin.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FArticleTypeEntity;
import com.resjz.common.service.zmadmin.FArticleTypeService;
//import io.renren.common.validator.ValidatorUtils;
import com.resjz.common.validator.Assert;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.resjz.sys.entity.FArticleTypeEntity;
//import com.resjz.sys.service.FArticleTypeService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;


/**
 * 材建资讯分类
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-09-18 08:05:45
 */
@RestController
@RequestMapping("sys/farticletype")
public class FArticleTypeController {
    @Autowired
    private FArticleTypeService fArticleTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:farticletype:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = fArticleTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 列表
     */
    @RequestMapping("/onlylist")
    @RequiresPermissions("sys:farticletype:list")
    public R onlylist(@RequestBody Map<String, Object> params) {
        List<FArticleTypeEntity> fArticleTypeEntities = fArticleTypeService.selectList(new EntityWrapper<FArticleTypeEntity>()
                .eq(params.get("atType") != null, "at_type", params.get("atType")));


        return R.ok().put("list", fArticleTypeEntities);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{typeid}")
    @RequiresPermissions("sys:farticletype:info")
    public R info(@PathVariable("typeid") Integer typeid) {
        FArticleTypeEntity fArticleType = fArticleTypeService.selectById(typeid);

        return R.ok().put("fArticleType", fArticleType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:farticletype:save")
    public R save(@RequestBody FArticleTypeEntity fArticleType) {
        Assert.isBlank(fArticleType.getTypeName(), "分类名称不能为空");
        Assert.isNull(fArticleType.getSort(), "排序不能为空");
        fArticleType.setAddtime(new Date());


        fArticleTypeService.insert(fArticleType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:farticletype:update")
    public R update(@RequestBody FArticleTypeEntity fArticleType) {
        Assert.isBlank(fArticleType.getTypeName(), "分类名称不能为空");
        Assert.isNull(fArticleType.getSort(), "排序不能为空");
        fArticleType.setAddtime(new Date());
        fArticleType.setUpdatetime(new Date());


        ValidatorUtils.validateEntity(fArticleType);
        fArticleTypeService.updateAllColumnById(fArticleType);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:farticletype:delete")
    public R delete(@RequestBody Integer[] typeids) {
        fArticleTypeService.deleteBatchIds(Arrays.asList(typeids));

        return R.ok();
    }

}
