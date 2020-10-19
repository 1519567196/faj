package com.resjz.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FJobEntity;
import com.resjz.common.service.zmadmin.FJobService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.Assert;
import com.resjz.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 招聘
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@Controller
@RequestMapping("fjob")
public class FJobController {
    @Autowired
    private FJobService fJobService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fjob:list")
    public String list(Model model, HttpServletRequest req, HttpServletResponse response) {
        Model model1 = (Model) req.getAttribute("model");
        if (model1 != null) {
            Map<String, Object> stringObjectMap = model1.asMap();
            model.addAllAttributes(stringObjectMap);
        }


        List<FJobEntity> fJobEntities = fJobService.selectList(new EntityWrapper<FJobEntity>().eq(false, "areaid", null));
        model.addAttribute("fJobEntities", fJobEntities);
        return "recruitment";
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{iobId}")
    @RequiresPermissions("sys:fjob:info")
    public R info(@PathVariable("iobId") Integer iobId) {
        FJobEntity fJob = fJobService.selectById(iobId);

        return R.ok().put("fJob", fJob);
    }


}
