package com.resjz.web.config;

import com.resjz.common.dao.zmadmin.entity.FMemberEntity;
import com.resjz.common.service.zmadmin.FMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private FMemberService fMemberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        FMemberEntity fMemberEntity = fMemberService.loadUserByUsername(s);
        if (fMemberEntity==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        // 得到用户角色
        String role = "admin";
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return new User(
                fMemberEntity.getMobile(),
               fMemberEntity.getPassword(),
                authorities
        );


    }
}
