package com.sawtooth.security;

import com.sawtooth.dao.abstractions.PersonDao;
import com.sawtooth.models.person.Person;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public final class UserService implements UserDetailsService {
    private final PersonDao personDao;

    public UserService(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Person person = personDao.getByUsername(username);
            return new User(person.name(), person.passwordHash(), Collections.emptyList());
        }
        catch (EmptyResultDataAccessException emptyException) {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    public void createNewUser(User user) {

    }
}
