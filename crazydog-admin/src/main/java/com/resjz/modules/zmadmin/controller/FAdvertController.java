package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FAdvertEntity;
import com.resjz.common.service.zmadmin.FAdvertService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.Assert;
import com.resjz.common.validator.ValidatorUtils;
import com.resjz.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;


/**
 * 广告
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fadvert")
public class FAdvertController extends AbstractController {
    @Autowired
    private FAdvertService fAdvertService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fadvert:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fAdvertService.queryPage(params);


        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fadvert:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FAdvertEntity fAdvert = fAdvertService.selectById(itemid);

        return R.ok().put("fAdvert", fAdvert);
    }




    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fadvert:save")
    public R save(@RequestBody FAdvertEntity fAdvert){


        fAdvert.setAdPlaceId(fAdvert.getAdPlaceId());
        Assert.isNull(fAdvert.getImgurl(),"图片地址不能为空");
        //System.out.println("=============="+fAdvert.getImgurl());

        Assert.isNull(fAdvert.getTitle(),"标题不能为空");
        Assert.isNull(fAdvert.getTourl(),"跳转链接不能为空");



        fAdvert.setSort(fAdvert.getSort());

        fAdvert.setAddtime(new Date());
        fAdvert.setAdduserid(getUserId());
        fAdvert.setUpdatetime(fAdvert.getUpdatetime());
        fAdvert.setAreaid(fAdvert.getAreaid());

        fAdvertService.insert(fAdvert);

        return R.ok();
    }

    /**
     * 分类
     */
    @RequestMapping("/typeList")
    public R typeList(@RequestParam Map<String, Object> params){

        List listType = fAdvertService.selectAll();


        return R.ok().put("list", listType);
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fadvert:update")
    public R update(@RequestBody FAdvertEntity fAdvert){

        fAdvert.setAdPlaceId(fAdvert.getAdPlaceId());

        Assert.isNull(fAdvert.getImgurl(),"图片地址不能为空");
        //System.out.println("=============="+fAdvert.getImgurl());

        Assert.isNull(fAdvert.getTitle(),"标题不能为空");
        Assert.isNull(fAdvert.getTourl(),"跳转链接不能为空");

        ValidatorUtils.validateEntity(fAdvert);
        fAdvert.setAdduserid(getUserId());

        fAdvert.setAddtime(new Date());
        fAdvertService.updateAllColumnById(fAdvert);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fadvert:delete")
    public R delete(@RequestBody Integer[] itemids){
        fAdvertService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

    /**
     * 状态：启用或禁用
     */
    @RequestMapping("/recommend/{itemid}")
    //   @RequiresPermissions("sys:tcase:recommend")
    public R recommend(@PathVariable("itemid") Integer itemid){

        FAdvertEntity fAdvers = fAdvertService.selectById(itemid);
        if(fAdvers!=null){

            if(fAdvers.getStatus()!=null&&fAdvers.getStatus()==0){
                fAdvers.setStatus(1);
            }else{
                fAdvers.setStatus(0);
            }
            fAdvertService.updateById(fAdvers);
        }

        return R.ok().put("msg","操作成功");
    }

}
