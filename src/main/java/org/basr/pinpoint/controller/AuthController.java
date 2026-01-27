package org.basr.pinpoint.controller;

import org.basr.pinpoint.dto.AuthDto;
import org.basr.pinpoint.dto.AuthResponseDto;
import org.basr.pinpoint.security.JwtService;
import org.basr.pinpoint.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager man, JwtService service, UserService userService) {
        this.authManager = man;
        this.jwtService = service;
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> signIn(@RequestBody AuthDto authDto) {
        UsernamePasswordAuthenticationToken up =
                new UsernamePasswordAuthenticationToken(authDto.email, authDto.password);

        try {
            Authentication auth = authManager.authenticate(up);

            UserDetails ud = (UserDetails) auth.getPrincipal();

            Long userId = userService.getUserIdByEmail(authDto.getEmail());

            List<String> roles = ud.getAuthorities()
                    .stream()
                    .map(a -> a.getAuthority())
                    .toList();

            String token = jwtService.generateToken(ud, userId, roles);

            AuthResponseDto responseDto = new AuthResponseDto(token, authDto.getEmail(), userId, roles);

            return ResponseEntity.ok().body(responseDto);
        }
        catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}