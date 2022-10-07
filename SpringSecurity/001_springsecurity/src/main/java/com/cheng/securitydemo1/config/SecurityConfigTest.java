package com.cheng.securitydemo1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


@Configuration
public class SecurityConfigTest extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbc = new JdbcTokenRepositoryImpl();
        jdbc.setDataSource(dataSource);
        //jdbc.setCreateTableOnStartup(true);
        return jdbc;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //退出的配置
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/test/hello").permitAll();
        http.formLogin()//自定义编写登陆页面
                .loginPage("/login.html")//登陆页面的设置
                .loginProcessingUrl("/user/login")//登陆访问路径
                .defaultSuccessUrl("/success.html").permitAll()//成功之后跳转到路径
                .and().authorizeRequests()
                    .antMatchers("/","/test/hello","/user/login").permitAll()//设置那些登录可以直接访问，不需要认证
                //当前登录的用户，只有具有admin权限的才可以访问这个路径
//                    .antMatchers("/test/index").hasAuthority("admins,manager")
//                .antMatchers("/test/index").hasAnyAuthority("admins,manager")
                .antMatchers("/test/index").hasRole("sale")
                .anyRequest().authenticated()
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)
                .userDetailsService(userDetailsService);
             //   .and().csrf().disable();
    }
}
