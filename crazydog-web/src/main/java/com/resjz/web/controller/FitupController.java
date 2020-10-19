package com.resjz.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FAdvertEntity;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrValueEntity;
import com.resjz.common.dao.zmadmin.entity.FFitupCompanyCasesEntity;
import com.resjz.common.dao.zmadmin.entity.FFitupCompanyEntity;
import com.resjz.common.service.zmadmin.*;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
public class FitupController {
    @Autowired
    private FFitupCompanyCasesService fFitupCompanyCasesService;
    @Autowired
    private FCommonAttrValueService fCommonAttrValueService;
    @Autowired
    private FFitupCompanyService fFitupCompanyService;
    @Autowired
    private FAdvertService fAdvertService;

    @RequestMapping("/jzal")
    private String jzlist(HttpServletRequest req, Model model, HttpServletResponse response){

        String mj = req.getParameter("mj");
        String hx =req.getParameter("hx");
        String fg =req.getParameter("fg");
        String ys =req.getParameter("ys");

        //遍历面积
        List<FCommonAttrValueEntity> AttrValueList = fCommonAttrValueService.selectList(new EntityWrapper<FCommonAttrValueEntity>().eq(true,"attr_id",4));
        model.addAttribute("squareList",AttrValueList);
        //遍历户型
        List<FCommonAttrValueEntity> AttrValueList1 = fCommonAttrValueService.selectList(new EntityWrapper<FCommonAttrValueEntity>().eq(true,"attr_id",8));
        model.addAttribute("housetypeList",AttrValueList1);
        //遍历风格
        List<FCommonAttrValueEntity> AttrValueList2 = fCommonAttrValueService.selectList(new EntityWrapper<FCommonAttrValueEntity>().eq(true,"attr_id",5));
        model.addAttribute("styleList",AttrValueList2);
        //遍历预算
        List<FCommonAttrValueEntity> AttrValueList3 = fCommonAttrValueService.selectList(new EntityWrapper<FCommonAttrValueEntity>().eq(true,"attr_id",6));
        model.addAttribute("moneyList",AttrValueList3);


        //广告
        List<FAdvertEntity> advertList = fAdvertService.selectList(new EntityWrapper<FAdvertEntity>().eq(true,"ad_place_id",9));
        model.addAttribute("advertList", advertList);


        HashMap<String, Object> map = new HashMap<>();
        map.put("page", req.getParameter("page")==null?1+"":req.getParameter("page"));
        map.put("limit", 6+"");
        map.put("squareid",mj);
        map.put("house_typeid",hx);
        map.put("styleid",fg);
        map.put("moneyid",ys);


        PageUtils pageUtils = fFitupCompanyCasesService.listPage(map);
        model.addAttribute("fFitupCompanyCasespage",pageUtils);

        model.addAttribute("mj", mj);
        model.addAttribute("hx", hx);
        model.addAttribute("fg", fg);
        model.addAttribute("ys", ys);

        return "jzal";
    }

