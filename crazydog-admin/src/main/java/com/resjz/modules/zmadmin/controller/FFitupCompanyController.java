package com.resjz.modules.zmadmin.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.*;
import com.resjz.common.service.zmadmin.FCommonAttrService;
import com.resjz.common.service.zmadmin.FCommonAttrValueService;
import com.resjz.common.service.zmadmin.FFitupCompanyService;
import com.resjz.common.service.zmadmin.FFitupCompanySkuService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import com.resjz.modules.sys.controller.AbstractController;
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
@RequestMapping("sys/ffitupcompany")
public class FFitupCompanyController extends AbstractController {
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
    @RequiresPermissions("sys:ffitupcompany:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = fFitupCompanyService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/onlyList")
    @RequiresPermissions("sys:ffitupcompany:list")
    public R onlyList(@RequestParam Map<String, Object> params) {
        List<FFitupCompanyEntity> status = fFitupCompanyService.selectList(new EntityWrapper<FFitupCompanyEntity>().eq("status", 0));


        return R.ok().put("list", status);
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

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:ffitupcompany:save")
    public R save(@RequestBody FFitupCompanyEntity fFitupCompany) {


        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = sdf.format(date);
        Date parse = null;
        try {
            parse = sdf.parse(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        fFitupCompany.setAddtime(parse);
        fFitupCompany.setAddUserid(getUserId());
        fFitupCompanyService.insert(fFitupCompany);


        FFitupCompanyEntity one = fFitupCompanyService.selectOne(new EntityWrapper<FFitupCompanyEntity>().eq("addtime", parse));
       if (fFitupCompany.getAttrandattrValue()!=null&&fFitupCompany.getAttrandattrValue().size()>0){
           for (FFitupCompanySkuEntity f : fFitupCompany.getAttrandattrValue()
           ) {

               f.setCompanyId(one.getItemid());

           }
           fFitupCompanySkuService.addSkus(fFitupCompany.getAttrandattrValue());
       }


        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:ffitupcompany:update")
    public R update(@RequestBody FFitupCompanyEntity fFitupCompany) {
        fFitupCompany.setUpdatetime(new Date());
        ValidatorUtils.validateEntity(fFitupCompany);
        fFitupCompanyService.updateAllColumnById(fFitupCompany);//全部更新
        for (FFitupCompanySkuEntity f : fFitupCompany.getAttrandattrValue()
        ) {

            f.setCompanyId(fFitupCompany.getItemid());

        }
        fFitupCompanySkuService.addSkus(fFitupCompany.getAttrandattrValue());

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:ffitupcompany:delete")
    public R delete(@RequestBody Integer[] itemids) {

        for (Integer i : itemids
        ) {
            FFitupCompanyEntity fFitupCompanyEntity = fFitupCompanyService.selectById(i);
            if (fFitupCompanyEntity!=null&&fFitupCompanyEntity.getImage()!=null){
                String  url=savePath+fFitupCompanyEntity.getImage();
                File serverFile = new File(url);
                if (serverFile.exists() && serverFile.isFile()
                        && serverFile.delete() == true) {


                }
            }

        }


        fFitupCompanyService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
