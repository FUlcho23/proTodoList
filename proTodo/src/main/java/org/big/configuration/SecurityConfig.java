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
		 http
		    .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
		    .csrf(csrf -> csrf.disable())
		    .logout(logout -> logout
		        .logoutUrl("/logout")  // 로그아웃 경로
		        .logoutSuccessUrl("/home")  // 로그아웃 성공 후 이동
		        .invalidateHttpSession(true)  // 세션 무효화
		        .deleteCookies("JSESSIONID")  // JSESSIONID 쿠키 삭제
		    )
		    .sessionManagement(session -> session
		        .invalidSessionUrl("/session-expired")  // 세션 만료 시 이동할 페이지
		    );
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

}
