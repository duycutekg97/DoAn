/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.configs;

import com.qlphongtro.filters.CustomAccessDeniedHandler;
import com.qlphongtro.filters.JwtAuthenticationTokenFilter;
import com.qlphongtro.filters.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author HOME
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.qlphongtro.controllers",
    "com.qlphongtro.repository",
    "com.qlphongtro.service",
    "com.qlphongtro.components"
})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password");
//
//        http.formLogin().defaultSuccessUrl("/")
//                .failureUrl("/login?error");
//
//        http.logout().logoutSuccessUrl("/login");
//
//        http.exceptionHandling()
//                .accessDeniedPage("/login?accessDenied");

//        http.authorizeRequests().antMatchers("/").permitAll()
//            .antMatchers("/api/**")
//            .access("hasRole('ROLE_ADMIN')");
//        http.csrf().disable();
        // Disable crsf cho đường dẫn /rest/**
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests().antMatchers("/api/current-user/").permitAll();
        http.authorizeRequests().antMatchers("/api/users/").permitAll();
        http.authorizeRequests().antMatchers("/api/register/").permitAll();
                http.authorizeRequests().antMatchers("/api/add/evaluate/{motelId}/").permitAll();
       http.authorizeRequests().antMatchers("/api/details/motel/{motelId}").permitAll();
       http.authorizeRequests().antMatchers("/api/motel/{motelId}").permitAll();
        http.authorizeRequests().antMatchers("/api/motel/{motelId}/evaluate/").permitAll();
        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/**").permitAll();
//        http.authorizeRequests().antMatchers("/api/diemtrungbinh/").permitAll();
//        http.authorizeRequests().antMatchers("/api/diemtrungbinh/")
//                .access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN', 'ROLE_GIAOVU', 'ROLE_STUDENT', 'ROLE_USER')");

        http.authorizeRequests().antMatchers("/api/swagger-ui.html").permitAll();
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").access("hasAnyRole('ROLE_TEACHER', 'ROLE_GIAOVU', 'ROLE_STUDENT')")
                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_GIAOVU')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_GIAOVU')")
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}
