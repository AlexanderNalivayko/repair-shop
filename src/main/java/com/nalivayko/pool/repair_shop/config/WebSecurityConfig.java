package com.nalivayko.pool.repair_shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home_page", "/about_page").permitAll()
                .antMatchers("/manager/**").hasAuthority("MANAGER")
                .antMatchers("/master/**").hasAuthority("MASTER")
                .antMatchers("/repair_page/**").hasAuthority("CUSTOMER")
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/home_page", true)
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutSuccessUrl("/home_page")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new MessageDigestPasswordEncoder("MD5"))
                .usersByUsernameQuery("select `username`, `password`, true from users where `username`=?")
                .authoritiesByUsernameQuery("select `username`, `role` from users where `username`=?");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**","/webjars/**", "/js/**", "/css/**");
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}

