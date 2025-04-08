package com.abinesh.fieldgaurd.workers.repository;

import com.abinesh.fieldgaurd.workers.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    Optional<Worker> findById(Long id);
}
