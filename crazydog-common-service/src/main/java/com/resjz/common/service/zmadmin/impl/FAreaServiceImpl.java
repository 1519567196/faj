package com.resjz.common.service.zmadmin.impl;

import com.resjz.common.dao.zmadmin.dao.FAreaDao;
import com.resjz.common.dao.zmadmin.entity.FAreaEntity;
import com.resjz.common.service.zmadmin.FAreaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


@Service("fAreaService")
public class FAreaServiceImpl extends ServiceImpl<FAreaDao, FAreaEntity> implements FAreaService {

    @Override
    public List<FAreaEntity> query(Map<String, Object> params) {
        List<FAreaEntity> fAreaEntities = this.selectList(null);
        return fAreaEntities;
    }

    @Override
    public List<FAreaEntity> treeList() {
        List<FAreaEntity> rootList = this.selectList(new EntityWrapper<FAreaEntity>().eq("area_parent_id", 0));
        List<FAreaEntity> bodyList = this.selectList(new EntityWrapper<FAreaEntity>().ne("area_parent_id", 0));
        Map<Integer, Integer> map = new HashMap<>(bodyList.size());

        rootList.forEach(beanTree -> getChild(beanTree,map,bodyList));
        return rootList;
    }


    public void getChild(FAreaEntity treeDto, Map<Integer, Integer> map, List<FAreaEntity> bodyList) {
        List<FAreaEntity> childList = new ArrayList<>();
        bodyList.stream()
                .filter(c -> !map.containsKey(c.getAreaId()))
                .filter(c -> c.getAreaParentId().equals(treeDto.getAreaId()))
                .forEach(c -> {
                    map.put(c.getAreaId(), c.getAreaParentId());

                    getChild(c, map, bodyList);
                    childList.add(c);
                });
        treeDto.setChildList(childList);//子数据集
    }

}
