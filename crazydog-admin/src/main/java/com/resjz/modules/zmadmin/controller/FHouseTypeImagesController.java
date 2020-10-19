package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FHouseTypeImagesEntity;
import com.resjz.common.dao.zmadmin.entity.FHouseimgSortEntity;
import com.resjz.common.service.zmadmin.FHouseTypeImagesService;
import com.resjz.common.service.zmadmin.FHouseimgSortService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import com.resjz.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/**
 * 楼盘户型相册
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fhousetypeimages")
public class FHouseTypeImagesController extends AbstractController {
    @Autowired
    private FHouseTypeImagesService fHouseTypeImagesService;

    @Autowired
    private FHouseimgSortService fHouseimgSortService;


    @Value("${upload.savePath}")
    private String savePath;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fhousetypeimages:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = fHouseTypeImagesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fhousetypeimages:info")
    public R info(@PathVariable("itemid") Integer itemid) {
        FHouseTypeImagesEntity fHouseTypeImages = fHouseTypeImagesService.selectById(itemid);

        return R.ok().put("fHouseTypeImages", fHouseTypeImages);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fhousetypeimages:save")
    public R save(@RequestBody FHouseTypeImagesEntity fHouseTypeImages) {
        fHouseTypeImages.setAddUserid(getUserId());
        fHouseTypeImages.setAddtime(new Date());


        fHouseTypeImagesService.insert(fHouseTypeImages);

        FHouseimgSortEntity fHouseimgSortEntity = new FHouseimgSortEntity();
        fHouseimgSortEntity.setIaId(fHouseTypeImages.getSortId());
        fHouseimgSortEntity.setImgCount(fHouseimgSortService.selectById(fHouseTypeImages.getSortId()).getImgCount() + 1);
        fHouseimgSortService.updateById(fHouseimgSortEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fhousetypeimages:update")
    public R update(@RequestBody FHouseTypeImagesEntity fHouseTypeImages) {
        ValidatorUtils.validateEntity(fHouseTypeImages);

        fHouseTypeImagesService.updateAllColumnById(fHouseTypeImages);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fhousetypeimages:delete")
    public R delete(@RequestBody Integer[] itemids) {

        if (itemids.length <= 0) {
            return R.error("未获取到待删除信息");
        }
        fHouseTypeImagesService.deleteBatchIds(Arrays.asList(itemids));
        FHouseTypeImagesEntity fHouseTypeImagesEntity = fHouseTypeImagesService.selectById(itemids[0]);
        String imgurl = savePath + fHouseTypeImagesEntity.getImage();
//        System.out.println(imgurl+"         nnnn");
        File serverFile = new File(imgurl);
        if (serverFile.exists() && serverFile.isFile()
                && serverFile.delete() == true) {


        }


        FHouseimgSortEntity fHouseimgSortEntity = new FHouseimgSortEntity();
        fHouseimgSortEntity.setIaId(fHouseTypeImagesEntity.getSortId());
        fHouseimgSortEntity.setImgCount(fHouseimgSortService.selectById(fHouseTypeImagesEntity.getSortId()).getImgCount() - 1);
        fHouseimgSortService.updateById(fHouseimgSortEntity);
        return R.ok();
    }

}
