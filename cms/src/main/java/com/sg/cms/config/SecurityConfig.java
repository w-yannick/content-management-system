/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 *
 * @author w-yan
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
//public class SecurityConfig {

    @Autowired
    public void configureGlobalInMemory(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("manager").password("{noop}password").roles("MANAGER")
                .and()
                .withUser("admin").password("{noop}password").roles("ADMIN", "MANAGER");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    
                .csrf().disable()
                .authorizeRequests()
                    //    "page To be protected"    "ROLE"   
                    .antMatchers("/create").hasRole("MANAGER")
                    .antMatchers("/pendingApproval").hasRole("ADMIN")
                    .antMatchers("/", "/home").permitAll()
                    .antMatchers("/css/**", "/js/**", "/fonts/**").permitAll()
//                    .anyRequest().hasRole("MANAGER")
                    .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home",true)
                    .failureUrl("/login?login_error=1")
                    .permitAll()
                .and()
                .logout()
                    .logoutSuccessUrl("/home")
                    .logoutUrl("/logout")
                    .permitAll();          
    }
}