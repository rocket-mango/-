package com.demogroup.demoweb.config;

import com.demogroup.demoweb.domain.Role;
import com.demogroup.demoweb.domain.User;
import com.demogroup.demoweb.security.auth.CustomUserDetailsService;
import com.demogroup.demoweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .httpBasic(httpBasic->httpBasic
                        .disable())
                .csrf(csrf->csrf
                        .disable())
                .authorizeHttpRequests(request->request
                        .requestMatchers(HttpMethod.POST,"/api/user/**","/user/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/home").hasRole(Role.USER.name())
                        .anyRequest().authenticated()
                )
                .formLogin((formLogin)->
                        formLogin
                                .loginPage("/user/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/home",true)
                                .permitAll())
                .build();
    }
}
