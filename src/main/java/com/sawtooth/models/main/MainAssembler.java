package com.sawtooth.models.main;

import com.sawtooth.controllers.*;
import com.sawtooth.models.login.Login;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NonNullApi
@Component
public class MainAssembler extends RepresentationModelAssemblerSupport<MainResponse, MainResponse> {
    public MainAssembler() {
        super(MainController.class, MainResponse.class);
    }

    @Override
    public MainResponse toModel(MainResponse entity) {
        entity.add(linkTo(methodOn(MainController.class).getRootEndpoints(null)).withSelfRel());
        entity.add(linkTo(methodOn(LoginController.class).login(new Login(null, null), null)).withRel("login"));
        entity.add(linkTo(methodOn(AuthenticationController.class).get(null, null)).withRel("auth-get"));
        entity.add(linkTo(methodOn(DepartmentController.class).getLabels(0, 0, null)).withRel("department-label-get"));
        entity.add(linkTo(methodOn(LocalityController.class).getByLevel(null, null)).withRel("locality-level"));
        return entity;
    }
}
