package com.sawtooth.controllers;

import com.sawtooth.models.exceptions.AppError;
import com.sawtooth.models.login.Login;
import com.sawtooth.security.JwtUtils;
import com.sawtooth.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(UserService userService, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody Login login) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.username(), login.password()));
        }
        catch (BadCredentialsException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AppError(
                    HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password", new Date()));
        }
        return ResponseEntity.ok(jwtUtils.generateToken(userService.loadUserByUsername(login.username())));
    }
}
