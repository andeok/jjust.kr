package kr.end.backend.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests((auth) -> auth
        .anyRequest().permitAll()

//        .requestMatchers("/swagger", "/swagger-ui.html", "/swagger-ui/**",
//            "/api-docs ", "/api-docs/**", "/v3/api-docs/**").permitAll()
//
//        .requestMatchers("/api/members", "/api/signup", "/login", "/api/reissue").permitAll()
//
//        .anyRequest().authenticated()
    );

    http.csrf(AbstractHttpConfigurer::disable);

    http.formLogin(AbstractHttpConfigurer::disable);

    http.httpBasic(AbstractHttpConfigurer::disable);

//    http.addFilterBefore(new JwtFilter(jwtTokenProvider, redisUtil), LoginFilter.class);
//    http.addFilterBefore(filterExceptionHandler, JwtFilter.class);
//    http.addFilterBefore(filterExceptionHandler, LogoutFilter.class);
//
//    http.addFilterAt(
//        new LoginFilter(authenticationManager(authenticationConfiguration), jwtTokenProvider, redisUtil),
//        UsernamePasswordAuthenticationFilter.class);

    http.sessionManagement(
        (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//    http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

//    http.logout(logout -> logout.logoutUrl("/logout")
//        .addLogoutHandler(customLogoutHandler));

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
