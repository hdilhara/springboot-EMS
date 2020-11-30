package com.okta.AuthorizationServerApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${user.oauth.user.username}")
    private String username;
    @Value("${user.oauth.user.password}")
    private String password;

    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
            .antMatchers("/login", "/oauth/authorize", "/logout/me")
            .and()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin().permitAll();
//            .and()
//            .logout(l -> l
//                    .logoutSuccessUrl("/").permitAll());
//            .logout()
//            .logoutUrl("/login?logout")
//            .permitAll()
//            .logoutSuccessUrl("/login")
//            .invalidateHttpSession(true)
//            .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        	.withUser("admin")
        	.password(passwordEncoder().encode("admin"))
        	.roles("ADMIN")
        	.and()
            .withUser(username)
            .password(passwordEncoder().encode(password))
            .roles("USER");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
