package com.resjz.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.*;
import com.resjz.common.service.zmadmin.*;
import com.resjz.common.utils.PageUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("fhouses")
public class FHousesController {
    @Autowired
    private FHousesService fHousesService;


    @Autowired
    private FCommonAttrService fCommonAttrService;

    @Autowired
    private FCommonAttrValueService fCommonAttrValueService;
    @Autowired
    private FAreaService fAreaService;

    /**
     * 列表
     */
    @RequestMapping("/xinFang.html")
    public String list(@RequestParam Map<String, Object> params, Model model, HttpServletRequest req) {

        /**
         * 获取顶部地区信息areaId1
         */
        String areaId = req.getParameter("areaId1");
        String area2 = req.getParameter("areaId2");
        Integer areaId1 = null;
        Integer areaId2 = null;  //商圈


        if (area2 != null&&!area2.equals("null")&&!area2.equals("")) {


            try {
                areaId2 = Integer.parseInt(area2);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }


        }
        if (areaId == null || areaId.equals("null")||areaId.equals("")) {

            FAreaEntity fAreaEntity = fAreaService.selectById(areaId2);
            areaId1 = fAreaEntity == null ? null : fAreaEntity.getAreaParentId() == null ? null : fAreaEntity.getAreaParentId();


        } else {
            try {
                areaId1 = Integer.parseInt(areaId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
/**
 * 获取规格属性
 *
 */
        List<FCommonAttrEntity> fCommonAttrEntities = null;
        try {
            Map<String, Object> paramsAttr = new HashMap<>();
            paramsAttr.put("type", 3);
            fCommonAttrEntities = fCommonAttrService.selectListCopy(paramsAttr);
            for (FCommonAttrEntity fa : fCommonAttrEntities) {
                List<FCommonAttrValueEntity> fCommonAttrValueEntities = fCommonAttrValueService.selectList(new EntityWrapper<FCommonAttrValueEntity>()
                        .eq("attr_id", fa.getItemid())
                );
                fa.setValues(fCommonAttrValueEntities);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 获取参数
         */
        StringBuffer fHousesSelAttrV = new StringBuffer("");
        if (params.get("fHousesSelAttrV") != null&&!params.get("fHousesSelAttrV").toString().equals("")) {
            String[] fHousesSelAttrVS = params.get("fHousesSelAttrV").toString().split(";");
            Object[] objects = oneClear(fHousesSelAttrVS);
            for (Object s : objects) {
                fHousesSelAttrV.append(s + ";");
            }
            params.put("fHousesSelAttrV",objects);
        }

        String fHousesSearch = "";
        if (params.get("fHousesSearch") != null&&!params.get("fHousesSearch").toString().equals("")) {
            fHousesSearch = params.get("fHousesSearch").toString();
        }

        Integer fHousesSaleStu = null;
        if (params.get("fHousesSaleStu") != null&&!params.get("fHousesSaleStu").toString().equals("")&&!params.get("fHousesSaleStu").toString().equals("null")) {
            fHousesSaleStu = Integer.valueOf(params.get("fHousesSaleStu").toString());
        }else {
            params.put("fHousesSaleStu",null);
        }
        Integer order = null;
        if (params.get("order") != null&&!params.get("order").toString().equals("")&&!params.get("order").toString().equals("null")) {
            order = Integer.valueOf(params.get("order").toString());
//            params.put("order",order);
            if (order==1){
                params.put("orderBy","addtime");
            }else if (order==2){
                params.put("orderBy","price");
            }else   if (order==3){
                params.put("orderBy","views");
            }
        }else {
            params.put("orderBy","addtime");
            params.put("order",null);
        }

        /**
         * 获取地区条件（页面商圈）
         */
        List<FAreaEntity> fAreaEntities = fAreaService.selectList(new EntityWrapper<FAreaEntity>().eq("area_parent_id", areaId1));
        List<Integer> listAreas = new ArrayList<>();
        if (areaId2==null||areaId2 == 1) {  //如果是查询所有 商圈  封装商圈信息sql查询

            for (FAreaEntity fa : fAreaEntities) {
                listAreas.add(fa.getAreaId());

            }
            params.put("listAreas",listAreas);
        }else {
            listAreas.add(areaId2);
            params.put("listAreas",listAreas);
        }


        model.addAttribute("fAreaEntities", fAreaEntities);
        model.addAttribute("fCommonAttrEntities", fCommonAttrEntities);
        params.put("limit", 7+"");
        params.put("page", params.get("page")==null?1+"":params.get("page"));
        params.put("areaId2",areaId2);
        PageUtils page = fHousesService.listPage(params);
        model.addAttribute("fHouses", page);
        model.addAttribute("fHousesAreaClass", areaId2);
        model.addAttribute("areaId1", areaId1);
        model.addAttribute("fHousesSelAttrV", fHousesSelAttrV);
        model.addAttribute("fHousesSearch", fHousesSearch);
        model.addAttribute("fHousesSaleStu", fHousesSaleStu);
        model.addAttribute("order", order);

        return "xinFang";
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
