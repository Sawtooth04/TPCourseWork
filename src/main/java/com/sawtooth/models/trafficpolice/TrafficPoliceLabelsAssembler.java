package com.sawtooth.models.trafficpolice;

import com.sawtooth.controllers.DepartmentController;
import com.sawtooth.controllers.MainController;
import com.sawtooth.models.login.Login;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@NonNullApi
public class TrafficPoliceLabelsAssembler extends RepresentationModelAssemblerSupport<TrafficPoliceLabels, TrafficPoliceLabels> {
    public TrafficPoliceLabelsAssembler() {
        super(DepartmentController.class, TrafficPoliceLabels.class);
    }

    @Override
    public TrafficPoliceLabels toModel(TrafficPoliceLabels entity) {
        entity.add(linkTo(methodOn(MainController.class).getRootEndpoints(null)).withRel("main"));
        entity.add(linkTo(methodOn(DepartmentController.class).getLabels(0, 0, null)).withRel("login"));
        return entity;
    }
}
