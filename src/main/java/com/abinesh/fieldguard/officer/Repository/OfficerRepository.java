package com.abinesh.fieldguard.officer.Repository;

import com.abinesh.fieldguard.officer.model.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficerRepository extends JpaRepository<Officer, Long> {
}
