package com.sawtooth.models.authentication;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class AuthenticationResponse extends RepresentationModel<AuthenticationResponse> {
    public boolean isAuthenticated;
    public String username;
    public List<String> roles;
}
