package com.resjz.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FAdvertEntity;
import com.resjz.common.dao.zmadmin.entity.FPediaEntity;
import com.resjz.common.dao.zmadmin.entity.FPediaTypeEntity;
import com.resjz.common.service.zmadmin.FAdvertService;
import com.resjz.common.service.zmadmin.FPediaService;
import com.resjz.common.service.zmadmin.FPediaTypeService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.Assert;
import com.resjz.common.validator.ValidatorUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;


/**
 * 百科
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@Controller
@RequestMapping("fpedia")
public class FPediaController {
    @Autowired
    private FPediaService fPediaService;
    @Autowired
    private FPediaTypeService fPediaTypeService;
    @Autowired
    private FAdvertService fAdvertService;
    /**
     * 列表
     */
//    @RequestMapping("/list")
//    public String list(Model model, HttpServletRequest req, HttpServletResponse response) {
//        Model model1 = (Model) req.getAttribute("model");
//        Map<String, Object> stringObjectMap = model1.asMap();
//        model.addAllAttributes(stringObjectMap);
//        List<FPediaEntity> fPediaEntities = fPediaService.selectList(new EntityWrapper<FPediaEntity>().
//                eq("is_delete", 0).
//                eq("recommend", 1).last("limit 5"));
//        FPediaEntity fPediaEntity = fPediaEntities.get(0);
//        if (fPediaEntities.size()>1){
//            List<FPediaEntity> fPediaEntities1 = fPediaEntities.subList(1, fPediaEntities.size());
//            model.addAttribute("fPediaEntities",fPediaEntities1);
//        }
//
//
//        model.addAttribute("fPediaEntity",fPediaEntity);
//        return "index";
//    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public String list(Integer type,  Model model, HttpServletRequest req, HttpServletResponse response) {


        //广告
        List<FAdvertEntity> advertList = fAdvertService.selectList(new EntityWrapper<FAdvertEntity>().eq(true,"ad_place_id",19));
        model.addAttribute("advertList", advertList);


        Map params = new HashMap();
        params.put("typeid",type);
        params.put("isDelete",0);
        List<FPediaEntity> pediaList = fPediaService.selectByMap(params);


        model.addAttribute("type",type);
        model.addAttribute("pediaList",pediaList);

        return "pediaList";
    }




    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    public R info(@PathVariable("itemid") Integer itemid) {
        FPediaEntity fPedia = fPediaService.selectById(itemid);

        return R.ok().put("fPedia", fPedia);
    }


}
