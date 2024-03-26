package com.sawtooth.models.locality;

import com.sawtooth.controllers.*;
import com.sawtooth.models.login.Login;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NonNullApi
public class LocalitiesAssembler extends RepresentationModelAssemblerSupport<Localities, Localities> {
    public LocalitiesAssembler() {
        super(LocalityController.class, Localities.class);
    }

    @Override
    public Localities toModel(Localities entity) {
        entity.add(linkTo(methodOn(MainController.class).getRootEndpoints(null)).withRel("main"));
        entity.add(linkTo(methodOn(LocalityController.class).getByLevel(0, null)).withRel("locality-level"));
        return entity;
    }
}
