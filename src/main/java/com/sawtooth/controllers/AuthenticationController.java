package com.sawtooth.controllers;

import com.sawtooth.models.authentication.AuthenticationAssembler;
import com.sawtooth.models.authentication.AuthenticationResponse;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @GetMapping("/get")
    @ResponseBody
    public AuthenticationResponse get(Authentication authentication, @Nullable AuthenticationAssembler assembler) {
        AuthenticationResponse response = new AuthenticationResponse();

        if (authentication != null) {
            response.isAuthenticated = true;
            response.username = authentication.getName();
            response.roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        }
        if (assembler != null)
            return assembler.toModel(response);
        return response;
    }
}
