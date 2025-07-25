package kr.end.backend.global.config;

import jakarta.validation.Validator;
import java.util.List;
import kr.end.backend.auth.controller.cookie.CookieProvider;
import kr.end.backend.auth.filter.LoginFilter;
import kr.end.backend.auth.service.CustomUserDetailsService;
import kr.end.backend.auth.service.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final Validator validator;
    private final CustomUserDetailsService authService;
    private final JwtProvider jwtProvider;
    private final CookieProvider cookieProvider;

    @Bean
    public AbstractAuthenticationProcessingFilter loginFilter() {
        AbstractAuthenticationProcessingFilter loginFilter = new LoginFilter(jwtProvider,
            cookieProvider, validator);
        loginFilter.setAuthenticationManager(authenticationManager());
//    loginFilter.setAuthenticationSuccessHandler(jsonLoginSuccessHandler());
//    loginFilter.setAuthenticationFailureHandler(jsonLoginFailureHandler());
        return loginFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(
            authService); // InMemoryUserDetailsManager 대신 AuthService 추가
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authenticationProvider);
    }


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
        http.logout(AbstractHttpConfigurer::disable);

        http.httpBasic(AbstractHttpConfigurer::disable);

        http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);

//    http.addFilterBefore(new JwtFilter(jwtTokenProvider, redisUtil), LoginFilter.class);
//    http.addFilterBefore(filterExceptionHandler, JwtFilter.class);
//    http.addFilterBefore(filterExceptionHandler, LogoutFilter.class);
//
//    http.addFilterAt(
//        new LoginFilter(authenticationManager(authenticationConfiguration), jwtTokenProvider, redisUtil),
//        UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement(
            (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*")); // 모든 도메인 허용
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // 모든 메소드 허용
        configuration.setAllowedHeaders(List.of("*")); // 모든 헤더 허용
        configuration.setAllowCredentials(true); // 자격 증명 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 대해 적용
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
