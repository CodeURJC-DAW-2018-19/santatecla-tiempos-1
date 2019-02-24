package es.urjc.code.daw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationProviderUser authenticationProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {


        //         PUBLIC VIEWS         //
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/sign_in").permitAll();
        http.authorizeRequests().antMatchers("/events").permitAll();
        http.authorizeRequests().antMatchers("/periods").permitAll();
        http.authorizeRequests().antMatchers("/categories").permitAll();
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("name");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/index");
        http.formLogin().failureUrl("/login");
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");

        //         PRIVATE VIEWS            //

        http.authorizeRequests().antMatchers("/index").hasAnyRole("TEACHER", "STUDENT", "VISITOR");
        http.authorizeRequests().antMatchers("/deleteEvents/**").hasAnyRole("TEACHER");
        http.authorizeRequests().antMatchers("/deleteCategory/**").hasAnyRole("TEACHER");

        http.authorizeRequests().antMatchers("/TeacherEventView/**").hasAnyRole("TEACHER");
        http.authorizeRequests().antMatchers("/StudentEventView/**").hasAnyRole("STUDENT");


    }

    @Override
    public void configure(AuthenticationManagerBuilder authentication) throws Exception {
        authentication.authenticationProvider(authenticationProvider);
    }
}