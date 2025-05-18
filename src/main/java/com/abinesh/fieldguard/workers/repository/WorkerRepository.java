package com.abinesh.fieldguard.workers.repository;

import com.abinesh.fieldguard.workers.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    Optional<Worker> findById(Long id);
    List<Worker> findBySubTaluk(String subTaluk);
    List<Worker> findByTaluk(String taluk);
    List<Worker> findByDistrict(String district);
    List<Worker> findByState(String state);

}
