package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.repository.UserRepository;
import com.demo.domain.User;
import static java.util.Collections.emptyList;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	
	@Autowired
	private UserRepository userReposiory;
	
	
	
	public UserDetailsServiceImpl(UserRepository userReposiory) {
        this.userReposiory = userReposiory;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User applicationUser = userReposiory.findByEmail(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(applicationUser.getEmail(), applicationUser.getPassword(), emptyList());
    }
	
	
	
	
	
}
