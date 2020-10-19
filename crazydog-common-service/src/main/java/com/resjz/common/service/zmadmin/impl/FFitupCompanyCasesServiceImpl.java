package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.sys.entity.SysUserEntity;
import com.resjz.common.dao.zmadmin.dao.FFitupCompanyCasesDao;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrValueEntity;
import com.resjz.common.dao.zmadmin.entity.FFitupCompanyCasesEntity;
import com.resjz.common.dao.zmadmin.entity.FFitupCompanyEntity;
import com.resjz.common.service.sys.SysUserService;
import com.resjz.common.service.zmadmin.FCommonAttrValueService;
import com.resjz.common.service.zmadmin.FFitupCompanyCasesService;
import com.resjz.common.service.zmadmin.FFitupCompanyService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("fFitupCompanyCasesService")
public class FFitupCompanyCasesServiceImpl extends ServiceImpl<FFitupCompanyCasesDao, FFitupCompanyCasesEntity> implements FFitupCompanyCasesService {
    @Autowired
    private FCommonAttrValueService fCommonAttrValueService;

    @Autowired
    private FFitupCompanyService fFitupCompanyService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FFitupCompanyCasesEntity> page = this.selectPage(
                new Query<FFitupCompanyCasesEntity>(params).getPage(),
                new EntityWrapper<FFitupCompanyCasesEntity>()
        );
        for (FFitupCompanyCasesEntity f : page.getRecords()) {
            FFitupCompanyEntity fFitupCompanyEntity = fFitupCompanyService.selectById(f.getCompanyId());
            f.setCompanyName(fFitupCompanyEntity==null?"":fFitupCompanyEntity.getCompanyName()==null?"":fFitupCompanyEntity.getCompanyName());
            f.setMainImg( f.getMainImg());
//            f.setMainImg(pathNginx + f.getMainImg());
            SysUserEntity sysUserEntity = sysUserService.selectById(f.getAdduserid());
            f.setAdduserName(sysUserEntity == null ? "" : sysUserEntity.getUsername() == null ? "" : sysUserEntity.getUsername());
            FCommonAttrValueEntity fCommonAttrValueEntity = fCommonAttrValueService.selectById(f.getStyleid());
            f.setStyle(fCommonAttrValueEntity == null ? "" : fCommonAttrValueEntity.getTitle() == null ? "" : fCommonAttrValueEntity.getTitle());
            FCommonAttrValueEntity fCommonAttrValueEntity1 = fCommonAttrValueService.selectById(f.getHouseTypeid());
            f.setHouseType(fCommonAttrValueEntity1 == null ? "" : fCommonAttrValueEntity1.getTitle() == null ? "" : fCommonAttrValueEntity1.getTitle());
            FCommonAttrValueEntity fCommonAttrValueEntity2 = fCommonAttrValueService.selectById(f.getSquareid());
            f.setSquareNum(fCommonAttrValueEntity2 == null ? "" : fCommonAttrValueEntity2.getTitle() == null ? "" : fCommonAttrValueEntity2.getTitle());


        }


