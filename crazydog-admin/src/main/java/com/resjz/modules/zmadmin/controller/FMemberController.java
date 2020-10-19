package com.resjz.modules.zmadmin.controller;

import com.resjz.common.dao.zmadmin.entity.FMemberEntity;
import com.resjz.common.service.zmadmin.FMemberService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.R;
import com.resjz.common.validator.Assert;
import com.resjz.common.validator.ValidatorUtils;
import com.resjz.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;



/**
 * 用户
 *
 * @author erdandan
 * @email resjz1@163.com
 * @date 2020-08-31 10:57:10
 */
@RestController
@RequestMapping("sys/fmember")
public class FMemberController extends AbstractController {
    @Autowired
    private FMemberService fMemberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:fmember:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fMemberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberId}")
    @RequiresPermissions("sys:fmember:info")
    public R info(@PathVariable("memberId") Integer memberId){
        FMemberEntity fMember = fMemberService.selectById(memberId);

        return R.ok().put("fMember", fMember);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:fmember:save")
    public R save(@RequestBody FMemberEntity fMember){
        Assert.isBlank(fMember.getMemberName(),"会员名不能为空");
        Assert.isNull(fMember.getMobile(),"手机号不能为空");

        Assert.isNull(fMember.getHeadImg(),"头像不能为空");
        Assert.isNull(fMember.getSex(),"性别不能为空");
        fMember.setAddtime(new Date());
        fMember.setInvitationCount(0);
        fMemberService.insert(fMember);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:fmember:update")
    public R update(@RequestBody FMemberEntity fMember){
        ValidatorUtils.validateEntity(fMember);
        Assert.isBlank(fMember.getMemberName(),"会员名不能为空");
        Assert.isNull(fMember.getMobile(),"手机号不能为空");


        Assert.isNull(fMember.getHeadImg(),"头像不能为空");
        Assert.isNull(fMember.getSex(),"性别不能为空");
        fMember.setAddtime(new Date());


        fMemberService.updateAllColumnById(fMember);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:fmember:delete")
    public R delete(@RequestBody Integer[] memberIds){
        fMemberService.deleteBatchIds(Arrays.asList(memberIds));

        return R.ok();
    }



    /**
     * 是否禁用
     */
    @RequestMapping("/status/{memberId}")
    //   @RequiresPermissions("sys:tcase:recommend")
    public R recommend(@PathVariable("memberId") Integer memberId){

        FMemberEntity fMember = fMemberService.selectById(memberId);
        if(fMember!=null){
            if(fMember.getStatus()==0){
                fMember.setStatus(1);
            }else{
                fMember.setStatus(0);
            }
            fMemberService.updateById(fMember);
        }

        return R.ok().put("msg","操作成功");
    }

}
