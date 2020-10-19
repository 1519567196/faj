package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FPediaEntity;
import com.resjz.common.dao.zmadmin.entity.FPediaTypeEntity;
import com.resjz.common.service.zmadmin.FPediaService;
import com.resjz.common.service.zmadmin.FPediaTypeService;
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
import java.util.Arrays;
import java.util.Date;
import java.util.Map;



/**
 * 百科
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fpedia")
public class FPediaController extends AbstractController {
    @Autowired
    private FPediaService fPediaService;
    @Autowired
    private FPediaTypeService fPediaTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fpedia:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fPediaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fpedia:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FPediaEntity fPedia = fPediaService.selectById(itemid);

        return R.ok().put("fPedia", fPedia);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fpedia:save")
    public R save(@RequestBody FPediaEntity fPedia){

        Assert.isBlank(fPedia.getTitle(), "标题不能为空");
        Assert.isNull(fPedia.getSubTitle(), "摘要不能为空");
        Assert.isNull(fPedia.getTypeid(), "分类不能为空");
        Assert.isNull(fPedia.getContent(), "内容不能为空");

        String ec="";
        try {
            ec = URLDecoder.decode(fPedia.getContent(),"utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        fPedia.setAddtime(new Date());
        fPedia.setAddUserid(getUserId().intValue());

        String pid=""+fPedia.getTypeid();
        if(fPedia.getTypeid()!=null){
            FPediaTypeEntity typeEntity = fPediaTypeService.selectById(fPedia.getTypeid());
            if(typeEntity.getParentid()!=0){
                pid=typeEntity.getParentid()+","+pid;
            }
        }
        fPedia.setSubCateIds(pid);
        fPediaService.insert(fPedia);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fpedia:update")
    public R update(@RequestBody FPediaEntity fPedia){
        ValidatorUtils.validateEntity(fPedia);
        fPediaService.updateAllColumnById(fPedia);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fpedia:delete")
    public R delete(@RequestBody Integer[] itemids){
        fPediaService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
