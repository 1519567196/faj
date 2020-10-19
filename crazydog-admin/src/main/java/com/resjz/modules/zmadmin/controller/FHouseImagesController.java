package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FHouseImagesEntity;
import com.resjz.common.dao.zmadmin.entity.FHouseimgSortEntity;
import com.resjz.common.dao.zmadmin.entity.FImagesEntity;
import com.resjz.common.service.zmadmin.FHouseImagesService;
import com.resjz.common.service.zmadmin.FHouseimgSortService;
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
import java.util.Map;



/**
 * 楼盘相册
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fhouseimages")
public class FHouseImagesController  extends AbstractController {
    @Autowired
    private FHouseImagesService fHouseImagesService;

    @Autowired
    private FHouseimgSortService fHouseimgSortService;


    @Value("${upload.savePath}")
    private  String savePath;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fhouseimages:list")
    public R list(@RequestParam Map<String, Object> params){
        try {
       Integer.valueOf(params.get("sortId").toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return  R.error("参数错误");
        }
        PageUtils page = fHouseImagesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fhouseimages:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FHouseImagesEntity fHouseImages = fHouseImagesService.selectById(itemid);

        return R.ok().put("fHouseImages", fHouseImages);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fhouseimages:save")
    public R save(@RequestBody FHouseImagesEntity fHouseImages){
        fHouseImages.setAddtime(new Date());
        fHouseImages.setAddUserid(getUserId());

        fHouseImagesService.insert(fHouseImages);
        FHouseimgSortEntity   fHouseimgSortEntity=new FHouseimgSortEntity();
        fHouseimgSortEntity.setIaId(fHouseImages.getSortId());
        fHouseimgSortEntity.setImgCount(fHouseimgSortService.selectById(fHouseImages.getSortId()).getImgCount()+1);
        fHouseimgSortService.updateById(fHouseimgSortEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fhouseimages:update")
    public R update(@RequestBody FHouseImagesEntity fHouseImages){
        ValidatorUtils.validateEntity(fHouseImages);
        fHouseImagesService.updateAllColumnById(fHouseImages);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fhouseimages:delete")
    public R delete(@RequestBody Integer[] itemids){
        if (itemids.length<=0){
            return   R.error("未获取到待删除信息");
        }
        fHouseImagesService.deleteBatchIds(Arrays.asList(itemids));
        FHouseImagesEntity fHouseImagesEntity = fHouseImagesService.selectById(itemids[0]);
        String imgurl = savePath + fHouseImagesEntity.getImage();
//        System.out.println(imgurl+"         nnnn");
        File serverFile = new File(imgurl);
        if (serverFile.exists() && serverFile.isFile()
                && serverFile.delete() == true) {


        }

        FHouseimgSortEntity   fHouseimgSortEntity=new FHouseimgSortEntity();
        fHouseimgSortEntity.setIaId(fHouseImagesEntity.getSortId());
        fHouseimgSortEntity.setImgCount(fHouseimgSortService.selectById(fHouseImagesEntity.getSortId()).getImgCount()-1);
        fHouseimgSortService.updateById(fHouseimgSortEntity);
        return R.ok();
    }


}
