package com.bandaddict.Config;

import com.bandaddict.Filter.JwtAuthenticationFilter;
import com.bandaddict.Handler.AuthenticationFailureHandlerImpl;
import com.bandaddict.Handler.AuthenticationSuccessHandlerImpl;
import com.bandaddict.Service.JwtTokenService;
import com.bandaddict.Service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Security config
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
    private AuthenticationFailureHandlerImpl authenticationFailureHandler;
    private UserService userService;
    private JwtTokenService jwtTokenService;

    /**
     * Constructor
     *
     * @param authenticationSuccessHandler the authenticationSuccessHandler bean
     * @param authenticationFailureHandler the authenticationFailureHandler bean
     * @param userService the userService bean
     * @param jwtTokenService the jwtTokenService bean
     */
    public SecurityConfig(final AuthenticationSuccessHandlerImpl authenticationSuccessHandler, final AuthenticationFailureHandlerImpl authenticationFailureHandler,
                          final UserService userService, final JwtTokenService jwtTokenService){
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected final void configure(final HttpSecurity http) throws Exception {
        http
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/get-user").permitAll()
                .antMatchers(HttpMethod.POST, "/sign-up").permitAll()
                .antMatchers(HttpMethod.PATCH, "/activate").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler).permitAll()
            .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtTokenService, userService))
                .exceptionHandling()
            .and()
                .cors()
            .and()
                .csrf().disable();
    }

    @Override
    public final void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
