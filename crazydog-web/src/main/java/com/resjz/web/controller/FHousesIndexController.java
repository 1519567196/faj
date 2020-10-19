package com.resjz.web.controller;

import com.resjz.common.dao.zmadmin.entity.FHousesEntity;
import com.resjz.common.service.zmadmin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * 楼盘
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@Controller
@RequestMapping("fhousesIndex")
public class FHousesIndexController {
    @Autowired
    private FHousesService fHousesService;
    @Autowired
    private FHouseimgSortService fHouseimgSortService;






    @Autowired
    private FHousePriceLogService fHousePriceLogService;

    @Autowired
    private FHouseSkuService fHouseSkuService;

    @Autowired
    private FCommonAttrService fCommonAttrService;



    @Autowired
    private FCommonAttrValueService fCommonAttrValueService;
    @Autowired
    private FAreaService fAreaService;

    /**
     * 列表
     */
    @RequestMapping("/lpIndex.html")
    public String list(@RequestParam Map<String, Object> params, Model model, HttpServletRequest req) {
        System.out.println(params);
        FHousesEntity fHousesEntityIndex = fHousesService.getIndex(params);
        model.addAttribute("fhIndex",fHousesEntityIndex);



        return "lpIndex";
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
