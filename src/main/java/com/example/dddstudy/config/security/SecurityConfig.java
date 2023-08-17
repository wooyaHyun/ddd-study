package com.example.dddstudy.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.NullRequestCache;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    public static final String AUTHCOOKIENAME = "AUTH";

    @Autowired
    private DataSource dataSource;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcDaoImpl jdbcDaoImpl() {
        JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
        jdbcDaoImpl.setDataSource(dataSource);
        jdbcDaoImpl.setUsersByUsernameQuery("select member_id, password, 'true' from member where member_id = ?");
        jdbcDaoImpl.setAuthoritiesByUsernameQuery("select member_id, authority from member_authorities where member_id = ?");

        return jdbcDaoImpl;
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
        return (web) -> web.ignoring()
                .requestMatchers(
                        "/vendor/**",
                        "/api/**",
                        "/images/**",
                        "/favicon.ico"
                );
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.securityContext((securityContextConfig)
                -> securityContextConfig.securityContextRepository(new CookieSecurityContextRepository())
        );

        http.requestCache((requestCacheConfig)
                -> requestCacheConfig.requestCache(new NullRequestCache())
        );

        http
                .csrf((csrfConfig) ->
                        csrfConfig.disable()
                ) // 1번
                .headers((headerConfig) ->
                        headerConfig.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.disable()
                        )
                )// 2번
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/", "/home", "/categories/**", "/products/**").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )// 3번
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/login")
                                .permitAll()
                                .successHandler(new CustomAuthSuccessHandler())
                )
                .logout((logoutConfig) ->
                        logoutConfig
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/loggedOut") // 6번
                                .deleteCookies(AUTHCOOKIENAME)
                                .permitAll()
                );

        return http.build();
    }







/*
    public final AuthenticationEntryPoint unauthorizedEntryPoint =
            (request, response, authException) -> {
                ErrorResponse fail = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Spring security unauthorized...");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };

    public  final AccessDeniedHandler accessDeniedHandler =
            (request, response, accessDeniedException) -> {
                ErrorResponse fail = new ErrorResponse(HttpStatus.FORBIDDEN, "Spring security forbidden...");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };
*/

}

