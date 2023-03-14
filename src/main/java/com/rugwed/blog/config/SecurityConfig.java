package com.rugwed.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rugwed.blog.security.CustomUserDetailService;
import com.rugwed.blog.security.JwtAuthEntryPoint;
import com.rugwed.blog.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	public static final String [] PUBLIC_URLS= {
			"/api/auth/**",
			"/v3/api-docs",
			"/v2/api-docs",
			"/swagger-ui/**",
			"/swagger-resources/**",
			"/webjars/**"
	};
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Autowired
	private JwtAuthEntryPoint jwtAuthEntryPoint;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			 .csrf()
			 .disable()
			 .authorizeHttpRequests()
		     .requestMatchers(PUBLIC_URLS).permitAll()
			 .requestMatchers(HttpMethod.GET).permitAll()
			 .anyRequest()
			 .authenticated()
			 .and ()
			 .exceptionHandling().authenticationEntryPoint(this.jwtAuthEntryPoint)
			 .and()
			 .sessionManagement()
			 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			//.httpBasic();

		 http.addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);

		 http.authenticationProvider(daoAuthenticationProvider());

		DefaultSecurityFilterChain build = http.build();
		return build;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.customUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
}
