package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FPediaTypeDao;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrValueEntity;
import com.resjz.common.dao.zmadmin.entity.FPediaTypeEntity;
import com.resjz.common.service.zmadmin.FCommonAttrValueService;
import com.resjz.common.service.zmadmin.FFitupCompanyCasesService;
import com.resjz.common.service.zmadmin.FPediaService;
import com.resjz.common.service.zmadmin.FPediaTypeService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("fPediaTypeService")
public class FPediaTypeServiceImpl extends ServiceImpl<FPediaTypeDao, FPediaTypeEntity> implements FPediaTypeService {
    @Autowired
    private FCommonAttrValueService fCommonAttrValueService;

    @Autowired
    private FFitupCompanyCasesService fFitupCompanyCasesService;
    @Autowired
    private FPediaService fPediaService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FPediaTypeEntity> page = this.selectPage(
                new Query<FPediaTypeEntity>(params).getPage(),
                new EntityWrapper<FPediaTypeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<FPediaTypeEntity> getMainList(Model model) {
        List<FPediaTypeEntity> list1 = this.selectList(new EntityWrapper<FPediaTypeEntity>().eq("zxbk_type", 1).eq("parentid",0));
        model.addAttribute("list1", list1);
        List<FPediaTypeEntity> list2 = this.selectList(new EntityWrapper<FPediaTypeEntity>().eq("zxbk_type", 2).eq("parentid",0));
        model.addAttribute("list2", list2);
        List<FPediaTypeEntity> list3 = this.selectList(new EntityWrapper<FPediaTypeEntity>().eq("zxbk_type", 3).eq("parentid",0));
        model.addAttribute("list3", list3);
        List<FPediaTypeEntity> list4 = this.selectList(new EntityWrapper<FPediaTypeEntity>().eq("zxbk_type", 4).eq("parentid",0));
        model.addAttribute("list4", list4);
        List<FCommonAttrValueEntity> list5 = fCommonAttrValueService.selectList(new EntityWrapper<FCommonAttrValueEntity>().eq("attr_id", 7));
        model.addAttribute("list5", list5);


        try {  //百科文章
            fPediaService.getIndexContents(model,list2);
            fPediaService.getIndexContents3(model,list3);
            fPediaService.getIndexContents3(model,list4);
            fFitupCompanyCasesService.getIndexCase3(model,list5);

        } catch (Exception e) {
            e.printStackTrace();
        }



        return null;
    }

}
