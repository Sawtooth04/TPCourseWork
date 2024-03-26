package com.sawtooth.dao.abstractions;

import java.util.List;

public interface Dao<T> {
    public T get(int id);
    public List<T> get();
}
