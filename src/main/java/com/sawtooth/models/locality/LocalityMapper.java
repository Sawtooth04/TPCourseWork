package com.sawtooth.models.locality;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalityMapper implements RowMapper<Locality> {

    @Override
    public Locality mapRow(ResultSet rs, int rowNum) throws SQLException {
        Locality result = new Locality();
        result.localityID = rs.getInt("localityID");
        result.localityTypeID = rs.getInt("localityTypeID");
        result.parentLocalityID = rs.getInt("parentLocalityID");
        result.name = rs.getString("name");
        return result;
    }
}
