package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FNewsEntity;
import com.resjz.common.dao.zmadmin.entity.FNewsTypeEntity;
import com.resjz.common.service.zmadmin.FNewsService;
import com.resjz.common.service.zmadmin.FNewsTypeService;
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
 * 房产资讯
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fnews")
public class FNewsController extends AbstractController {
    @Autowired
    private FNewsService fNewsService;
    @Autowired
    private FNewsTypeService fNewsTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fnews:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fNewsService.queryPage(params);
        List listNews = page.getList();
        List list = new ArrayList();
        FNewsEntity fn = null;
        if(listNews.size()>0){
            for (int i = 0; i < listNews.size(); i++) {
                fn = new FNewsEntity();
                FNewsEntity fnewsbean = (FNewsEntity)listNews.get(i);
                if(fnewsbean!=null){
                    fn.setItemid(fnewsbean.getItemid());
                    fn.setTitle(fnewsbean.getTitle());
                    fn.setTypeid(fnewsbean.getTypeid());
                    fn.setImage(fnewsbean.getImage());
                    fn.setAddtime(fnewsbean.getAddtime());
                    fn.setIsRecommend(fnewsbean.getIsRecommend());
                    fn.setContent(fnewsbean.getContent());
                    fn.setFrom(fnewsbean.getFrom());
                    fn.setViews(fnewsbean.getViews());
                    if(fnewsbean.getTypeid()>0){
                        FNewsTypeEntity type = fNewsTypeService.selectById(fnewsbean.getTypeid());
                        if(type!=null){
                            fn.setTypeName(type.getTypeName());
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
    @RequiresPermissions("sys:fnews:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FNewsEntity fNews = fNewsService.selectById(itemid);


        return R.ok().put("fNews", fNews);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fnews:save")
    public R save(@RequestBody FNewsEntity fNews){
        Assert.isBlank(fNews.getTitle(),"标题不能为空");
        Assert.isNull(fNews.getTypeid(),"分类不能为空");
        fNews.setImage(fNews.getImage());

        String ec="";
        try {
            ec = URLDecoder.decode(fNews.getContent(),"utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        fNews.setContent(ec);
        fNews.setViews(1);
        fNews.setAddtime(new Date());
        fNews.setAddUserid(getUserId());


        fNewsService.insert(fNews);

        return R.ok();
    }

    /**
     * 分类列表
     */
    @RequestMapping("/typeList")
//    @RequiresPermissions("sys:tnews:typeList")
    public R typeList(@RequestParam Map<String, Object> params){

        PageUtils page = fNewsTypeService.queryPage(params);
        return R.ok().put("list", page.getList());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fnews:update")
    public R update(@RequestBody FNewsEntity fNews){
        Assert.isBlank(fNews.getTitle(),"标题不能为空");
        Assert.isNull(fNews.getTypeid(),"分类不能为空");
        ValidatorUtils.validateEntity(fNews);

        fNews.setImage(fNews.getImage());

        String ec="";
        try {
            ec = URLDecoder.decode(fNews.getContent(),"utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        fNews.setContent(ec);
        fNews.setAddUserid(getUserId());
        fNews.setUpdatetime(new Date());

        fNewsService.updateAllColumnById(fNews);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fnews:delete")
    public R delete(@RequestBody Integer[] itemids){
        fNewsService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

    /**
     * 推荐
     */
    @RequestMapping("/recommend/{itemid}")
    //   @RequiresPermissions("sys:tcase:recommend")
    public R recommend(@PathVariable("itemid") Integer itemid){

        FNewsEntity fNews = fNewsService.selectById(itemid);
        if(fNews!=null){

            if(fNews.getIsRecommend()!=null&&fNews.getIsRecommend()==0){
                fNews.setIsRecommend(1);
            }else{
                fNews.setIsRecommend(0);
            }
            fNewsService.updateById(fNews);
        }

        return R.ok().put("msg","操作成功");
    }

}
