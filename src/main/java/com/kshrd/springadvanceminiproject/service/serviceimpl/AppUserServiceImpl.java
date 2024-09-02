package com.kshrd.springadvanceminiproject.service.serviceimpl;

import com.kshrd.springadvanceminiproject.repository.AppUserRepository;
import com.kshrd.springadvanceminiproject.service.AppUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
