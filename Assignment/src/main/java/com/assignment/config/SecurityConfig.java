package com.assignment.config;

/**
 * @author magantilavakumar
 *
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter  {


	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encode  = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication()
		.withUser("Admin")
		.password(encode.encode("Admin"))
		.roles("ADMIN")
		.and()
		.withUser("Customer")
		.password(encode.encode("Password"))
		.roles("USER");
		
	}
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
        .antMatchers("/private/**")
        .authenticated()
        .antMatchers("/public/**")
        .permitAll()
        .antMatchers("/h2-console/**")
        .permitAll()
        .and()
        .httpBasic();
//		httpSecurity.authorizeRequests()
//		.antMatchers("/**")
//        .permitAll()
//        .anyRequest()
//        .authenticated()
//        .and()
//        .formLogin();
       
      httpSecurity.csrf()
        .ignoringAntMatchers("/**");
      httpSecurity.headers()
        .frameOptions()
        .sameOrigin();
		
		
	}
}
