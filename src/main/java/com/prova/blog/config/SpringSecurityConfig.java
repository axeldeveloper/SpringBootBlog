package com.prova.blog.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//import com.prova.blog.service.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //http.csrf().disable()
        	
        			
        http
        		.authorizeRequests()
        			//.antMatchers("/resources/**").permitAll()
        			//.antMatchers("/bootstrap/**", "/css/**",  "/js/**","/img/**", "/webjars/**", "**/favicon.ico", "/login").permitAll()               	
        			// .antMatchers("/bootstrap/**").permitAll()
        			// .antMatchers("/resources/static/**").permitAll()
                	.antMatchers("/").permitAll()
					.antMatchers("/home").permitAll()
					.antMatchers("/about").permitAll()
					.antMatchers("/admin/**").hasAnyRole("ADMIN")
					.antMatchers("/Users/**").hasAnyRole("USER")
					.antMatchers("/Posts/**").hasAnyRole("USER")
					.anyRequest()
					.authenticated()				
                .and()
                	.csrf()
                	.disable()
                	.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/", true)					 
					.failureUrl("/login?error=true")
					.permitAll()
				.and()
                	.logout()
                	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    				.logoutSuccessUrl("/")
					.permitAll()
				
	            //and()
	            //	.headers()
                //	.frameOptions().sameOrigin()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        //.antMatchers("/resources/**");
        .antMatchers("/resources/**", "/static/**", "/bootstrap/**", "/css/**",  "/js/**","/img/**", "/webjars/**", "**/favicon.ico");
        
       
    }
    
    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	//auth.userDetailsService(UserService);
        auth.inMemoryAuthentication()
                .withUser("axel").password("123").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");
    }
}
