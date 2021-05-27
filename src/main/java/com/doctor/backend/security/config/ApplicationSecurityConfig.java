package com.doctor.backend.security.config;


import com.doctor.backend.security.jwt.JwtConfig;
import com.doctor.backend.security.jwt.JwtTokenVerifier;
import com.doctor.backend.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.doctor.backend.security.jwt.JwtUtils;
import com.doctor.backend.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.crypto.SecretKey;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;


    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     UserService userService,
                                     SecretKey secretKey,
                                     JwtUtils jwtUtils,
                                     JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.secretKey = secretKey;

        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**", "api/auth/**").permitAll()
                .anyRequest()
                .authenticated();

        var authenticationFilter = new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey);
        authenticationFilter.setFilterProcessesUrl("/auth/login");

        http.addFilter(authenticationFilter)
                .addFilterAfter(new JwtTokenVerifier(jwtUtils, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE").exposedHeaders("PatientId","Authorization");
            }
        };
    }

}
