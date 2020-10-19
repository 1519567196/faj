package com.resjz.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FAreaEntity;
import com.resjz.common.dao.zmadmin.entity.FFitupCompanyEntity;
import com.resjz.common.dao.zmadmin.entity.FPediaTypeEntity;
import com.resjz.common.service.zmadmin.*;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 百科分类
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@Controller
public class FPediaTypeController {
    @Autowired
    private FPediaTypeService fPediaTypeService;

    @Autowired
    private FFitupCompanyService fFitupCompanyService;

    @Autowired
    private FAreaService fAreaService;




    /**
     * 列表
     */
    @RequestMapping("/zxbk.html")
    public String list(Model model) {
        try {//分类列表
            fPediaTypeService.getMainList(model);
        } catch (Exception e) {
            e.printStackTrace();
        }




        try {  //装修公司排行前三
            List<FFitupCompanyEntity> fFitupCompanyEntities =
                    fFitupCompanyService.selectList(new EntityWrapper<FFitupCompanyEntity>()
                            .orderBy("sort", false)
                    );

            for (FFitupCompanyEntity f : fFitupCompanyEntities) {

                FAreaEntity fAreaEntity = fAreaService.selectById(f.getAreaid());
                f.setAreaName(fAreaEntity == null ? "" : fAreaEntity.getAreaName() == null ? "" : fAreaEntity.getAreaName());
            }
            if (fFitupCompanyEntities.size() > 3) {
                model.addAttribute("fFitupCompanyEntities", fFitupCompanyEntities.subList(0, 3));
                List<FFitupCompanyEntity> list1 = new ArrayList<>();
                List<FFitupCompanyEntity> list2 = new ArrayList<>();
                List<FFitupCompanyEntity> list3 = new ArrayList<>();
                List<FFitupCompanyEntity> list = fFitupCompanyEntities.subList(3, fFitupCompanyEntities.size());
                int bj = 1;
                for (int i = 0; i < list.size(); i++) {

                    if (bj % 3 == 1) {
                        list1.add(list.get(i));
                        bj++;
                    } else if (bj % 3 == 2) {
                        list2.add(list.get(i));
                        bj++;
                    } else if (bj % 3 == 0) {
                        list3.add(list.get(i));
                        bj++;
                    }

                }
                model.addAttribute("listComp1", list1);
                model.addAttribute("listComp2", list2);
                model.addAttribute("listComp3", list3);


            } else {
                model.addAttribute("fFitupCompanyEntities", fFitupCompanyEntities.subList(0, fFitupCompanyEntities.size()));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return "zxbk";
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fpediatype:info")
    public R info(@PathVariable("itemid") Integer itemid) {
        FPediaTypeEntity fPediaType = fPediaTypeService.selectById(itemid);

        return R.ok().put("fPediaType", fPediaType);
    }


}
