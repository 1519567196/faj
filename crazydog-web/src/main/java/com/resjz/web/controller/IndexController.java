package com.resjz.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.resjz.common.dao.zmadmin.dao.FNewsDao;
import com.resjz.common.dao.zmadmin.entity.*;
import com.resjz.common.service.zmadmin.*;
import com.resjz.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
//@RequestMapping("/")
public class IndexController {

    @Autowired
    private FMemberService fMemberService;

    @Autowired
    private FFitupCompanyService fFitupCompanyService;

    @Autowired
    private FPediaService fPediaService;
    @Autowired
    private FNewsDao fNewsDao;

    @Autowired
    private FLinkUsService fLinkUsService;
    @Autowired
    private FLinksService fLinksService;
    @Autowired
    private FAdvertService fAdvertService;
    @Autowired
    private FFitupCompanyCasesService fFitupCompanyCasesService;
//
//    @Autowired
//    private ServerConfig serverConfig;


    @RequestMapping("/login.html")
    public String login(Model model, HttpServletRequest req) {
        Object info = req.getAttribute("info");
        if (info != null) {
            model.addAttribute("info", info.toString());
        }


        return "login";
    }

    @RequestMapping("/recruitment.html")
    public String recruitment(HttpServletRequest req, HttpServletResponse response) {
        try {
//            req.setAttribute("model", model);
            req.getRequestDispatcher("/fjob/list").forward(req, response);
//            response.sendRedirect(serverConfig.getUrl()+"/sys/ffitupcompanycases/list");
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }


        return "recruitment";
    }


    @RequestMapping("/register.html")
    public String register() {

        return "register";
    }


