package com.resjz.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FAdvertEntity;
import com.resjz.common.dao.zmadmin.entity.FBbsEntity;
import com.resjz.common.dao.zmadmin.entity.FMemberEntity;
import com.resjz.common.dao.zmadmin.entity.FRepleyBbsEntity;
import com.resjz.common.service.zmadmin.FAdvertService;
import com.resjz.common.service.zmadmin.FBbsService;
import com.resjz.common.service.zmadmin.FMemberService;
import com.resjz.common.service.zmadmin.FRepleyBbsService;
import com.resjz.common.utils.IpUtil;
import com.resjz.web.bean.Json;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class BbsController {

    @Autowired
    private FBbsService fBbsService;
    @Autowired
    private FMemberService fMemberService;
    @Autowired
    private FRepleyBbsService fRepleyBbsService;
    @Autowired
    private FAdvertService fAdvertService;

    @RequestMapping("/ltzx")
    private String itlistzx( Integer type, HttpServletRequest req, Model model, HttpServletResponse response){

        //广告
        List<FAdvertEntity> advertList = fAdvertService.selectList(new EntityWrapper<FAdvertEntity>().eq(true,"ad_place_id",19));
        model.addAttribute("advertList", advertList);

        //精华帖
        List<FBbsEntity> goodList = fBbsService.selectList(new EntityWrapper<FBbsEntity>().eq(true,"good",1));
        model.addAttribute("goodList",goodList);

        model.addAttribute("type", type);

        HashMap<String, Object> map = new HashMap<>();
        map.put("page", req.getParameter("page")==null?1+"":req.getParameter("page"));
        map.put("limit", 9+"");
        map.put("type",type);
//        PageUtils pageUtils = fBbsService.listPage(map);
        PageUtils pageUtils = fBbsService.listPage(map);
        model.addAttribute("fbbspage",pageUtils);


        return "lt";

    }



    @RequestMapping("/bbsSearch")
    private String bbsSearch( String title, HttpServletRequest req, Model model, HttpServletResponse response){
//        String type1 = req.getParameter("type");
//        System.out.println(type1);
        //精华帖
        List<FBbsEntity> goodList = fBbsService.selectList(new EntityWrapper<FBbsEntity>().eq(true,"good",1));
        model.addAttribute("goodList",goodList);

        //广告
        List<FAdvertEntity> advertList = fAdvertService.selectList(new EntityWrapper<FAdvertEntity>().eq(true,"ad_place_id",19));
        model.addAttribute("advertList", advertList);


        HashMap<String, Object> map = new HashMap<>();
        map.put("page", req.getParameter("page")==null?1+"":req.getParameter("page"));
        map.put("limit", 9+"");
        map.put("title",title);
        PageUtils pageUtils = fBbsService.listPage2(map);
        //遍历获取 回帖
       List bbsList = pageUtils.getList();
       if(bbsList.size()>0){
            for (int i = 0; i <bbsList.size() ; i++) {
                FBbsEntity bbs = (FBbsEntity)bbsList.get(i);
                Integer memberid = bbs.getMemberid();
                Integer itemid = bbs.getItemid();
                FMemberEntity fMemberEntity = fMemberService.selectById(memberid);
                String memberName = fMemberEntity.getMemberName();
                bbs.setMemberName(memberName);

                List<FRepleyBbsEntity> fRepleyList = fRepleyBbsService.selectList(new EntityWrapper().eq("bbsid",itemid));
                int number = 0;

                if(fRepleyList.size()>0){
                    for (int j = 0; j < fRepleyList.size(); j++) {
                        Integer bbsid = fRepleyList.get(j).getBbsid();
                        if (bbsid != null && bbsid == itemid) {
                            number++;
                        }
                    }
                }

                bbs.setNumber(number);

            }
        }
        model.addAttribute("fbbspage",pageUtils);

        return "ltcx";

    }

    @RequestMapping("/news/{itemid}")
    private String ltxq(@PathVariable(value = "itemid", required = false) String itemid, HttpServletRequest req, Model model, HttpServletResponse response){

        FBbsEntity bean = fBbsService.selectById(itemid);
        if (bean!=null){
            FMemberEntity fMemberEntity = fMemberService.selectById(bean.getMemberid());
            bean.setMemberName(fMemberEntity.getMemberName());
            bean.setTouxiang(fMemberEntity.getHeadImg());
            bean.setViews(bean.getViews()+1);
            fBbsService.updateById(bean);
        }


        model.addAttribute("bean", bean);

        HashMap<String, Object> map = new HashMap<>();
        //回帖列表
        Map params = new HashMap();
        params.put("bbsid",itemid);
        List replyList =  fRepleyBbsService.selectByMap(params);
        for (int i = 0; i < replyList.size(); i++) {
            FRepleyBbsEntity reply = (FRepleyBbsEntity)replyList.get(i);
            FMemberEntity fMemberEntity1 = fMemberService.selectById(reply.getMemberid());
            if (fMemberEntity1!=null){
                String memberName = fMemberEntity1.getMemberName();
                ((FRepleyBbsEntity) replyList.get(i)).setRepleyname(memberName);
                map.put("repleyname",memberName);
            }


        }
        model.addAttribute("replyList", replyList);

        //分页
        //HashMap<String, Object> map = new HashMap<>();
        String pageStr = req.getParameter("page");
        String page = pageStr==null?"1":pageStr;
        if("0".equals(page)){
            page="1";
        }
        map.put("page", page);
        map.put("limit", 1+"");
        map.put("bbsid",itemid);
        PageUtils pageUtils = fRepleyBbsService.listPage(map);


        model.addAttribute("replypages",pageUtils);


        return "ltxq";

    }





    @RequestMapping(path = "/bbs/replaySave", method= RequestMethod.POST)
    @ResponseBody
    private Json replaySave(@RequestBody FRepleyBbsEntity model,HttpServletRequest req){



        Json json = new Json();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null) {

            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                model.setRepleyname(currentUserName);
            }
        }

        model.setAddtime(new Date());
        model.setFloor(0);
        String ipAddr = IpUtil.getIpAddr(req);
        model.setIp(ipAddr);



        fRepleyBbsService.insert(model);

        json.put("code",0);
        json.put("msg","保存成功");


        return json;

    }



}
