package com.resjz.web.config;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.resjz.common.dao.zmadmin.entity.FMemberEntity;
import com.resjz.common.service.zmadmin.FMemberService;
import com.resjz.common.service.zmadmin.impl.FMemberServiceImpl;
import com.resjz.web.config.security.LocalAccessDeniedHandler;
import com.resjz.web.config.security.LocalAuthenticationEntryPoint;
import com.resjz.web.config.security.LocalAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.List;
import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

//    @Autowired
//    private FMemberService fMemberService;


    /**
     * 指定加密方式
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        // 设置数据源
        tokenRepository.setDataSource(dataSource);
        // 启动的时候创建存储token的表
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)//user Details Service验证
                .passwordEncoder(
                        passwordEncoder());

        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/fjs/**", "/fcss/**", "/fimages/**","/register.html",
                "/login",
                "/ffitupcompanycases/list",
                "/fpedia/list",
                "/index","/index.html","/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {




        http.formLogin() // 表单登录

                .loginPage("/login.html")
//                .loginProcessingUrl("/sys/fmember/login") // 登录请求拦截的url,也就是form表单提交时指定的action
//                .successHandler(loginSuccessHandler)
                .failureHandler(new LocalAuthenticationFailureHandler())
                .usernameParameter("phone")
                .successForwardUrl("/index")
                .and()
                .rememberMe()
//                .userDetailsService(customUserDetailsService) // 设置userDetailsService
//                .tokenRepository(persistentTokenRepository()) // 设置数据访问层
                .tokenValiditySeconds(60*24* 60) // 记住我的时间(秒)
                .and()
                .authorizeRequests() // 对请求授权
                .antMatchers("/czPassword","/fmember/update","/czSuccess")  .authenticated() // 允许所有人访问login.html和自定义的登录页
//                .antMatchers(HttpMethod.POST, "/sys/fmember/save").permitAll()
//                .antMatchers(HttpMethod.GET, "/index").permitAll()
                .anyRequest() // 任何请求
                .permitAll()
//                .authenticated()// 需要身份认证
                .and()
                .csrf().disable() // 关闭跨站伪造
        ;












    }
}