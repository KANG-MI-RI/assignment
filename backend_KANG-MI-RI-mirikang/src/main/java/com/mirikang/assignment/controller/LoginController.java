package com.mirikang.assignment.controller;

import com.mirikang.assignment.security.JwtTokenProvider;
import com.mirikang.assignment.security.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody Token.Request request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getId(),
                        request.getSecret()));

        String token = JwtTokenProvider.generateToken(authentication);

        Token.Response response = Token.Response.builder().token(token).build();

        return ResponseEntity.ok(response);
    }
}
