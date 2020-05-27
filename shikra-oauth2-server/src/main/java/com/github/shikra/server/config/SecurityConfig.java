package com.github.shikra.server.config;

import com.github.shikra.server.config.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(3)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private SecurityAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private SecurityAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private SecurityAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private SecurityLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private SecurityUserDetailsService securityUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .accessDeniedHandler(accessDeniedHandler)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/oauth/**").permitAll()
//                .anyRequest()
//                .authenticated()   // 其他地址的访问均需验证权限
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/oauth/sign_in")
////                .successHandler(authenticationSuccessHandler)
////                .failureHandler(authenticationFailureHandler)
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/oauth/sign_out")
//                .logoutSuccessHandler(logoutSuccessHandler)
//                .and()
//                .rememberMe()
//                .rememberMeParameter("rememberMe")
//                .and().headers().cacheControl();
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.formLogin().loginPage("/oauth/sign_in").failureUrl("/oauth/sign_in?error").permitAll();
        http.logout().permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/antd/**","/actuator/**");
    }
}
