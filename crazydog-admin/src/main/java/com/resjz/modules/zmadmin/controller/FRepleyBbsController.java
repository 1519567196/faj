package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FBbsEntity;
import com.resjz.common.dao.zmadmin.entity.FMemberEntity;
import com.resjz.common.dao.zmadmin.entity.FRepleyBbsEntity;
import com.resjz.common.service.zmadmin.FBbsService;
import com.resjz.common.service.zmadmin.FMemberService;
import com.resjz.common.service.zmadmin.FRepleyBbsService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;


/**
 * 回帖
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/frepleybbs")
public class FRepleyBbsController {
    @Autowired
    private FRepleyBbsService fRepleyBbsService;
    @Autowired
    private FBbsService fBbsService;
    @Autowired
    private FMemberService fMemberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:frepleybbs:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fRepleyBbsService.queryPage(params);
        List listrepleybbs = page.getList();
        List list = new ArrayList();
        FRepleyBbsEntity fn = null;
        if(listrepleybbs.size()>0){
            for (int i = 0; i < listrepleybbs.size(); i++) {
                fn = new FRepleyBbsEntity();
                FRepleyBbsEntity frepleybean = (FRepleyBbsEntity)listrepleybbs.get(i);
                if(frepleybean!=null){
                    fn.setItemid(frepleybean.getItemid());
                    fn.setBbsid(frepleybean.getBbsid());
                    fn.setRepleyContent(frepleybean.getRepleyContent());
                    fn.setMemberid(frepleybean.getMemberid());
                    fn.setIp(frepleybean.getIp());
                    fn.setToMemberid(frepleybean.getToMemberid());
                    fn.setAreaid(frepleybean.getAreaid());
                    fn.setFloor(frepleybean.getFloor());
                    fn.setAddtime(frepleybean.getAddtime());

                    if(frepleybean.getItemid()>0){
                        FBbsEntity f = fBbsService.selectById(frepleybean.getBbsid());
                        if(f!=null){
                            fn.setTitle(f.getTitle());
                            FMemberEntity n = fMemberService.selectById(f.getMemberid());
                            fn.setTomembername(n.getMemberName());
                           // System.out.println("========"+f.getMemberName());
                        }
                        FMemberEntity m = fMemberService.selectById(frepleybean.getMemberid());
                        if(m!=null){
                            fn.setRepleyname(m.getMemberName());
                        }
                    }


                }
                list.add(fn);
            }
            page.setList(list);
        }


        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:frepleybbs:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FRepleyBbsEntity fRepleyBbs = fRepleyBbsService.selectById(itemid);

        return R.ok().put("fRepleyBbs", fRepleyBbs);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:frepleybbs:save")
    public R save(@RequestBody FRepleyBbsEntity fRepleyBbs){
        fRepleyBbs.setBbsid(1);

        String ec="";
        try {
            ec = URLDecoder.decode(fRepleyBbs.getRepleyContent(),"utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        fRepleyBbs.setRepleyContent(ec);
        fRepleyBbs.setAddtime(new Date());


        fRepleyBbsService.insert(fRepleyBbs);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:frepleybbs:update")
    public R update(@RequestBody FRepleyBbsEntity fRepleyBbs){

        fRepleyBbs.setBbsid(1);
        String ec="";
        try {
            ec = URLDecoder.decode(fRepleyBbs.getRepleyContent(),"utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        fRepleyBbs.setRepleyContent(ec);
        fRepleyBbs.setAddtime(new Date());

        ValidatorUtils.validateEntity(fRepleyBbs);
        fRepleyBbsService.updateAllColumnById(fRepleyBbs);//全部更新


        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:frepleybbs:delete")
    public R delete(@RequestBody Integer[] itemids){
        fRepleyBbsService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
