package com.resjz.modules.zmadmin.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrEntity;
import com.resjz.common.dao.zmadmin.entity.FCommonAttrValueEntity;
import com.resjz.common.dao.zmadmin.entity.FHouseSkuEntity;
import com.resjz.common.dao.zmadmin.entity.FHousesEntity;
import com.resjz.common.service.zmadmin.FCommonAttrService;
import com.resjz.common.service.zmadmin.FCommonAttrValueService;
import com.resjz.common.service.zmadmin.FHouseSkuService;
import com.resjz.common.service.zmadmin.FHousesService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import com.resjz.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 楼盘
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fhouses")
public class FHousesController extends AbstractController {
    @Autowired
    private FHousesService fHousesService;

    @Autowired
    private FHouseSkuService fHouseSkuService;

    @Autowired
    private FCommonAttrService fCommonAttrService;

    @Autowired
    private FCommonAttrValueService fCommonAttrValueService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fhouses:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = fHousesService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/onlyList")
    @RequiresPermissions("sys:fhouses:list")
    public R onlyList(@RequestParam Map<String, Object> params) {
        List<FHousesEntity> query = fHousesService.query();

        return R.ok().put("list", query);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fhouses:info")
    public R info(@PathVariable("itemid") Integer itemid) {
        FHousesEntity fHouses = fHousesService.selectById(itemid);
        List<FHouseSkuEntity> skus = fHouseSkuService.selectList(new EntityWrapper<FHouseSkuEntity>().eq("house_id", itemid));
        for (FHouseSkuEntity f : skus) {
            FCommonAttrEntity fCommonAttrEntity = fCommonAttrService.selectById(f.getAttrId());
            if (fCommonAttrEntity == null) {
                skus.remove(f);
                continue;
            }
            f.setAttr(fCommonAttrEntity == null ? "" : fCommonAttrEntity.getTitle());

            FCommonAttrValueEntity fCommonAttrValueEntity = fCommonAttrValueService.selectById(f.getAttrValueId());
            if (fCommonAttrValueEntity == null) {
                skus.remove(f);
                continue;
            }

            f.setAttrValue(fCommonAttrValueEntity.getTitle());

        }

        return R.ok().put("fHouses", fHouses).put("skus", skus);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fhouses:save")
    public R save(@RequestBody FHousesEntity fHouses) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = sdf.format(date);
        Date parse = null;
        try {
            parse = sdf.parse(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fHouses.setAddtime(parse);
        fHousesService.insert(fHouses);
        FHousesEntity housesEntity = fHousesService.selectOne(new EntityWrapper<FHousesEntity>().eq("addtime", parse));
        for (FHouseSkuEntity f : fHouses.getAttrandattrValue()
        ) {

            f.setHouseId(housesEntity.getItemid());

        }
        if (fHouses.getAttrandattrValue() != null && fHouses.getAttrandattrValue().size() > 0) {
            fHouseSkuService.addSkus(fHouses.getAttrandattrValue());
        }

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fhouses:update")
    public R update(@RequestBody FHousesEntity fHouses) {
        ValidatorUtils.validateEntity(fHouses);
        fHouses.setUpdatetime(new Date());
        fHousesService.updateAllColumnById(fHouses);//全部更新
        for (FHouseSkuEntity f : fHouses.getAttrandattrValue()
        ) {

            f.setHouseId(fHouses.getItemid());

        }
        if (fHouses.getAttrandattrValue() != null && fHouses.getAttrandattrValue().size() > 0) {
            fHouseSkuService.addSkus(fHouses.getAttrandattrValue());
        }

        return new R().put("code", 0).
                put("msg", "success");
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fhouses:delete")
    public R delete(@RequestBody Integer[] itemids) {
        fHousesService.deleteBatchIds(Arrays.asList(itemids));

        return R.ok();
    }

}
