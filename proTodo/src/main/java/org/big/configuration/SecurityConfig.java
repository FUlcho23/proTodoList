package org.big.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
 
	 @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
	    http.csrf().disable();
	    return http.build();
	  }
 
 @Bean
 public WebSecurityCustomizer webSecurityCustomizer() {
     return (web) -> web.ignoring().requestMatchers("/board/*", "/api/board/*","/error", "/error/*", "/img/**", "/favicon.ico");
 }
 @Bean
 public HttpFirewall defaultHttpFirewall() {
    return new DefaultHttpFirewall();
 }
<<<<<<< HEAD
 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     return http
         .headers(headers -> headers
             .contentSecurityPolicy(csp -> csp.policyDirectives("style-src 'self' 'unsafe-inline'"))
         )
         .build();
 }
=======
>>>>>>> refs/remotes/origin/master
}
