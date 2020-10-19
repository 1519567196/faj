package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.sys.entity.SysUserEntity;
import com.resjz.common.dao.zmadmin.entity.FHouseArticleEntity;
import com.resjz.common.service.zmadmin.FHouseArticleService;
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
 * 楼盘态动
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fhousearticle")
public class FHouseArticleController   extends AbstractController {
    @Autowired
    private FHouseArticleService fHouseArticleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fhousearticle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fHouseArticleService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fhousearticle:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FHouseArticleEntity fHouseArticle = fHouseArticleService.selectById(itemid);

        return R.ok().put("fHouseArticle", fHouseArticle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fhousearticle:save")
    public R save(@RequestBody FHouseArticleEntity fHouseArticle){
        fHouseArticle.setAddtime(new Date());
        fHouseArticle.setAddUserid(getUserId());
        fHouseArticleService.insert(fHouseArticle);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fhousearticle:update")
    public R update(@RequestBody FHouseArticleEntity fHouseArticle){
        ValidatorUtils.validateEntity(fHouseArticle);
        fHouseArticle.setUpdatetime(new Date());
        fHouseArticleService.updateAllColumnById(fHouseArticle);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fhousearticle:delete")
    public R delete(@RequestBody Integer[] itemids){
        fHouseArticleService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
