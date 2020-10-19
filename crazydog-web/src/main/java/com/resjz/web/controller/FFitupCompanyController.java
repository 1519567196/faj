package com.resjz.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrEntity;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrValueEntity;
import com.resjz.common.dao.zmadmin.entity.FFitupCompanyEntity;
import com.resjz.common.dao.zmadmin.entity.FFitupCompanySkuEntity;
import com.resjz.common.service.zmadmin.FCommonAttrService;
import com.resjz.common.service.zmadmin.FCommonAttrValueService;
import com.resjz.common.service.zmadmin.FFitupCompanyService;
import com.resjz.common.service.zmadmin.FFitupCompanySkuService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 装修公司
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("/ffitupcompany")
public class FFitupCompanyController {
    @Autowired
    private FFitupCompanyService fFitupCompanyService;

    @Autowired
    private FFitupCompanySkuService fFitupCompanySkuService;


    @Autowired
    private FCommonAttrService fCommonAttrService;

    @Autowired
    private FCommonAttrValueService fCommonAttrValueService;

    @Value("${upload.savePath}")
    private String savePath;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = fFitupCompanyService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/rankList")
    public R rankList(@RequestParam Map<String, Object> params) {
        List<FFitupCompanyEntity> fFitupCompanyEntity = fFitupCompanyService.
                selectList(
                        new EntityWrapper<FFitupCompanyEntity>().
                                eq("status", 0).
                                orderBy("sort", false).last("limit 5"));


        return R.ok().put("fitupCompanys", fFitupCompanyEntity);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:ffitupcompany:info")
    public R info(@PathVariable("itemid") Integer itemid) {
        FFitupCompanyEntity fFitupCompany = fFitupCompanyService.selectById(itemid);


        List<FFitupCompanySkuEntity> skus = fFitupCompanySkuService.selectList(new EntityWrapper<FFitupCompanySkuEntity>().eq("company_id", itemid));
        for (FFitupCompanySkuEntity f : skus) {
            FCommonAttrEntity fCommonAttrEntity = fCommonAttrService.selectById(f.getAttrId());
            if (fCommonAttrEntity == null) {
                skus.remove(f);
                continue;
            }
            f.setAttr(fCommonAttrEntity == null ? "" : fCommonAttrEntity.getTitle());

            FCommonAttrValueEntity fCommonAttrValueEntity = fCommonAttrValueService.selectById(f.getAttrValueId());
            if (fCommonAttrValueEntity == null) {
                skus.remove(f);
                continue;
            }

            f.setAttrValue(fCommonAttrValueEntity.getTitle());

        }


        return R.ok().put("fFitupCompany", fFitupCompany).put("skus", skus)
                ;
    }


}
