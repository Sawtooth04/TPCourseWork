package com.sawtooth.security;

import com.sawtooth.dao.abstractions.PersonDao;
import com.sawtooth.dao.abstractions.RoleDao;
import com.sawtooth.models.person.Person;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public final class UserService implements UserDetailsService {
    private final PersonDao personDao;
    private final RoleDao roleDao;

    public UserService(PersonDao personDao, RoleDao roleDao) {
        this.personDao = personDao;
        this.roleDao = roleDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Person person = personDao.getByUsername(username);
            List<GrantedAuthority> authorities = roleDao.getByUsername(username).stream().map(r -> new SimpleGrantedAuthority(r.name()))
                    .collect(Collectors.toList());
            return new User(person.name(), person.passwordHash(), authorities);
        }
        catch (EmptyResultDataAccessException emptyException) {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    public void createNewUser(User user) {

    }
}
