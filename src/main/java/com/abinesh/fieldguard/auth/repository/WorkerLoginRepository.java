package com.abinesh.fieldguard.auth.repository;

import com.abinesh.fieldguard.auth.model.WorkerLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerLoginRepository extends JpaRepository<WorkerLogin, Long> {
    Optional<WorkerLogin> findByPhoneNumber(Long phoneNumber);

}
