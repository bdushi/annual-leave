package de.dlh.lhind.annualleave.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static de.dlh.lhind.annualleave.common.Common.PATH;

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
        http.exceptionHandling()
                .accessDeniedPage(PATH)
                .and()
                // remove csrf and state in session because in jwt we do not need them
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // .and()
                // .logout()
                // .permitAll()
                // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // .logoutSuccessUrl("/static/login.html")
                // .invalidateHttpSession(true)
                // .deleteCookies("JSESSIONID")
                //Fixed cors
                .cors()
                .and()
                // H2 console in Spring Boot show a blank screen after logging in?
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests(configure ->
                        // configure access rules
                        configure.antMatchers(
                                jwtConfig.getResetPassword(),
                                jwtConfig.getSignIn(),
                                jwtConfig.getCreateUser(),
                                jwtConfig.getH2Console(),
                                jwtConfig.getSignOut(),
                                "/v2/api-docs",
                                "/configuration/ui",
                                "/swagger-resources",
                                "/configuration/security",
                                "/swagger-ui.html",
                                "/swagger-ui/index.html",
                                "/webjars/**",
                                "/swagger.json",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                // Web App
                                "/",
                                "/index.html",
                                "/global.css",
                                "/favicon.png",
                                "/favicon.ico",
                                "/**/assets/**",
                                "/**/*.png",
                                "/**/*.gif",
                                "/**/*.svg",
                                "/**/*.jpg",
                                "/**/*.html",
                                "/**/*.css",
                                "/**/*.map",
                                "/**/*.js",
                                "/build/bundle.js",
                                "/build/bundle.css"
                        )
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
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
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
