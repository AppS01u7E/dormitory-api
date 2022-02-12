package com.appsolute.soom.dormitoryapi.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //TODO 추후 인증 및 인가 관련 로직 설정
                .authorizeRequests().antMatchers("/**").permitAll()
                .and()
                .cors().disable()
                .csrf().disable();
    }
}
