package com.resjz.web.controller;

import com.resjz.common.dao.zmadmin.entity.FProgrammeHouseEntity;
import com.resjz.common.service.zmadmin.FProgrammeHouseService;
import com.resjz.common.service.zmadmin.FProgrammeImagesService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * 我加方案-户型表
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@Controller
@RequestMapping("fprogrammehouse")
public class FProgrammeHouseController {
    @Autowired
    private FProgrammeHouseService fProgrammeHouseService;



    /**
     * 列表
     */
    @RequestMapping("/wjfa2.html")
    public String list(Model model, HttpServletRequest req) {
        String fvId=null;
        String type=null;
        String parm=null;
        Integer fHousesTypeClass=0;
        try {  //封装参数
            String parm1 = req.getParameter("parm");
            if (parm1==null||parm1.equals("null")){
                parm=null;
            }else {
                parm=parm1;
            }

            if (req.getParameter("fvId")==null||req.getParameter("fvId").equals("null")){
                fvId=null;
            }else {
                fvId=req.getParameter("fvId");
            }

            if (req.getParameter("type")==null||req.getParameter("type").equals("null")){
                fHousesTypeClass=0;
                type=null;
            }else {
                type=req.getParameter("type");
                try {
                    fHousesTypeClass=Integer.parseInt(req.getParameter("type"));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, Object> params = new HashMap<>();
        params.put("page", req.getParameter("page")==null?1+"":req.getParameter("page"));
        params.put("limit",9+"" );
        params.put("parm",parm );
        params.put("programmeVillageId",fvId );
        params.put("houseType",type);
        PageUtils page = fProgrammeHouseService.listPage(params);
        model.addAttribute("fProgrammeHouses", page);
        model.addAttribute("fHousesTypeClass", fHousesTypeClass);

        return "wjfa2";
    }

    @RequestMapping("/onlyList")
    public R onlyList() {
        List<FProgrammeHouseEntity> fProgrammeHouseEntities = fProgrammeHouseService.selectList(null);

        return R.ok().put("list", fProgrammeHouseEntities);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fprogrammehouse:info")
    public R info(@PathVariable("itemid") Integer itemid) {
        FProgrammeHouseEntity fProgrammeHouse = fProgrammeHouseService.selectById(itemid);

        return R.ok().put("fProgrammeHouse", fProgrammeHouse);
    }


}
