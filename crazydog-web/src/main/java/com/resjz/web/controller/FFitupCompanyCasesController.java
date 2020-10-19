package com.resjz.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FFitupCompanyCasesEntity;
import com.resjz.common.service.zmadmin.FFitupCompanyCasesService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 装修公司案例
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@Controller
@RequestMapping("ffitupcompanycases")
public class FFitupCompanyCasesController {
    @Autowired
    private FFitupCompanyCasesService fFitupCompanyCasesService;


    /**
     * 列表
     */
//    @RequestMapping("/list")
//    @RequiresPermissions("sys:ffitupcompanycases:list")
//    public String list(Model model, HttpServletRequest req, HttpServletResponse response) {
//        Model model1 = (Model) req.getAttribute("model");
//        Map<String, Object> stringObjectMap = model1.asMap();
//        model.addAllAttributes(stringObjectMap);
//
//        List<FFitupCompanyCasesEntity> fFitupCompanyCasesEntities = fFitupCompanyCasesService.selectList(new EntityWrapper<FFitupCompanyCasesEntity>().eq("is_public", 1).eq("recommend", 1).last("limit 5"));
//        model.addAttribute("fFitupCompanyCasesEntities", fFitupCompanyCasesEntities);
//
//        /**
//         * 装修百科
//         */
//        try {
//            req.setAttribute("model",model);
//            req.getRequestDispatcher("/fpedia/list").forward(req,response);
//        } catch (IOException | ServletException e) {
//            e.printStackTrace();
//        }
//
//        return "index";
//    }


    /**
     * 信息
     */
    @RequestMapping("/info/{caseId}")
    @RequiresPermissions("sys:ffitupcompanycases:info")
    public R info(@PathVariable("caseId") Integer caseId) {
        FFitupCompanyCasesEntity fFitupCompanyCases = fFitupCompanyCasesService.selectById(caseId);

        return R.ok().put("fFitupCompanyCases", fFitupCompanyCases);
    }


}
