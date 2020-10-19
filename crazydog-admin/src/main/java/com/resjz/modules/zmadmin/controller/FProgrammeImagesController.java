package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FProgrammeImagesEntity;
import com.resjz.common.service.zmadmin.FProgrammeImagesService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import com.resjz.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 我家方案-设计图
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fprogrammeimages")
public class FProgrammeImagesController extends AbstractController {
    @Autowired
    private FProgrammeImagesService fProgrammeImagesService;

    @Value("${upload.savePath}")
    private String savePath;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fprogrammeimages:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = fProgrammeImagesService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/onlyList")
    @RequiresPermissions("sys:fprogrammeimages:list")
    public R onlyList() {
        List<FProgrammeImagesEntity> fProgrammeImagesEntities = fProgrammeImagesService.selectList(null);

        return R.ok().put("list", fProgrammeImagesEntities);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fprogrammeimages:info")
    public R info(@PathVariable("itemid") Integer itemid) {
        FProgrammeImagesEntity fProgrammeImages = fProgrammeImagesService.selectById(itemid);

        return R.ok().put("fProgrammeImages", fProgrammeImages);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fprogrammeimages:save")
    public R save(@RequestBody FProgrammeImagesEntity fProgrammeImages) {
        fProgrammeImages.setAddtime(new Date());
        fProgrammeImages.setAddUserid(getUserId());
        fProgrammeImagesService.insert(fProgrammeImages);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fprogrammeimages:update")
    public R update(@RequestBody FProgrammeImagesEntity fProgrammeImages) {
        fProgrammeImages.setUpdatetime(new Date());
        ValidatorUtils.validateEntity(fProgrammeImages);
        fProgrammeImagesService.updateAllColumnById(fProgrammeImages);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fprogrammeimages:delete")
    public R delete(@RequestBody Integer[] itemids) {
        fProgrammeImagesService.deleteBatchIds(Arrays.asList(itemids));

        for (Integer i : itemids) {
            FProgrammeImagesEntity fProgrammeImagesEntity = fProgrammeImagesService.selectById(i);
            if (fProgrammeImagesEntity != null && fProgrammeImagesEntity.getImage() != null) {
                String imgurl = savePath + fProgrammeImagesEntity.getImage();
//        System.out.println(imgurl+"         nnnn");
                File serverFile = new File(imgurl);
                if (serverFile.exists() && serverFile.isFile()
                        && serverFile.delete() == true) {


                }

            }


        }

        return R.ok();
    }

}
