package com.abinesh.fieldgaurd.officer.Repository;

import com.abinesh.fieldgaurd.officer.model.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficerRepository extends JpaRepository<Officer, Long> {
}
