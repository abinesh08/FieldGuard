package com.abinesh.fieldguard.auth.controller;

import com.abinesh.fieldguard.auth.dto.JwtResponse;
import com.abinesh.fieldguard.auth.dto.LoginRequest;
import com.abinesh.fieldguard.auth.dto.SignUpRequest;
import com.abinesh.fieldguard.auth.model.OfficerLogin;
import com.abinesh.fieldguard.auth.model.WorkerLogin;
import com.abinesh.fieldguard.auth.repository.OfficerLoginRepository;
import com.abinesh.fieldguard.auth.repository.WorkerLoginRepository;
import com.abinesh.fieldguard.auth.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired private OfficerLoginRepository officerLoginRepository;
    @Autowired private WorkerLoginRepository workerLoginRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequest req){
        if("OFFICER".equalsIgnoreCase(req.getRole())){
            OfficerLogin officer= OfficerLogin.builder()
                    .name(req.getName())
                    .phoneNumber(req.getPhoneNumber())
                    .password(passwordEncoder.encode(req.getPassword()))
                    .role("ROLE_OFFICER")
                    .build();
            officerLoginRepository.save(officer);
        }else {
            WorkerLogin worker = WorkerLogin.builder()
                    .name(req.getName())
                    .phoneNumber(req.getPhoneNumber())
                    .password(passwordEncoder.encode(req.getPassword()))
                    .role("ROLE_WORKER")
                    .build();
            workerLoginRepository.save(worker);
        }
        return "Signup successful!";
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest req){
        Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                req.getPhoneNumber().toString(),req.getPassword()
        ));
        String token = jwtUtil.generateToken(auth);
        String role= ((UserDetails) auth.getPrincipal()).getAuthorities().iterator().next().getAuthority();
        return new JwtResponse(token, role);
    }
}
