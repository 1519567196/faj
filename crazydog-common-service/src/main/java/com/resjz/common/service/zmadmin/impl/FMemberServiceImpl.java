package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.dao.FMemberDao;
import com.resjz.common.dao.zmadmin.entity.FMemberEntity;
import com.resjz.common.service.zmadmin.FMemberService;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("fMemberService")
public class FMemberServiceImpl extends ServiceImpl<FMemberDao, FMemberEntity> implements FMemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FMemberEntity> page = this.selectPage(
                new Query<FMemberEntity>(params).getPage(),
                new EntityWrapper<FMemberEntity>()
                        .orderBy("memberId", false)
        );

        return new PageUtils(page);
    }

    @Override
    public FMemberEntity loadUserByUsername(String s) throws UsernameNotFoundException {


        List<FMemberEntity> mobile = this.selectList(new EntityWrapper<FMemberEntity>().eq("mobile", s).eq("status",0));
        if (mobile == null || mobile.size() == 0) {
          return null;
        }
        return mobile.get(0);

    }
}
