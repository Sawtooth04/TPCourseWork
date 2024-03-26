package com.sawtooth.dao.realizations;

import com.sawtooth.models.locality.Locality;
import com.sawtooth.models.locality.LocalityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalityDao implements com.sawtooth.dao.abstractions.LocalityDao {
    private final JdbcTemplate template;

    @Autowired
    public LocalityDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Locality get(int id) {
        return null;
    }

    @Override
    public List<Locality> get() {
        return null;
    }

    @Override
    public List<Locality> getByLevel(int level) {
        return template.query("SELECT * FROM get_localities_by_level(?)", new LocalityMapper(), level);
    }
}
