package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FArticleTypeEntity;
import com.resjz.common.dao.zmadmin.entity.FMaterialBrandEntity;
import com.resjz.common.dao.zmadmin.entity.FMaterialTypeEntity;
import com.resjz.common.dao.zmadmin.entity.FMatericalArticleEntity;
import com.resjz.common.service.zmadmin.*;
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
 * 材料资讯
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fmatericalarticle")
public class FMatericalArticleController extends AbstractController {
    @Autowired
    private FMatericalArticleService fMatericalArticleService;
    @Autowired
    private FArticleTypeService fArticleTypeService;
    @Autowired
    private FMaterialBrandService fMaterialBrandService;
    @Autowired
    private FMaterialService fMaterialService;
    @Autowired
    private FMaterialTypeService fMaterialTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fmatericalarticle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fMatericalArticleService.queryPage(params);

        List listArticles =
                page.getList();
        List list = new ArrayList();
        FMatericalArticleEntity fn = null;
        if(listArticles.size()>0){
            for (int i = 0; i < listArticles.size(); i++) {
                fn = new FMatericalArticleEntity();
                FMatericalArticleEntity articlesbean = (FMatericalArticleEntity)listArticles.get(i);
                if(articlesbean!=null){
                    fn.setItemid(articlesbean.getItemid());
                    fn.setTitle(articlesbean.getTitle());
                    fn.setMaterialTypeId(articlesbean.getMaterialTypeId());
                    fn.setSubTitle(articlesbean.getSubTitle());
                    fn.setContent(articlesbean.getContent());
                    fn.setViews(articlesbean.getViews());
                    fn.setMaterialBrandId(articlesbean.getMaterialTypeId());
                    fn.setTags(articlesbean.getTags());
                    fn.setAddtime(articlesbean.getAddtime());
                    fn.setAddUserid(articlesbean.getAddUserid());
                    fn.setArticleTypeId(articlesbean.getArticleTypeId());
                    fn.setUpdatetime(articlesbean.getUpdatetime());
                    fn.setAreaid(articlesbean.getAreaid());


                    if(articlesbean.getArticleTypeId()>0){
                        FArticleTypeEntity type = fArticleTypeService.selectById(articlesbean.getArticleTypeId());
                        if(type!=null){
                            fn.setTypeName(type.getTypeName());
                        }
                        FMaterialBrandEntity type2 = fMaterialBrandService.selectById(articlesbean.getMaterialBrandId());
                        if(type2!=null){
                            fn.setMaterialTypeName(type2.getBrandName());
                        }

                       /* List<FMaterialBrandEntity> type2 = fMaterialBrandService.selectByBrandId(articlesbean.getMaterialBrandId());
                        if(type2!=null){
                            for (FMaterialBrandEntity m : type2) {
                                fn.setMaterialTypeName(m.getBrandName());
                            }
                        }*/
                        //fMaterialService.selectById(articlesbean.getMaterialTypeId());
                        FMaterialTypeEntity type3 = fMaterialTypeService.selectById(articlesbean.getMaterialTypeId());
                        if(type3!=null){
                            fn.setMaterialTypeName2(type3.getType());
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
    @RequiresPermissions("sys:fmatericalarticle:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FMatericalArticleEntity fMatericalArticle = fMatericalArticleService.selectById(itemid);

        return R.ok().put("fMatericalArticle", fMatericalArticle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fmatericalarticle:save")
    public R save(@RequestBody FMatericalArticleEntity fMatericalArticle){
        Assert.isBlank(fMatericalArticle.getTitle(),"标题不能为空");
        Assert.isNull(fMatericalArticle.getSubTitle(),"摘要不能为空");

        String ec="";
        try {
            ec = URLDecoder.decode(fMatericalArticle.getContent(),"utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        fMatericalArticle.setContent(ec);
        fMatericalArticle.setViews(1);
        //fMatericalArticle.setMaterialBrandId(fMatericalArticle.getMaterialTypeId());
        //fMatericalArticle.setMaterialTypeId(fMatericalArticle.getArticleTypeId());
        fMatericalArticle.setAddtime(new Date());
        fMatericalArticle.setAddUserid(getUserId());




        fMatericalArticleService.insert(fMatericalArticle);

        return R.ok();
    }


    /**
     * 材料文章分类列表
     */
    @RequestMapping("/typeList")
//    @RequiresPermissions("sys:tnews:typeList")
    public R typeList(@RequestParam Map<String, Object> params){

        PageUtils page = fArticleTypeService.queryPage(params);
        return R.ok().put("list", page.getList());
    }
    /**
     * 材料品牌分类列表
     */
    @RequestMapping("/typeList2")
//    @RequiresPermissions("sys:tnews:typeList")
    public R typeList2(@RequestParam Map<String, Object> params){

        PageUtils page = fMaterialBrandService.queryPage(params);
        return R.ok().put("list", page.getList());
    }
    /**
     * 所属材料类分列表
     */
    @RequestMapping("/typeList3")
//    @RequiresPermissions("sys:tnews:typeList")
    public R typeList3(@RequestParam Map<String, Object> params){

        PageUtils page = fMaterialTypeService.queryPage(params);
        return R.ok().put("list", page.getList());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fmatericalarticle:update")
    public R update(@RequestBody FMatericalArticleEntity fMatericalArticle){


        Assert.isBlank(fMatericalArticle.getTitle(),"标题不能为空");
        Assert.isNull(fMatericalArticle.getSubTitle(),"摘要不能为空");
        Assert.isNull(fMatericalArticle.getContent(),"内容不能为空");

        ValidatorUtils.validateEntity(fMatericalArticle);

        String ec="";
        try {
            ec = URLDecoder.decode(fMatericalArticle.getContent(),"utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        fMatericalArticle.setContent(ec);
        fMatericalArticle.setUpdatetime(new Date());
        fMatericalArticle.setAddUserid(getUserId());

        fMatericalArticleService.updateAllColumnById(fMatericalArticle);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fmatericalarticle:delete")
    public R delete(@RequestBody Integer[] itemids){
        fMatericalArticleService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }



}
