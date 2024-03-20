package com.sawtooth.models.trafficpolice;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrafficPoliceLabelMapper implements RowMapper<TrafficPoliceLabel> {
    @Override
    public TrafficPoliceLabel mapRow(ResultSet rs, int rowNum) throws SQLException {
        TrafficPoliceLabel label = new TrafficPoliceLabel();

        label.trafficPoliceID = rs.getInt("trafficPoliceID");
        label.locality = rs.getString("locality");
        label.address = rs.getString("address");
        label.employeesCount = rs.getInt("employeesCount");
        label.inspectionsCount = rs.getInt("inspectionsCount");
        return label;
    }
}
