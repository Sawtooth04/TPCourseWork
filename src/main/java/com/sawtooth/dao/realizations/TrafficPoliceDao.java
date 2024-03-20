package com.sawtooth.dao.realizations;

import com.sawtooth.models.trafficpolice.TrafficPoliceLabel;
import com.sawtooth.models.trafficpolice.TrafficPoliceLabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrafficPoliceDao implements com.sawtooth.dao.abstractions.TrafficPoliceDao {
    private final JdbcTemplate template;

    @Autowired
    public TrafficPoliceDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<TrafficPoliceLabel> getLabels(int start, int count) {
        return template.query("SELECT * FROM get_traffic_polices(?, ?)", new TrafficPoliceLabelMapper(), start, count);
    }
}
