package com.example.dddstudy.config.security;
/*

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.NullRequestCache;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    public static final String AUTHCOOKIENAME = "AUTH";

    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    private DataSource dataSource;


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
                -> securityContextConfig.securityContextRepository(new CookieSecurityContextRepository(userDetailsService()));
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
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
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
                )
                .userDetailsService(); // 7번

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsServiceBean() {
        return
    }


    */
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
*//*

}
*/
