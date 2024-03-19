package com.sawtooth.models.authentication;

import org.springframework.hateoas.RepresentationModel;

public class AuthenticationResponse extends RepresentationModel<AuthenticationResponse> {
    public boolean isAuthenticated;
    public String username;
}
