package de.dlh.lhind.annualleave.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static de.dlh.lhind.annualleave.security.SecurityConstants.*;

/**
 * https://www.baeldung.com/spring-security-logout
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.exceptionHandling()
                // .authenticationEntryPoint(JwtAuthenticationEntryPoint())
                .accessDeniedPage(PATH);*/
        http
                .logout()
                .logoutSuccessUrl("/login.html")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        http
                // remove csrf and state in session because in jwt we do not need them
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // H2 console in Spring Boot show a blank screen after logging in?
                .headers().frameOptions().disable()
                .and()
                // .headers().frameOptions().sameOrigin()
                // add jwt filters (1. authentication, 2. authorization)
                .addFilter(new JWTAuthenticationFilter(authenticationManagerBean()))
                .addFilterAfter(new JWTAuthorizationFilter(), JWTAuthenticationFilter.class)
                .authorizeRequests()
                // configure access rules
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.POST, SIGN_IN_URL).permitAll()
                .antMatchers(HttpMethod.POST, CREATE_URL).permitAll()
                .antMatchers(H2_CONSOLE).permitAll()
                /*
                 * Creating a React app with Spring Security
                 * https://guides.grails.org/react-spring-security/guide/index.html
                 */
                .antMatchers(
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/swagger-ui/index.html",
                        "/webjars/**",
                        "/swagger.json",
                        "/swagger-ui/**",
                        "/swagger-resources/**"
                )
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    /*@Bean
    fun authenticationFailureHandler(): AuthenticationFailureHandler {
        return CustomAuthenticationFailureHandler()
    }*/

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    protected BCryptPasswordEncoder passwordEncoder()  {
        return new BCryptPasswordEncoder();
    }
}
