package br.com.serratec.src.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static final String[] AUTH_WHITELIST = { "v3/api-docs/**", "/swagger-ui/**", "/user/**", "/user/create",
			"/actuator/**" };

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> authorizeRequests.antMatchers(AUTH_WHITELIST).permitAll()
				.anyRequest().authenticated()).httpBasic(withDefaults());
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> cors.disable());

		return http.build();
	}
}
