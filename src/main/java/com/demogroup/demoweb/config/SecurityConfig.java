package com.demogroup.demoweb.config;

import com.demogroup.demoweb.domain.Role;
import com.demogroup.demoweb.security.JWTFilter;
import com.demogroup.demoweb.security.LoginFilter;
import com.demogroup.demoweb.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
//왜인지는 모르겠지만 BCryptPasswordEncoder는 따로 만든 @Configuration에서 정의해주어야 잘 돌아가는 듯 하다
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtils jwtUtils;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf->csrf
                        .disable()
                )
                .formLogin((form)->form
                        .disable()
                )
                .httpBasic((basic)->basic
                        .disable()
                )
                .authorizeHttpRequests(request->request
                        .requestMatchers("/","/login","/loginform","/loginProc","/join","/joinProc","/admin").permitAll()
//                        .requestMatchers("/admin").hasRole("USER")
                        .anyRequest().authenticated()
                )
//                .formLogin(form->form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/loginProc")
//                        .defaultSuccessUrl("/admin")
//                        .failureForwardUrl("/error")
//                        .permitAll()
//                )
                .addFilterBefore(new JWTFilter(jwtUtils), LoginFilter.class)
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtils),UsernamePasswordAuthenticationFilter.class)
                .logout((out)->out
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
                .sessionManagement((session)->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .build();
    }
}
