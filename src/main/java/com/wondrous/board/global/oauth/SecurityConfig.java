package com.wondrous.board.global.oauth;


import com.wondrous.board.domain.member.entity.Role;
import com.wondrous.board.domain.member.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.DispatcherServlet;


@RequiredArgsConstructor

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http, DispatcherServlet dispatcherServlet) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/css"),
                                new AntPathRequestMatcher("/js/**"),
                                new AntPathRequestMatcher("/h2/console"),
                                new AntPathRequestMatcher("/profile")
                        ).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/**")).hasRole(Role.USER.name())
                        .anyRequest().authenticated())
                //이후 유저 엔드 포인트 설정
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(customOAuth2UserService)))
                .logout((logout) -> logout
                        .logoutSuccessUrl("/"));

        return http.build();
    }
}
