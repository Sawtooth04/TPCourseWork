package com.sawtooth.dao.realizations;

import com.sawtooth.models.role.Role;
import com.sawtooth.models.role.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDao implements com.sawtooth.dao.abstractions.RoleDao {
    private final JdbcTemplate template;

    @Autowired
    public RoleDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Role> getByUsername(String username) {
        return template.query("SELECT * FROM get_person_roles_by_name(?)", new RoleMapper(), username);
    }
}
