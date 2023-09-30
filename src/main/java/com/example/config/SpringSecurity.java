package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("/register/**").permitAll()
						.requestMatchers("/author/list").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/category/list").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/publisher/list").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/member/list").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/book/list").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/api/**").permitAll()
						.requestMatchers("/issuebook/**").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/book/**").hasRole("ADMIN")
						.requestMatchers("/user/**").hasRole("ADMIN")
						.requestMatchers("/author/**").hasRole("ADMIN")
						.requestMatchers("/category/**").hasRole("ADMIN")
						.requestMatchers("/publisher/**").hasRole("ADMIN")
						.requestMatchers("/member/**").hasRole("ADMIN")
						)
			
				.formLogin(form -> form
						.loginPage("/login")
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/", true)
						.permitAll())
				.logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.permitAll());
		return http.build();
	
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		// Set of login unnecessary page
		return (web) -> web.ignoring().requestMatchers("/webjars/**").requestMatchers("/css/**")
				.requestMatchers("/images/**").requestMatchers("/custom/**").requestMatchers("/vendors/**");
	}
}