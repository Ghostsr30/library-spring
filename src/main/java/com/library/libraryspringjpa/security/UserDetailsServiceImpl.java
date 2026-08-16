package com.library.libraryspringjpa.security;

import com.library.libraryspringjpa.entities.User;
import com.library.libraryspringjpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) { //carrega os detalhes do usuário pelo email
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Email not found: " + email);
        }
        return user;
    }
}
