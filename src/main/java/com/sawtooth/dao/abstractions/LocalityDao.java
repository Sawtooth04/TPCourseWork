package com.sawtooth.dao.abstractions;

import com.sawtooth.models.locality.Locality;

import java.util.List;

public interface LocalityDao extends Dao<Locality>{
    public List<Locality> getByLevel(int level);
}
