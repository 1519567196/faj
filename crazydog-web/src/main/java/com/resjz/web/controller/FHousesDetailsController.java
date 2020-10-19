package com.resjz.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FAreaEntity;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrEntity;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrValueEntity;
import com.resjz.common.service.zmadmin.*;
import com.resjz.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * 楼盘
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@Controller
@RequestMapping("fhousesDetails")
public class FHousesDetailsController {
    @Autowired
    private FHousesService fHousesService;
    @Autowired
    private FHouseimgSortService fHouseimgSortService;

    @Autowired
    private FHouseArticleService fHouseArticleService;

    @Autowired
    private FHouseTypeImagesService fHouseTypeImagesService;


    @Autowired
    private FHousePriceLogService fHousePriceLogService;

    @Autowired
    private FHouseSkuService fHouseSkuService;

    @Autowired
    private FCommonAttrService fCommonAttrService;

    @Autowired
    private FHouseImagesService fHouseImagesService;

    @Autowired
    private FCommonAttrValueService fCommonAttrValueService;
    @Autowired
    private FAreaService fAreaService;

    /**
     * 列表
     */
    @RequestMapping("/lpDetails.html")
    public String list(@RequestParam Map<String, Object> params, Model model, HttpServletRequest req) {
        System.out.println(params);

        return "lpDetails";
    }



    public static Object[] oneClear(Object[] arr) {
        Set set = new HashSet();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && !arr[i].toString().equals("")) {
                set.add(arr[i]);
            }

        }
        return set.toArray();
    }



}
