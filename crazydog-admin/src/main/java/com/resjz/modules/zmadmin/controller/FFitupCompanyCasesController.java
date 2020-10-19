package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FFitupCompanyCasesEntity;
import com.resjz.common.service.zmadmin.FFitupCompanyCasesService;
import com.resjz.common.utils.*;
import com.resjz.common.validator.ValidatorUtils;
import com.resjz.modules.sys.controller.AbstractController;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/**
 * 装修公司案例
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/ffitupcompanycases")
public class FFitupCompanyCasesController extends AbstractController {
    @Autowired
    private FFitupCompanyCasesService fFitupCompanyCasesService;


    @Value("${upload.savePath}")
    private String savePath;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:ffitupcompanycases:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = fFitupCompanyCasesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{caseId}")
    @RequiresPermissions("sys:ffitupcompanycases:info")
    public R info(@PathVariable("caseId") Integer caseId) {
        FFitupCompanyCasesEntity fFitupCompanyCases = fFitupCompanyCasesService.selectById(caseId);

        return R.ok().put("fFitupCompanyCases", fFitupCompanyCases);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:ffitupcompanycases:save")
    public R save(@RequestBody FFitupCompanyCasesEntity fFitupCompanyCases) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = sdf.format(date);
        Date parse = null;
        try {
            parse = sdf.parse(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        FFitupCompanyCasesEntity fFitupCompanyCasesEntity  =new FFitupCompanyCasesEntity();
//        FFitupCompanyCasesEntity fFitupCompanyCases = MapUtil.map2JavaBean( fFitupCompanyCasesEntity,params,true);
        String ec="";
        try {
            ec = URLDecoder.decode(fFitupCompanyCases.getContent(),"utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        fFitupCompanyCases.setContent(ec);
        fFitupCompanyCases.setAddtime(parse);
        fFitupCompanyCases.setAdduserid(getUserId());

        fFitupCompanyCasesService.insert(fFitupCompanyCases);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:ffitupcompanycases:update")
    public R update(@RequestBody FFitupCompanyCasesEntity fFitupCompanyCases) throws InvocationTargetException, IllegalAccessException {

//        FFitupCompanyCasesEntity fFitupCompanyCases  =new FFitupCompanyCasesEntity();
//        FFitupCompanyCasesEntity fFitupCompanyCasesEntity = MapUtil.map2JavaBean( fFitupCompanyCases,params,true);
        String ec="";
        try {
            ec = URLDecoder.decode(fFitupCompanyCases.getContent(),"utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        fFitupCompanyCases.setContent(ec);
        fFitupCompanyCases.setUpdatetime(new Date());
        ValidatorUtils.validateEntity(fFitupCompanyCases);
        fFitupCompanyCasesService.updateAllColumnById(fFitupCompanyCases);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:ffitupcompanycases:delete")
    public R delete(@RequestBody Integer[] caseIds) {
        for (Integer i : caseIds) {
            FFitupCompanyCasesEntity fFitupCompanyCasesEntity = fFitupCompanyCasesService.selectById(i);
            if (fFitupCompanyCasesEntity!=null&&fFitupCompanyCasesEntity.getMainImg()!=null){
                String  url=savePath+fFitupCompanyCasesEntity.getMainImg();
                File serverFile = new File(url);
                if (serverFile.exists() && serverFile.isFile()
                        && serverFile.delete() == true) {


                }
            }

        }

        fFitupCompanyCasesService.deleteBatchIds(Arrays.asList(caseIds));

        return R.ok();
    }

}
