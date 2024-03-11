package com.sawtooth.dao.abstractions;

import java.util.List;

public interface Dao<T> {
    public T Get(int id);
    public List<T> Get();
}
