package com.sawtooth.dao.realizations;

import com.sawtooth.models.person.Person;
import com.sawtooth.models.person.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public final class PersonDao implements com.sawtooth.dao.abstractions.PersonDao {
    private final JdbcTemplate template;

    @Autowired
    public PersonDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Person Get(int id) {
        return null;
    }

    @Override
    public List<Person> Get() {
        return null;
    }

    @Override
    public Person getByUsername(String username) {
        return template.queryForObject("SELECT * FROM get_person_by_name(?)", new PersonMapper(), username);
    }
}
