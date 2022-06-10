package com.miniProject.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //User
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/account/register",
                        "/category/getAll",
                        "/category/getById/**",
                        "/product/all",
                        "/product/getById/**",
                        "/shipper/getAll",
                        "/shipper/getById")
                .permitAll()
                .antMatchers(
                        "/account/profile",
                        "/account/update",
                        "/account/order",
                        "/account/history",
                        "/order/new-order",
                        "/order/confirm-order",
                        "order/new-order",
                        "order/confirm-order")
                .hasAnyAuthority("User", "Admin")
                .antMatchers(
                        "/account/delete",
                        "/account/all",
                        "/category/**",
                        "/product/**",
                        "/order/**",
                        "/shipper/**",
                        "/order/**")
                .hasAuthority("Admin")

                .anyRequest().permitAll()
//                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(15);
    }
}

