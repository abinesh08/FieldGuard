package com.abinesh.fieldguard.auth.repository;

import com.abinesh.fieldguard.auth.model.OfficerLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficerLoginRepository extends JpaRepository<OfficerLogin, Long> {
    Optional<OfficerLogin> findByPhoneNumber(Long phoneNumber);
}
