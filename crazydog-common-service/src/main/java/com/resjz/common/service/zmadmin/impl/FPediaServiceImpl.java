package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FPediaDao;
import com.resjz.common.dao.zmadmin.entity.FPediaEntity;
import com.resjz.common.dao.zmadmin.entity.FPediaTypeEntity;
import com.resjz.common.service.zmadmin.FPediaService;
import com.resjz.common.service.zmadmin.FPediaTypeService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("fPediaService")
public class FPediaServiceImpl extends ServiceImpl<FPediaDao, FPediaEntity> implements FPediaService {
    @Autowired
    private FPediaTypeService fPediaTypeService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FPediaEntity> page = this.selectPage(
                new Query<FPediaEntity>(params).getPage(),
                new EntityWrapper<FPediaEntity>()
        );
        for (FPediaEntity f : page.getRecords()
        ) {
            FPediaTypeEntity fPediaTypeEntity = fPediaTypeService.selectById(f.getTypeid());
            f.setTypeName(fPediaTypeEntity == null ? "" : fPediaTypeEntity.getTitle() == null ? "" : fPediaTypeEntity.getTitle());


        }

        return new PageUtils(page);
    }

    @Override
    public List<FPediaEntity> getIndexContents(Model model, List<FPediaTypeEntity> list2) {
        List<FPediaEntity> fPediaEntities = this.selectList(new EntityWrapper<FPediaEntity>());
        List<FPediaEntity> result = new ArrayList<>();
        for (FPediaTypeEntity ft : list2) {

            for (FPediaEntity f : fPediaEntities) {
                String[] split = f.getSubCateIds().split(",");
                for (String s : split
                ) {
                    if (s != null && !s.equals("")) {
                        if (Integer.valueOf(s) == ft.getItemid()) {
                            f.setTypeid(ft.getItemid());  //只保留主分类，方便前端判断区分主分类文章
                            result.add(f);
                        }
                    }


                }


            }


        }


        model.addAttribute("contents" + list2.get(0).getZxbkType(), result);


        return null;
    }

    @Override
    public List<FPediaEntity> getIndexContents3(Model model, List<FPediaTypeEntity> list2) {


        List<FPediaEntity> fPediaEntities = this.selectList(new EntityWrapper<FPediaEntity>());

        List<Map<String, List<FPediaEntity>>> results = new ArrayList<>();

        for (FPediaTypeEntity ft : list2) {
            List<FPediaEntity> result = new ArrayList<>();
            for (FPediaEntity f : fPediaEntities) {

                if (f.getTypeid() == ft.getItemid()) {
                    result.add(f);
                }


            }
            Map<String, List<FPediaEntity>> map = new HashMap<>();
            List<FPediaEntity> list1 = new ArrayList<>();
            List<FPediaEntity> list22 = new ArrayList<>();
            List<FPediaEntity> list3 = new ArrayList<>();

            int bj = 1;
            for (int i = 0; i < result.size(); i++) {

                if (bj % 3 == 1) {
                    list1.add(result.get(i));
                    bj++;
                } else if (bj % 3 == 2) {
                    list22.add(result.get(i));
                    bj++;
                } else if (bj % 3 == 0) {
                    list3.add(result.get(i));
                    bj++;
                }

            }
            map.put("listBaiKe1", list1);
            map.put("listBaiKe2", list22);
            map.put("listBaiKe3", list3);

            results.add(map);
        }


        System.out.println(results);

        model.addAttribute("listBaiKeResults"+list2.get(0).getZxbkType(), results);


        return null;
    }

}
