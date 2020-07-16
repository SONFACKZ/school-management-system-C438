package com.sms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("teacher").password(get().encode("teacherpass")).authorities("ROLE_T_STAFF")
		.and()
		.withUser("student").password(get().encode("studentpass")).authorities("ROLE_STUD")
		.and()
		.withUser("principal").password(get().encode("adminpass")).authorities("ROLE_T_ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
		.antMatchers("/sms/ac/ad/**", "/sms/academic/admin/**").hasAnyRole("T_ADMIN") //school administrators
		.antMatchers("/sms/ac/ts/**", "/sms/academic/teachers/**").hasAnyRole("T_STAFF") //teaching staff
		.antMatchers("/sms/ac/st/**", "/sms/academic/student/**").hasAnyRole("STUD") //students
		.antMatchers("/sms/nts/**").hasAnyRole("NT_STAFF") //non teaching staff
		.antMatchers("/sms/sc/**").authenticated()
		.anyRequest().permitAll()
		.and().formLogin(lgp ->
							lgp
								.loginPage("/sms/login")
								.loginProcessingUrl("/auth/signin")
								.defaultSuccessUrl("/sms/welcome")
						);
	}

	
	@Bean
	public PasswordEncoder get() {
		return new BCryptPasswordEncoder();
	}
	
}
