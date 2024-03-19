package com.sawtooth.models.authentication;

import com.sawtooth.controllers.AuthenticationController;
import com.sawtooth.controllers.MainController;
import com.sawtooth.models.login.Login;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NonNullApi
@Component
public class AuthenticationAssembler extends RepresentationModelAssemblerSupport<AuthenticationResponse, AuthenticationResponse> {
    public AuthenticationAssembler() {
        super(AuthenticationController.class, AuthenticationResponse.class);
    }

    @Override
    public AuthenticationResponse toModel(AuthenticationResponse entity) {
        entity.add(linkTo(methodOn(MainController.class).getRootEndpoints(null)).withRel("main"));
        entity.add(linkTo(methodOn(AuthenticationController.class).get(null, null)).withSelfRel());
        return entity;
    }
}
