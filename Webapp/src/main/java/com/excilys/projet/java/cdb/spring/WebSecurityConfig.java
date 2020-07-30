package com.excilys.projet.java.cdb.spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.excilys.projet.java.cdb.service.ServiceUser;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private static final String LOGIN = "/login";
	
	@Autowired
	private ServiceUser serviceUser;

	@Autowired
	public void userConfigurationGlobal(AuthenticationManagerBuilder authentificationManagerBuilder)
			throws Exception {
	
		authentificationManagerBuilder.userDetailsService(serviceUser).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/", LOGIN, "/logout").permitAll();

		http.authorizeRequests().antMatchers("/dashboard").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

		http.authorizeRequests().antMatchers("/editComputer", "/addComputer", "/deleteComputer")
				.access("hasRole('ROLE_ADMIN')");

		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		http.authorizeRequests().and().formLogin().loginProcessingUrl("/j_spring_security_check").loginPage(LOGIN)
				.defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").usernameParameter("username")
				.passwordParameter("password").and().logout().logoutUrl("/logout")
				.logoutSuccessUrl(LOGIN);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder() ;
	}

} 