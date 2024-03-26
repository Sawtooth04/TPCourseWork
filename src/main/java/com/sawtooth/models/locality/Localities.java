package com.sawtooth.models.locality;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class Localities extends RepresentationModel<Localities> {
    public List<Locality> localities;
}
