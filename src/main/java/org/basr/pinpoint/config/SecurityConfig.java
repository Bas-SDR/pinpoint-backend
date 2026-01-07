package org.basr.pinpoint.config;

import org.basr.pinpoint.repository.UserRepository;
import org.basr.pinpoint.security.JwtRequestFilter;
import org.basr.pinpoint.security.JwtService;
import org.basr.pinpoint.security.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service, UserRepository userRepos) {
        this.jwtService = service;
        this.userRepository = userRepos;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        return authentication -> {
            UserDetails user = userDetailsService.loadUserByUsername(authentication.getName());
            if (!passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
                throw new RuntimeException("Invalid credentials");
            }
            return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CorsConfigurationSource corsConfig) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/games").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/games/batch").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/games/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/games/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/games/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/teams").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/teams/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/teams/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/teams/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/leagues").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/leagues/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/leagues/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/leagues/*").hasRole("ADMIN")
                        .requestMatchers("/roles").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                        .requestMatchers("/players/**").permitAll()
                        .requestMatchers("/users/**").authenticated()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/games/**").permitAll()
                        .requestMatchers("/teams/**").permitAll()
                        .requestMatchers("/leagues/**").permitAll()
                        .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfig))
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}