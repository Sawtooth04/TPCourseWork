package com.sawtooth.dao.abstractions;

import com.sawtooth.models.role.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> getByUsername(String username);
}
