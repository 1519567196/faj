package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FBbsEntity;
import com.resjz.common.dao.zmadmin.entity.FMemberEntity;
import com.resjz.common.dao.zmadmin.entity.FNewsEntity;
import com.resjz.common.service.zmadmin.FBbsService;
import com.resjz.common.service.zmadmin.FMemberService;
import com.resjz.common.utils.IPUtils;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.Assert;
import com.resjz.common.validator.ValidatorUtils;
import com.resjz.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;


/**
 * 贴子表
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fbbs")
public class FBbsController extends AbstractController {
    @Autowired
    private FBbsService fBbsService;
    @Autowired
    private FMemberService fMemberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fbbs:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fBbsService.queryPage(params);
        List listBbs = page.getList();
        List list = new ArrayList();
        FBbsEntity fn = null;
        if(listBbs.size()>0){
            for (int i = 0; i < listBbs.size(); i++) {
                fn = new FBbsEntity();
                FBbsEntity fbbsbean = (FBbsEntity)listBbs.get(i);
                if(fbbsbean!=null){
                    fn.setItemid(fbbsbean.getItemid());
                    fn.setTitle(fbbsbean.getTitle());
                    fn.setMemberid(fbbsbean.getMemberid());
                    fn.setContent(fbbsbean.getContent());
                    fn.setGood(fbbsbean.getGood());
                    fn.setTop(fbbsbean.getTop());
                    fn.setViews(fbbsbean.getViews());
                    fn.setIp(fbbsbean.getIp());
                    fn.setStatus(fbbsbean.getStatus());
                    fn.setAddtime(fbbsbean.getAddtime());

                    if(fbbsbean.getMemberid()>0){
                        FMemberEntity m = fMemberService.selectById(fbbsbean.getMemberid());
                        if(m != null){
                           fn.setMemberName(m.getMemberName());
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
    @RequiresPermissions("sys:fbbs:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FBbsEntity fBbs = fBbsService.selectById(itemid);

        return R.ok().put("fBbs", fBbs);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fbbs:save")
    public R save(@RequestBody FBbsEntity fBbs){

        Assert.isBlank(fBbs.getTitle(),"标题不能为空");
        fBbs.setMemberid(1);

        String ec="";
        try {
            ec = URLDecoder.decode(fBbs.getContent(),"utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        fBbs.setContent(ec);
        //设置IP地址
        //sysLog.setIp(IPUtils.getIpAddr(request));
        fBbs.setAddtime(new Date());
        fBbs.setViews(1);



        fBbsService.insert(fBbs);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fbbs:update")
    public R update(@RequestBody FBbsEntity fBbs){

        Assert.isBlank(fBbs.getTitle(),"标题不能为空");
        fBbs.setMemberid(1);
        String ec="";
        try {
            ec = URLDecoder.decode(fBbs.getContent(),"utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        fBbs.setContent(ec);
        fBbs.setUpdatetime(new Date());


        ValidatorUtils.validateEntity(fBbs);
        fBbsService.updateAllColumnById(fBbs);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fbbs:delete")
    public R delete(@RequestBody Integer[] itemids){
        fBbsService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

    /**
     * 置顶
     */
    @RequestMapping("/recommend/{itemid}")
    //   @RequiresPermissions("sys:tcase:recommend")
    public R recommend(@PathVariable("itemid") Integer itemid){

        FBbsEntity fBbs = fBbsService.selectById(itemid);
        if(fBbs!=null){

            if(fBbs.getTop()!=null&&fBbs.getTop()==0){
                fBbs.setTop(1);
            }else{
                fBbs.setTop(0);
            }
            fBbsService.updateById(fBbs);
        }

        return R.ok().put("msg","操作成功");
    }

    /**
     * 精华帖
     */
    @RequestMapping("/status/{itemid}")
    //   @RequiresPermissions("sys:tcase:recommend")
    public R status(@PathVariable("itemid") Integer itemid){

        FBbsEntity fBbs = fBbsService.selectById(itemid);
        if(fBbs!=null){

            if(fBbs.getStatus()!=null&&fBbs.getStatus()==0){
                fBbs.setStatus(1);
            }else{
                fBbs.setStatus(0);
            }
            fBbsService.updateById(fBbs);
        }

        return R.ok().put("msg","操作成功");
    }

    /**
     * 屏蔽
     */
    @RequestMapping("/good/{itemid}")
    //   @RequiresPermissions("sys:tcase:recommend")
    public R good(@PathVariable("itemid") Integer itemid){

        FBbsEntity fBbs = fBbsService.selectById(itemid);
        if(fBbs!=null){

            if(fBbs.getGood()!=null&&fBbs.getGood()==0){
                fBbs.setGood(1);
            }else{
                fBbs.setGood(0);
            }
            fBbsService.updateById(fBbs);
        }

        return R.ok().put("msg","操作成功");
    }

}
