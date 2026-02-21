package org.self.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
	
	private final UserDetailsService userDetailsService;
   
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	  public AuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	      provider.setUserDetailsService(userDetailsService);
	      provider.setPasswordEncoder(passwordEncoder());   
	      return provider;
	  }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:5173"));
		configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
