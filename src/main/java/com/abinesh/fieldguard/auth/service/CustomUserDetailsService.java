package com.abinesh.fieldguard.auth.service;

import com.abinesh.fieldguard.auth.model.OfficerLogin;
import com.abinesh.fieldguard.auth.model.WorkerLogin;
import com.abinesh.fieldguard.auth.repository.OfficerLoginRepository;
import com.abinesh.fieldguard.auth.repository.WorkerLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private final WorkerLoginRepository workerLoginRepository;
    @Autowired
    private final OfficerLoginRepository officerLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        try {
            Long phoneNumber = Long.parseLong(phone);
            Optional<OfficerLogin> officer = officerLoginRepository.findByPhoneNumber(phoneNumber);
            if (officer.isPresent()) {
                return new User(phone, officer.get().getPassword(), List.of(new SimpleGrantedAuthority("ROLE_OFFICER")));
            } else {
                Optional<WorkerLogin> worker = workerLoginRepository.findByPhoneNumber(phoneNumber);
                if (worker.isPresent()) {
                    return new User(phone, worker.get().getPassword(), List.of(new SimpleGrantedAuthority("ROLE_WORKER")));
                }
            }
            throw new UsernameNotFoundException("Username not found with phoneNumber: " + phone);
        } catch (Exception e) {
            System.out.println("Exception in loadUserByUsername: " + e.getMessage());
            e.printStackTrace(); // Log the full error
            throw new UsernameNotFoundException("Failed to load user: " + phone); // always throw this, not the raw exception
        }
    }
}
