package com.hoangvinh.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.hoangvinh.springboot.admin.service.impl.UserLoginService;

@SpringBootApplication
public class AgricultureWebApplication extends WebSecurityConfigurerAdapter {

	@Autowired
	UserLoginService userLoginService;
	
	public static void main(String[] args) {
		SpringApplication.run(AgricultureWebApplication.class, args);
	}

	// simple authenticated.
	// only /admin/** must login and any request is allowed
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable().authorizeRequests() .antMatchers("/").permitAll()
	 * .antMatchers("/admin/**").authenticated() .and().httpBasic(); }
	 * 
	 * @Override public void configure(WebSecurity web) throws Exception { not
	 * authenticate static resources
	 * web.ignoring().antMatchers("/client/css/**", "/client/js/**",
	 * "/client/font/**", "/client/images/**"); }
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder auth)
	 * throws Exception { hardcode username=admin && password=123
	 * auth.inMemoryAuthentication().withUser("admin").password("123").roles(
	 * "ADMIN"); }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.and().formLogin().loginPage("/login").defaultSuccessUrl("/admin/list-category").failureUrl("/login?e=error").permitAll()
			.and().logout().permitAll()
			.and().exceptionHandling().accessDeniedPage("/login?e=deny");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/client/css/**", "/client/js/**", "/client/font/**", "/client/images/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN");
		auth.userDetailsService(userLoginService);
	}
}
