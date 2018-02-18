package com.visu.align.ims.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String REALM = "REALM";
    private static final String ADMIN = "admin";
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String ADMIN_PASSWORD = "adminPassword";

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(ADMIN)
                .password(ADMIN_PASSWORD)
                .roles(ADMIN_ROLE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/products/*")
                .hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.PUT, "/products/*")
                .hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.DELETE, "/products/*")
                .hasRole(ADMIN_ROLE)
                .and()
                .httpBasic()
                .realmName(REALM)
                .authenticationEntryPoint(getBasicAuthEntryPoint())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }
}