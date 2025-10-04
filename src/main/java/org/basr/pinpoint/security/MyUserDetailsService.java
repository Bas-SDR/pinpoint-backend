package org.basr.pinpoint.security;

import org.basr.pinpoint.model.User;
import org.basr.pinpoint.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepos;

    public MyUserDetailsService(UserRepository repos) {
        this.userRepos = repos;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepos.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        return new MyUserDetails(user);
    }
}
