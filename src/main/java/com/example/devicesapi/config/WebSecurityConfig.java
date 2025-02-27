package com.example.devicesapi.config;
import com.example.devicesapi.service.UserService;
import com.example.devicesapi.utility.AuthEntryPointJwt;
import com.example.devicesapi.utility.AuthTokenFilter;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class WebSecurityConfig {

    @Value("${jwt.enable}")
    private String ENABLE_JWT;

    @Autowired
    UserService userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                if(!ENABLE_JWT.equals("enabled")){
                    // override and enable all requests
                    http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll());
                }else{
                    // Updated configuration for Spring Security 6.x
                    http
                            .csrf(AbstractHttpConfigurer::disable) // Disable CSRF
                            .cors(AbstractHttpConfigurer::disable) // Disable CORS (or configure if needed)
                            .exceptionHandling(exceptionHandling ->
                                    exceptionHandling.authenticationEntryPoint(unauthorizedHandler)
                            )
                            .sessionManagement(sessionManagement ->
                                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                            )
                            .authorizeHttpRequests(authorizeRequests ->
                                    authorizeRequests
                                            .requestMatchers("/api/auth/**").permitAll()
                                            .anyRequest().authenticated()
                            );

                }

        // Add the JWT Token filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
