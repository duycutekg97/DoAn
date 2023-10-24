/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.qlphongtro.service.Impl.CustomLogoutHandler;
import java.text.SimpleDateFormat;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

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
    "com.qlphongtro.service"
})
@Order(2)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomLogoutHandler logoutHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {

        http.formLogin().loginPage("/login/")
                .usernameParameter("username")
                .passwordParameter("password");

        http.formLogin().defaultSuccessUrl("/")
                .failureUrl("/login?error");

        http.logout()
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .logoutSuccessUrl("/login");
        http.exceptionHandling()
                .accessDeniedPage("/login?accessDenied");
        http.authorizeRequests().antMatchers("/danh-sach-nguoi-dung/").hasRole("Admin");

        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/index/User/").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/host/**").access("hasAnyRole('ROLE_ADMIN' , 'ROLE_HOST')");
        http.authorizeRequests().antMatchers("/follow/**").access("hasAnyRole('ROLE_ADMIN' , 'ROLE_HOST' , 'ROLE_RENTER')");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/detailMotel/**")
                .access("hasAnyRole('ROLE_ADMIN' , 'ROLE_HOST', 'ROLE_RENTER')");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/indexFindAccommodation/post/**")
                .access("hasAnyRole('ROLE_ADMIN' , 'ROLE_HOST', 'ROLE_RENTER')");

//                .access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN', 'ROLE_GIAOVU', 'ROLE_STUDENT')")
//                .antMatchers("/login").permitAll()
//                .antMatchers("/perform-logout").permitAll()
//                .antMatchers("/danh-sach-nguoi-dung/")
//                .access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/danh-sach-diem-khoa-luan/")
//                .access("hasRole('ROLE_TEACHER')")
//                .antMatchers("/danh-sach-hoi-dong-khoa-luan/")
//                .access("hasRole('ROLE_GIAOVU')");
////                .antMatchers("/**/pay")
//                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
        http.csrf().disable();
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", this.env.getProperty("cloudinary.cloud_name"),
                        "api_key", this.env.getProperty("cloudinary.api_id"),
                        "api_secret", this.env.getProperty("cloudinary.api_secret"),
                        "secure", true));
        return cloudinary;
    }

    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(this.env.getProperty("spring.mail.port")));

        mailSender.setUsername(this.env.getProperty("spring.mail.username"));
        mailSender.setPassword(this.env.getProperty("spring.mail.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", this.env.getProperty("spring.mail.properties.mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", this.env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
                "This is the test email template for your email:\n%s\n");
        return message;
    }

    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean freeMarkerFactoryBean = new FreeMarkerConfigurationFactoryBean();
        freeMarkerFactoryBean.setTemplateLoaderPaths("classpath:/templates");
        freeMarkerFactoryBean.setPreferFileSystemAccess(true);
        freeMarkerFactoryBean.setDefaultEncoding("UTF-8");
        return freeMarkerFactoryBean;
    }
}
