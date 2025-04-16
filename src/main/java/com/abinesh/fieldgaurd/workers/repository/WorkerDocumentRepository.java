package com.abinesh.fieldgaurd.workers.repository;

import com.abinesh.fieldgaurd.workers.model.WorkerDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkerDocumentRepository extends JpaRepository<WorkerDocument, Long> {
    List<WorkerDocument> findByWorkerId(Long workerId);
    Optional<WorkerDocument> findByWorkerIdAndDocumentType(Long workerId, String documentType);
}
