package com.resjz.modules.zmadmin.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FProgrammeVrEntity;
import com.resjz.common.service.zmadmin.FProgrammeVrService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.ValidatorUtils;
import com.resjz.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;



/**
 * 我家方案-全景
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fprogrammevr")
public class FProgrammeVrController extends AbstractController {
    @Autowired
    private FProgrammeVrService fProgrammeVrService;
    @Value("${upload.savePath}")
    private String savePath;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fprogrammevr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fProgrammeVrService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 首页推荐
     */
    @Transactional
    @RequestMapping("/indexPush/{itemid}")
    public R indexPush(@PathVariable("itemid") Integer itemid){
        try {
            FProgrammeVrEntity fProgrammeVrEntity = fProgrammeVrService.selectById(itemid);
            if (fProgrammeVrEntity.getIndexpush()==1){
                return R.error("它已经是首页推荐啦");
            }
            fProgrammeVrEntity.setIndexpush(1);
            boolean b = fProgrammeVrService.updateAllColumnById(fProgrammeVrEntity);
            FProgrammeVrEntity fProgrammeVrEntityU =new FProgrammeVrEntity();
            fProgrammeVrEntityU.setIndexpush(0);
            boolean itemid1 = fProgrammeVrService.update(fProgrammeVrEntityU, new EntityWrapper<FProgrammeVrEntity>().ne("itemid", itemid));

            if (b){
                return R.ok();
            }

            return R.error("设置失败，请联系管理员");
        } catch (RuntimeException e) {


            e.printStackTrace();
            return R.error("设置失败，请联系管理员");
        }

    }




    /**
     * 信息
     */
    @RequestMapping("/info/{itemid}")
    @RequiresPermissions("sys:fprogrammevr:info")
    public R info(@PathVariable("itemid") Integer itemid){
        FProgrammeVrEntity fProgrammeVr = fProgrammeVrService.selectById(itemid);

        return R.ok().put("fProgrammeVr", fProgrammeVr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fprogrammevr:save")
    public R save(@RequestBody FProgrammeVrEntity fProgrammeVr){
        fProgrammeVr.setAddtime(new Date());
        fProgrammeVr.setAddUserid(getUserId());
        fProgrammeVrService.insert(fProgrammeVr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fprogrammevr:update")
    public R update(@RequestBody FProgrammeVrEntity fProgrammeVr){
        fProgrammeVr.setUpdatetime(new Date());
        ValidatorUtils.validateEntity(fProgrammeVr);
        fProgrammeVrService.updateAllColumnById(fProgrammeVr);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fprogrammevr:delete")
    public R delete(@RequestBody Integer[] itemids){
        for (Integer i : itemids) {
            FProgrammeVrEntity fProgrammeVrEntity = fProgrammeVrService.selectById(i);
            if (fProgrammeVrEntity != null && fProgrammeVrEntity.getVrImg() != null) {
                String imgurl = savePath + fProgrammeVrEntity.getVrImg();
//        System.out.println(imgurl+"         nnnn");

                File serverFile = new File(imgurl);
                if (serverFile.exists() && serverFile.isFile()
                        && serverFile.delete() == true) {


                }

            }


        }
        fProgrammeVrService.deleteBatchIds(Arrays.asList(itemids));


        return R.ok();
    }

}
