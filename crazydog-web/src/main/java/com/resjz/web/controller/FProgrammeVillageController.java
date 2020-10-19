package com.resjz.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FAreaEntity;
import com.resjz.common.dao.zmadmin.entity.FProgrammeVillageEntity;
import com.resjz.common.service.zmadmin.FAreaService;
import com.resjz.common.service.zmadmin.FProgrammeVillageService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 我家方案-小区
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@Controller
@RequestMapping("fprogrammevillage")
public class FProgrammeVillageController {
    @Autowired
    private FProgrammeVillageService fProgrammeVillageService;

    @Autowired
    private FAreaService fAreaService;

    /**
     * 列表
     */
    @RequestMapping("/wjfa1.html")
    public String list(Model model, HttpServletRequest req, HttpServletResponse response) {


        /**
         * 获取顶部地区信息areaId1
         */
        String areaId = req.getParameter("areaId1");
        Integer areaId1 = null;
        Integer areaId2 = null;
        if (areaId == null||areaId.equals("null")) {

            String area = req.getParameter("areaId2");
            if (area != null) {


                try {
                    areaId2 = Integer.parseInt(area);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                FAreaEntity fAreaEntity = fAreaService.selectById(areaId2);
                areaId1 = fAreaEntity == null ? null : fAreaEntity.getAreaParentId() == null ? null : fAreaEntity.getAreaParentId();


            }

        }else {
            try {
                areaId1 = Integer.parseInt(areaId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }




        List<FAreaEntity> fAreaEntities = fAreaService.selectList(new EntityWrapper<FAreaEntity>().eq("area_parent_id", areaId1));
        model.addAttribute("fAreaEntities", fAreaEntities);


        Map<String, Object> map = new HashMap<>();
        map.put("page", req.getParameter("page")==null?1+"":req.getParameter("page"));
        map.put("limit", 9+"");
        map.put("areaid",areaId2==null?fAreaEntities.get(0).getAreaId(): areaId2.toString());
        PageUtils pageUtils = fProgrammeVillageService.listPage(map);

        model.addAttribute("fProgrammeVillagePage", pageUtils);
        areaId2=areaId2==null?fAreaEntities.get(0).getAreaId(): areaId2;
        model.addAttribute("areaid2", areaId2);


//        PageUtils page = fProgrammeVillageService.queryPage(params);

        return "wjfa1";
    }

    @RequestMapping("/onlyList")
    public R onlyList(@RequestParam Map<String, Object> params) {
        List<FProgrammeVillageEntity> fProgrammeVillageEntities = fProgrammeVillageService.selectList(null);

        return R.ok().put("list", fProgrammeVillageEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    public R info(@PathVariable("itemid") Integer itemid) {
        FProgrammeVillageEntity fProgrammeVillage = fProgrammeVillageService.selectById(itemid);
        Integer[] value = new Integer[3];
        if (fProgrammeVillage.getProvinceid() != null) {
            value[0] = fProgrammeVillage.getProvinceid();
        }

        if (fProgrammeVillage.getCityid() != null) {
            value[1] = fProgrammeVillage.getCityid();
        }
        if (fProgrammeVillage.getTownid() != null) {
            value[2] = fProgrammeVillage.getTownid();
        }

        return R.ok().put("fProgrammeVillage", fProgrammeVillage).put("value", value);
    }


}