    @RequestMapping("/commonHeader.html")
    public String common(Model model, HttpServletRequest req, HttpServletResponse response) {

        /**
         * 获取【联系我们】信息
         */
        List<FLinkUsEntity> fLinkUsEntities = fLinkUsService.selectList(new EntityWrapper<FLinkUsEntity>());
        model.addAttribute("LinkUs", fLinkUsEntities.get(0));


        /**
         * 获取当前登录用户信息
         */
        FMemberEntity login = new FMemberEntity();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null){


            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
//            System.out.println(currentUserName);
                login.setMobile(currentUserName);
                model.addAttribute("bean", login);
            }else {
                model.addAttribute("bean", null);
            }

        }else {
            model.addAttribute("bean", null);
        }



        return "commonHeader";
    }

    @RequestMapping("/commonBottom.html")
    public String commonBottom(Model model, HttpServletRequest req, HttpServletResponse response) {

        /**
         * 获取【联系我们】信息
         */
        List<FLinkUsEntity> fLinkUsEntities = fLinkUsService.selectList(new EntityWrapper<FLinkUsEntity>());
        model.addAttribute("LinkUs", fLinkUsEntities.get(0));



        return "commonBottom";
    }

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest req, HttpServletResponse response) {


        /**
         * 装修公司排行
         */
        List<FFitupCompanyEntity> fFitupCompanyEntity = fFitupCompanyService.
                selectList(
                        new EntityWrapper<FFitupCompanyEntity>().
                                eq("status", 0).
                                orderBy("sort", false).last("limit 5"));
        model.addAttribute("fitupCompanys", fFitupCompanyEntity);

        /**
         * 公装
         *
         */

        List<FFitupCompanyCasesEntity> fFitupCompanyCasesEntities = fFitupCompanyCasesService.selectList(new EntityWrapper<FFitupCompanyCasesEntity>().eq("is_public", 1).eq("recommend", 1).last("limit 5"));
        model.addAttribute("fFitupCompanyCasesEntities", fFitupCompanyCasesEntities);

        //百科
        List<FPediaEntity> fPediaEntities = fPediaService.selectList(new EntityWrapper<FPediaEntity>().
                eq("is_delete", 0).
                eq("recommend", 1).last("limit 5"));
        FPediaEntity fPediaEntity = fPediaEntities.get(0);
        if (fPediaEntities.size()>1){
            List<FPediaEntity> fPediaEntities1 = fPediaEntities.subList(1, fPediaEntities.size());
            model.addAttribute("fPediaEntities",fPediaEntities1);
        }


        model.addAttribute("fPediaEntity",fPediaEntity);



        //广告1
        List advertList = fAdvertService.selectList(new EntityWrapper<FAdvertEntity>().eq("ad_place_id",1));
        model.addAttribute("advertList", advertList);

        //广告2
        List advertList2 = fAdvertService.selectList(new EntityWrapper<FAdvertEntity>().eq("ad_place_id",2));
        model.addAttribute("advertList2", advertList2);

        //广告3
        List advertList3 = fAdvertService.selectList(new EntityWrapper<FAdvertEntity>().eq("ad_place_id",1));
        model.addAttribute("advertList3", advertList3);

        //广告4
        List advertList4 = fAdvertService.selectList(new EntityWrapper<FAdvertEntity>().eq("ad_place_id",4));
        model.addAttribute("advertList4", advertList4);

        //广告5
        List advertList5 = fAdvertService.selectList(new EntityWrapper<FAdvertEntity>().eq("ad_place_id",1));
        model.addAttribute("advertList5", advertList5);

        //广告6
        List advertList6 = fAdvertService.selectList(new EntityWrapper<FAdvertEntity>().eq("ad_place_id",4));
        model.addAttribute("advertList6", advertList6);

        //友情链接
        List linkList = fLinksService.selectList(new EntityWrapper<FLinksEntity>().orderBy("sort",false));
        model.addAttribute("linkList", linkList);

        return "index";

    }


    /*****
     * 新闻列表
     *  cid 为分类id，全部则为0
     *  pageNum为页数，默认1
     *
     */
    @RequestMapping("/nlist/{CID}/{pageNum}")
    public String nlist(@PathVariable(value = "CID", required = false) String CID,
                        @PathVariable(value = "pageNum", required = false) Integer pageNum,
                        HttpServletRequest request,
                        Model model) {


        int memnerid = 1;
        FMemberEntity bean = fMemberService.selectById(memnerid);
        model.addAttribute("bean", bean);


        EntityWrapper<FNewsEntity> entityWrapper = new EntityWrapper();
        if (pageNum == null) {
            pageNum = 1;
        }
        if ((CID == null && CID.equals("")) || CID.equals("-1")) {
            CID = "1";
        }

        if (StringUtils.notNullAndEmpty(CID) && Integer.parseInt(CID) > 0) {
            entityWrapper.and("typeid=" + CID);
        }

        Page page = new Page(pageNum, 8);
        entityWrapper.orderBy("itemid desc");
//        entityWrapper.and("is_recommend= 0");

        List<FNewsEntity> newsList = fNewsDao.selectPage(page, entityWrapper);
        List<String> pages = page(page.getCurrent(), page.getPages(), CID);
        model.addAttribute("newsList", newsList);
        model.addAttribute("CID", Integer.parseInt(CID));
        String href = " ";
        for (String str : pages) {
            href = href + str + " ";
        }
        model.addAttribute("pages", href);



        //友情链接
        List linkList = fLinksService.selectList(new EntityWrapper<FLinksEntity>().orderBy("sort",false));
        model.addAttribute("linkList", linkList);


        return "test";
    }


    private List<String> page(int currPage, int totalPage, String cid) {
        String head = "<a href='/nlist-";
        String end = "</a>";
        if (currPage == 1 && totalPage == 1) {
            return Arrays.asList("<a href='/nlist-" + cid + "-1' class='current'>1</a>");
        }
        List<String> hrefs = new ArrayList<>(13);

        for (int i = 4; i > 0; --i) {
            if (currPage - i > 0) {
                hrefs.add(head + cid + "-" + (currPage - i) + "" + "'>" + (currPage - i) + end);
            }
        }
        hrefs.add(head + cid + "-" + currPage + "' class='current'>" + currPage + "</a>");
        for (int i = 1; i < 5; ++i) {
            if (currPage + i < totalPage) {
                hrefs.add(head + cid + "-" + (currPage + i) + "'>" + (currPage + i) + end);
            }
        }
        if (currPage != 1) {
            hrefs.add(0, head + cid + "-" + 1 + "'>首页" + end);
            hrefs.add(1, head + cid + "-" + (currPage - 1) + "'>上页" + end);
        }
        if (currPage != totalPage) {
            hrefs.add(head + cid + "-" + (currPage + 1) + "'>下页" + end);
            hrefs.add(head + cid + "-" + totalPage + "'>尾页" + end);
        }
        return hrefs;
    }


    private List<String> tagpage(int currPage, int totalPage, String searchkey) {
        String head = "<a href='/tagnews/";
        String end = "</a>";
        if (currPage == 1 && totalPage == 1) {
            return Arrays.asList("<a href='/tagnews/" + searchkey + "/1' class='current'>1</a>");
        }
        List<String> hrefs = new ArrayList<>(13);

        for (int i = 4; i > 0; --i) {
            if (currPage - i > 0) {
                hrefs.add(head + searchkey + "/" + (currPage - i) + "" + "'>" + (currPage - i) + end);
            }
        }
        hrefs.add(head + searchkey + "/" + currPage + "' class='current'>" + currPage + "</a>");
        for (int i = 1; i < 5; ++i) {
            if (currPage + i < totalPage) {
                hrefs.add(head + searchkey + "/" + (currPage + i) + "'>" + (currPage + i) + end);
            }
        }
        if (currPage != 1) {
            hrefs.add(0, head + searchkey + "/" + 1 + "'>首页" + end);
            hrefs.add(1, head + searchkey + "/" + (currPage - 1) + "'>上页" + end);
        }
        if (currPage != totalPage) {
            hrefs.add(head + searchkey + "/" + (currPage + 1) + "'>下页" + end);
            hrefs.add(head + searchkey + "/" + totalPage + "'>尾页" + end);
        }
        return hrefs;
    }

}
