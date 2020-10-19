package com.resjz.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FMemberEntity;
import com.resjz.common.service.zmadmin.FMemberService;
import com.resjz.common.utils.Constant;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.utils.ShiroUtils;
import com.resjz.common.validator.Assert;
import com.resjz.common.validator.ValidatorUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 用户
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@Controller
@RequestMapping("/fmember")
public class FMemberController {
    @Autowired
    private FMemberService fMemberService;


    @Autowired
    private PasswordEncoder passwordEncoder;

//
//    /**
//     * 列表
//     */
//    @RequestMapping("/list")
//    @RequiresPermissions("sys:fmember:list")
//    public R list(@RequestParam Map<String, Object> params) {
//        PageUtils page = fMemberService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }


//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{memberId}")
//    @RequiresPermissions("sys:fmember:info")
//    public R info(@PathVariable("memberId") Integer memberId) {
//        FMemberEntity fMember = fMemberService.selectById(memberId);
//
//        return R.ok().put("fMember", fMember);
//    }

    /**
     * 注册
     */
    @ResponseBody
    @RequestMapping("/save")
    public R save(@RequestBody FMemberEntity fMember) {

        List<FMemberEntity> mobile = fMemberService.selectList(new EntityWrapper<FMemberEntity>().eq("mobile", fMember.getMobile()));
        if (mobile != null && mobile.size() > 0) {
            return R.error(501, "手机号已经被注册");
        }
        fMember.setPassword(passwordEncoder.encode(fMember.getPassword()));

        fMember.setAddtime(new Date());
        fMember.setInvitationCount(0);
        fMemberService.insert(fMember);

        return new R();
    }
//
//    /**
//     * 登录
//     */
//    @ResponseBody
//    @RequestMapping("/login")
//    public R login(@RequestBody FMemberEntity fMember, HttpServletRequest req, HttpServletResponse resp) {
//
//        List<FMemberEntity> mobile = fMemberService.selectList(new EntityWrapper<FMemberEntity>().eq("mobile", fMember.getMobile()));
//        if (mobile == null || mobile.size() == 0) {
//            return R.error(501, "手机号无注册信息");
//        }
//        String password = mobile.get(0).getPassword();
//        String s = ShiroUtils.sha256(fMember.getPassword(), Constant.MEMBER_SALT);
//        if (!fMember.getPassword().equals(password)) {
//            return R.error(502, "密码错误");
//        }
//        req.getSession().setAttribute("login", mobile.get(0));
//        if (fMember.getAuto()){
////            req.getSession().setAttribute("login", mobile.get(0));
//            req.getSession().setMaxInactiveInterval(100*60);
//
//        }
//
//        return new R();
//    }


    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(@RequestParam(value = "newPass") String newPass) {
        String s = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            System.out.println(currentUserName);
            s = currentUserName;
        }
        if (s.equals("")) {
            return R.error("登录信息失效,请重新登录");
        }

        Assert.isBlank(newPass.trim(), "新密码不能为空");

        FMemberEntity fMemberEntity = fMemberService.loadUserByUsername(s);
        fMemberEntity.setPassword(passwordEncoder.encode(newPass.trim()));


        boolean b = fMemberService.updateAllColumnById(fMemberEntity);//全部更新
        if (b) {
            return R.ok();

        }
        return R.error("修改失败");

    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fmember:delete")
    public R delete(@RequestBody Integer[] memberIds) {
        fMemberService.deleteBatchIds(Arrays.asList(memberIds));

        return R.ok();
    }


    /**
     * 是否禁用
     */
    @RequestMapping("/status/{memberId}")
    //   @RequiresPermissions("sys:tcase:recommend")
    public R recommend(@PathVariable("memberId") Integer memberId) {

        FMemberEntity fMember = fMemberService.selectById(memberId);
        if (fMember != null) {
            if (fMember.getStatus() == 0) {
                fMember.setStatus(1);
            } else {
                fMember.setStatus(0);
            }
            fMemberService.updateById(fMember);
        }

        return R.ok().put("msg", "操作成功");
    }

}
