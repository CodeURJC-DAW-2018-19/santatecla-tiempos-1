package es.urjc.code.daw.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception {

 // User
 auth.inMemoryAuthentication()
 .withUser("user").password("pass").roles("USER");
 }
}