        return new PageUtils(page);
    }

    @Override
    public void getIndexCase3(Model model, List<FCommonAttrValueEntity> list) {

        List<FFitupCompanyCasesEntity> fFitupCompanyCasesEntities = this.selectList(new EntityWrapper<FFitupCompanyCasesEntity>().eq("is_public", 1));
        List<Map<String, List<FFitupCompanyCasesEntity>>> results = new ArrayList<>();
        for (FCommonAttrValueEntity fa : list
        ) {
            List<FFitupCompanyCasesEntity> result = new ArrayList<>();

            for (FFitupCompanyCasesEntity f : fFitupCompanyCasesEntities
            ) {
                if (f.getPublicTypeId() == fa.getValueId()) {

                    result.add(f);
                }


            }
            Map<String, List<FFitupCompanyCasesEntity>> map=new HashMap<>();
            List<FFitupCompanyCasesEntity> list1 = new ArrayList<>();
            List<FFitupCompanyCasesEntity> list2 = new ArrayList<>();
            List<FFitupCompanyCasesEntity> list3 = new ArrayList<>();

            int bj = 1;
            for (int i = 0; i < result.size(); i++) {

                if (bj % 3 == 1) {
                    list1.add(result.get(i));
                    bj++;
                } else if (bj % 3 == 2) {
                    list2.add(result.get(i));
                    bj++;
                } else if (bj % 3 == 0) {
                    list3.add(result.get(i));
                    bj++;
                }

            }
            map.put("listBaiKe1", list1);
            map.put("listBaiKe2", list2);
            map.put("listBaiKe3", list3);

            results.add(map);



        }
        model.addAttribute("listBaiKeResults5", results);


    }

    @Override
    public PageUtils listPage(Map<String, Object> params) {



        Page<FFitupCompanyCasesEntity> page = this.selectPage(
                new Query<FFitupCompanyCasesEntity>(params).getPage(),
                new EntityWrapper<FFitupCompanyCasesEntity>()
                .eq(params.get("squareid")!=null&&params.get("squareid")!="","squareid",(String) params.get("squareid"))
                .eq(params.get("house_typeid")!=null&&params.get("house_typeid")!="0","house_typeid",(String) params.get("house_typeid"))
                .eq(params.get("styleid")!=null&&params.get("styleid")!="","styleid",(String) params.get("styleid"))
                .eq(params.get("moneyid")!=null&&params.get("moneyid")!="","moneyid",(String) params.get("moneyid"))
                .eq("is_public",2)
        );


        for (FFitupCompanyCasesEntity f : page.getRecords()) {
            FFitupCompanyEntity fFitupCompanyEntity = fFitupCompanyService.selectById(f.getCompanyId());
            f.setCompanyName(fFitupCompanyEntity==null?"":fFitupCompanyEntity.getCompanyName()==null?"":fFitupCompanyEntity.getCompanyName());
            f.setMainImg( f.getMainImg());
//            f.setMainImg(pathNginx + f.getMainImg());
            SysUserEntity sysUserEntity = sysUserService.selectById(f.getAdduserid());
            f.setAdduserName(sysUserEntity == null ? "" : sysUserEntity.getUsername() == null ? "" : sysUserEntity.getUsername());
            FCommonAttrValueEntity fCommonAttrValueEntity = fCommonAttrValueService.selectById(f.getStyleid());
            f.setStyle(fCommonAttrValueEntity == null ? "" : fCommonAttrValueEntity.getTitle() == null ? "" : fCommonAttrValueEntity.getTitle());
            FCommonAttrValueEntity fCommonAttrValueEntity1 = fCommonAttrValueService.selectById(f.getHouseTypeid());
            f.setHouseType(fCommonAttrValueEntity1 == null ? "" : fCommonAttrValueEntity1.getTitle() == null ? "" : fCommonAttrValueEntity1.getTitle());
            FCommonAttrValueEntity fCommonAttrValueEntity2 = fCommonAttrValueService.selectById(f.getSquareid());
            f.setSquareNum(fCommonAttrValueEntity2 == null ? "" : fCommonAttrValueEntity2.getTitle() == null ? "" : fCommonAttrValueEntity2.getTitle());
        }

        return new PageUtils(page);
    }

    @Override
    public PageUtils listPage2(Map<String, Object> params) {
        String title = params.get("title").toString();
        Page<FFitupCompanyCasesEntity> page = this.selectPage(
                new Query<FFitupCompanyCasesEntity>(params).getPage(),
                new EntityWrapper<FFitupCompanyCasesEntity>()
                .eq("is_public",2)
                .like("title",title)
        );
        for (FFitupCompanyCasesEntity f : page.getRecords()) {
            FFitupCompanyEntity fFitupCompanyEntity = fFitupCompanyService.selectById(f.getCompanyId());
            f.setCompanyName(fFitupCompanyEntity==null?"":fFitupCompanyEntity.getCompanyName()==null?"":fFitupCompanyEntity.getCompanyName());
            f.setMainImg( f.getMainImg());
//            f.setMainImg(pathNginx + f.getMainImg());
            SysUserEntity sysUserEntity = sysUserService.selectById(f.getAdduserid());
            f.setAdduserName(sysUserEntity == null ? "" : sysUserEntity.getUsername() == null ? "" : sysUserEntity.getUsername());
            FCommonAttrValueEntity fCommonAttrValueEntity = fCommonAttrValueService.selectById(f.getStyleid());
            f.setStyle(fCommonAttrValueEntity == null ? "" : fCommonAttrValueEntity.getTitle() == null ? "" : fCommonAttrValueEntity.getTitle());
            FCommonAttrValueEntity fCommonAttrValueEntity1 = fCommonAttrValueService.selectById(f.getHouseTypeid());
            f.setHouseType(fCommonAttrValueEntity1 == null ? "" : fCommonAttrValueEntity1.getTitle() == null ? "" : fCommonAttrValueEntity1.getTitle());
            FCommonAttrValueEntity fCommonAttrValueEntity2 = fCommonAttrValueService.selectById(f.getSquareid());
            f.setSquareNum(fCommonAttrValueEntity2 == null ? "" : fCommonAttrValueEntity2.getTitle() == null ? "" : fCommonAttrValueEntity2.getTitle());
        }

        return new PageUtils(page);
    }

}
