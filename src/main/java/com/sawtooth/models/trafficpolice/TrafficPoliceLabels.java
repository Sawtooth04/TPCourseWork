package com.sawtooth.models.trafficpolice;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class TrafficPoliceLabels extends RepresentationModel<TrafficPoliceLabels> {
    public List<TrafficPoliceLabel> trafficPoliceLabels;
}
