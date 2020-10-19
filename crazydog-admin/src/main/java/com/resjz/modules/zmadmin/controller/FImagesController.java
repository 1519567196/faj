package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FImagesEntity;
import com.resjz.common.service.zmadmin.FImagesService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/**
 * 装修相册
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fimages")
public class FImagesController {
    @Autowired
    private FImagesService fImagesService;
    @Value("${upload.savePath}")
    private String savePath;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fimages:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = fImagesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fimages:info")
    public R info(@PathVariable("itemid") Integer itemid) {
        FImagesEntity fImages = fImagesService.selectById(itemid);

        return R.ok().put("fImages", fImages);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fimages:save")
    public R save(@RequestBody FImagesEntity fImages) {
        fImages.setAddtime(new Date());
        fImagesService.insert(fImages);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fimages:update")
    public R update(@RequestBody FImagesEntity fImages) {
        ValidatorUtils.validateEntity(fImages);
        fImagesService.updateAllColumnById(fImages);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fimages:delete")
    public R delete(@RequestBody Integer[] itemids) {
        for (Integer i : itemids
        ) {
            FImagesEntity fImagesEntity = fImagesService.selectById(i);
            if (fImagesEntity!=null&&fImagesEntity.getImage()!=null){
                String  url=savePath+fImagesEntity.getImage();
                File serverFile = new File(url);
                if (serverFile.exists() && serverFile.isFile()
                        && serverFile.delete() == true) {


                }
            }


        }
        fImagesService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
