package com.sawtooth.dao.abstractions;

import com.sawtooth.models.trafficpolice.TrafficPoliceLabel;

import java.util.List;

public interface TrafficPoliceDao {
    public List<TrafficPoliceLabel> getLabels(int start, int count);
}
