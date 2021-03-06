package de.dlh.lhind.annualleave.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtConfig jwtConfig;
    @Autowired
    public WebSecurityConfig(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.exceptionHandling()
                // .authenticationEntryPoint(JwtAuthenticationEntryPoint())
                .accessDeniedPage(PATH);*/
        http
                // remove csrf and state in session because in jwt we do not need them
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // .and()
                // .logout()
                // .permitAll()
                // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // .logoutSuccessUrl("/static/login.html")
                // .invalidateHttpSession(true)
                // .deleteCookies("JSESSIONID")
                .and()
                // H2 console in Spring Boot show a blank screen after logging in?
                .headers().frameOptions().disable()
                .and()
                // .headers().frameOptions().sameOrigin()
                // add jwt filters (1. authentication, 2. authorization)
                .addFilter(new JWTAuthenticationFilter(authenticationManagerBean(), jwtConfig))
                .addFilterAfter(new JWTAuthorizationFilter(jwtConfig), JWTAuthenticationFilter.class)
                .authorizeRequests()
                // configure access rules
                .antMatchers(HttpMethod.POST, jwtConfig.getResetPassword()).permitAll()
                .antMatchers(HttpMethod.POST, jwtConfig.getSignIn()).permitAll()
                .antMatchers(HttpMethod.POST, jwtConfig.getCreateUser()).permitAll()
                .antMatchers(jwtConfig.getH2Console()).permitAll()
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