    @RequestMapping("/cases/{case_id}")
    private String ltxq(@PathVariable(value = "case_id", required = false) Integer case_id, HttpServletRequest req, Model model, HttpServletResponse response){
        FFitupCompanyCasesEntity bean = fFitupCompanyCasesService.selectById(case_id);

        //面积
        FCommonAttrValueEntity fCommonAttrValueEntity = fCommonAttrValueService.selectById(bean.getSquareid());
        String squarename = fCommonAttrValueEntity.getTitle();
        bean.setSquareNum(squarename);

        //户型
        FCommonAttrValueEntity fCommonAttrValueEntity1 = fCommonAttrValueService.selectById(bean.getHouseTypeid());
        String housetype = fCommonAttrValueEntity1.getTitle();
        bean.setHouseType(housetype);

        //风格
        FCommonAttrValueEntity fCommonAttrValueEntity2 = fCommonAttrValueService.selectById(bean.getStyleid());
        String style = fCommonAttrValueEntity2.getTitle();
        bean.setStyle(style);

        //预算
        FCommonAttrValueEntity fCommonAttrValueEntity3 = fCommonAttrValueService.selectById(bean.getMoneyid());
        String money = fCommonAttrValueEntity3.getTitle();
        bean.setMoney(money);

        //浏览量+1
        bean.setViews(bean.getViews()+1);
        fFitupCompanyCasesService.updateById(bean);
        model.addAttribute("bean",bean);

        List<FFitupCompanyCasesEntity> casesList = fFitupCompanyCasesService.selectList(new EntityWrapper<FFitupCompanyCasesEntity>().eq("is_public", 2).orderBy("views",false).last("limit 4"));
        if(casesList.size()>0){
            for (int i = 0; i < casesList.size(); i++) {

                Integer squareid = casesList.get(i).getSquareid();
                FCommonAttrValueEntity AttrValueEntity = fCommonAttrValueService.selectById(squareid);
                if(AttrValueEntity!=null){
                    String squareNum = AttrValueEntity.getTitle();//面积
                    casesList.get(i).setSquareNum(squareNum);
                }
                Integer houseTypeid = casesList.get(i).getHouseTypeid();
                FCommonAttrValueEntity AttrValueEntity1 = fCommonAttrValueService.selectById(houseTypeid);
                if(AttrValueEntity1!=null){
                    String houseType = AttrValueEntity1.getTitle();//户型
                    casesList.get(i).setHouseType(houseType);
                }

                Integer styleid = casesList.get(i).getStyleid();
                FCommonAttrValueEntity AttrValueEntity2 = fCommonAttrValueService.selectById(styleid);
                if(AttrValueEntity2!=null){
                    String style1 = AttrValueEntity2.getTitle();//风格
                    casesList.get(i).setStyle(style1);
                }

                Integer moneyid = casesList.get(i).getMoneyid();
                FCommonAttrValueEntity AttrValueEntity3 = fCommonAttrValueService.selectById(moneyid);
                if(AttrValueEntity3!=null){
                    String money1 = AttrValueEntity3.getTitle();//预算
                    casesList.get(i).setMoney(money1);
                }

                Integer companyId = casesList.get(i).getCompanyId();
                FFitupCompanyEntity fFitupCompanyEntity = fFitupCompanyService.selectById(companyId);
                if(fFitupCompanyEntity!=null){
                    String companyName = fFitupCompanyEntity.getCompanyName();//企业名称
                    casesList.get(i).setCompanyName(companyName);

                }

            }
        }
        model.addAttribute("CompanyCasesEntities", casesList);

        return "alxq";
    }

    @RequestMapping("/caseSearch")
    private String bbsSearch( String title, HttpServletRequest req, Model model, HttpServletResponse response){

        //遍历面积
        List<FCommonAttrValueEntity> AttrValueList = fCommonAttrValueService.selectList(new EntityWrapper<FCommonAttrValueEntity>().eq(true,"attr_id",4));
        model.addAttribute("squareList",AttrValueList);
        //遍历户型
        List<FCommonAttrValueEntity> AttrValueList1 = fCommonAttrValueService.selectList(new EntityWrapper<FCommonAttrValueEntity>().eq(true,"attr_id",8));
        model.addAttribute("housetypeList",AttrValueList1);
        //遍历风格
        List<FCommonAttrValueEntity> AttrValueList2 = fCommonAttrValueService.selectList(new EntityWrapper<FCommonAttrValueEntity>().eq(true,"attr_id",5));
        model.addAttribute("styleList",AttrValueList2);
        //遍历预算
        List<FCommonAttrValueEntity> AttrValueList3 = fCommonAttrValueService.selectList(new EntityWrapper<FCommonAttrValueEntity>().eq(true,"attr_id",6));
        model.addAttribute("moneyList",AttrValueList3);

        //广告
        List<FAdvertEntity> advertList = fAdvertService.selectList(new EntityWrapper<FAdvertEntity>().eq(true,"ad_place_id",9));
        model.addAttribute("advertList", advertList);

        HashMap<String, Object> map = new HashMap<>();
        map.put("page", req.getParameter("page")==null?1+"":req.getParameter("page"));
        map.put("limit", 6+"");
        map.put("title",title);



        PageUtils pageUtils = fFitupCompanyCasesService.listPage2(map);
        model.addAttribute("fFitupCompanyCasespage",pageUtils);




        return "jzalcx";
    }




    }
