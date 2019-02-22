package es.urjc.code.daw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity 
@ComponentScan
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/index").permitAll();
	
        
       /* // Private pages (all other pages)
        http.authorizeRequests().antMatchers("/servicios_cliente").hasAnyRole("CLIENT");
        http.authorizeRequests().antMatchers("/datos_usuario").hasAnyRole("CLIENT", "EMPLOYEE");
        http.authorizeRequests().antMatchers("/editar_usuario").hasAnyRole("CLIENT", "EMPLOYEE");
        http.authorizeRequests().antMatchers("/administracion").hasAnyRole("ADMIN"); */
        
        // Login form
        http.formLogin().loginPage("/index");
        http.formLogin().usernameParameter("email");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/index");
        http.formLogin().failureUrl("/loginerror");
        
        // Logout
        http.logout().logoutUrl("/index");
        http.logout().logoutSuccessUrl("/index"); 
        
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
}