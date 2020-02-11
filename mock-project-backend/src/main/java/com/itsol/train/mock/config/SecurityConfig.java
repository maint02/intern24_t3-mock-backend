package com.itsol.train.mock.config;

import com.itsol.train.mock.security.RoleEntitesConstants;
import com.itsol.train.mock.security.jwt.JWTConfigurer;
import com.itsol.train.mock.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    private final TokenProvider tokenProvider;

    public SecurityConfig(CorsFilter corsFilter, TokenProvider tokenProvider) {
        this.corsFilter = corsFilter;
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/login/*").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/employee/active-account/*").permitAll()
                .antMatchers("/api/logout").permitAll()
                .antMatchers("/api/user/register").permitAll()
                .antMatchers("/api/user/search").permitAll()
                .antMatchers("/api/user/get/*").permitAll()

                .antMatchers("/api/project/**").permitAll()
                .antMatchers("/api/issue/**").permitAll()
                .antMatchers("/api/issue-history/**").permitAll()
                .antMatchers("/api/employee-issue/**").permitAll()
                .antMatchers("/api/team-project/**").permitAll()
                .antMatchers("/api/timesheet/**").permitAll()
                .antMatchers("/api/status/**").permitAll()
                .antMatchers("/api/product/**").hasAnyRole(RoleEntitesConstants.EMPLOYEE, RoleEntitesConstants.ADMIN)
                .antMatchers("/api/admin/**").hasAnyRole(RoleEntitesConstants.ADMIN)
                .antMatchers("/api/**").authenticated()
//                .antMatchers("/api/employee/get/*").permitAll()
//                .antMatchers("/api/product/**").hasAnyRole(RoleEntitesConstants.EMPLOYEE, RoleEntitesConstants.ADMIN)
//                .antMatchers("/api/admin/**").hasAnyRole(RoleEntitesConstants.ADMIN)
//                .antMatchers("/api/**").authenticated()
                .and()
                .httpBasic()
                .and()
                .apply(securityConfigurerAdapter())
                .and()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedPage("/403");
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
}
