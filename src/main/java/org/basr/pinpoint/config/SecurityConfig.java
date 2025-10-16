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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/logout").permitAll()
                        .requestMatchers("/roles").hasRole("ADMIN")
                        .requestMatchers("/users").authenticated()
                        .requestMatchers("/players").authenticated()
                        .requestMatchers("/**").permitAll()
                        .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}