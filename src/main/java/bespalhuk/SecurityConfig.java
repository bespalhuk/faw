package bespalhuk;

import bespalhuk.infra.auth.UnauthenticatedSessionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		auth.userDetailsService(userDetailsService).passwordEncoder(new ShaPasswordEncoder(512));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.exceptionHandling()
				.authenticationEntryPoint(unauthenticatedSessionEntryPoint());
		http.headers()
				.contentTypeOptions()
				.xssProtection()
				.httpStrictTransportSecurity()
				.frameOptions();
		http.authorizeRequests()
				.antMatchers("/login.faces").permitAll()
				.anyRequest().authenticated().and()
				.formLogin().usernameParameter("username").passwordParameter("password")
				.loginProcessingUrl("/j_spring_security_check")
				.loginPage("/login.faces").defaultSuccessUrl("/").permitAll();
		http.logout()
				.logoutUrl("/j_spring_security_logout")
				.logoutSuccessUrl("/");
	}

	@Bean
	public LoginUrlAuthenticationEntryPoint unauthenticatedSessionEntryPoint() {
		return new UnauthenticatedSessionEntryPoint();
	}

}
