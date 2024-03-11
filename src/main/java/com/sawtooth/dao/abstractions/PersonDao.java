package com.sawtooth.dao.abstractions;

import com.sawtooth.models.person.Person;

public interface PersonDao extends Dao<Person> {
    public Person getByUsername(String username);
}
