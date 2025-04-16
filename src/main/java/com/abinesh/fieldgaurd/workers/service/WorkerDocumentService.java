package com.abinesh.fieldgaurd.workers.service;

import com.abinesh.fieldgaurd.workers.dto.WorkerDocumentDTO;
import com.abinesh.fieldgaurd.workers.mapper.WorkerDocumentMapper;
import com.abinesh.fieldgaurd.workers.model.Worker;
import com.abinesh.fieldgaurd.workers.model.WorkerDocument;
import com.abinesh.fieldgaurd.workers.repository.WorkerDocumentRepository;
import com.abinesh.fieldgaurd.workers.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkerDocumentService {
    @Autowired
    private final WorkerRepository workerRepository;
    private final WorkerDocumentRepository documentRepository;
    public WorkerDocumentDTO saveWorkerDocument(Long workerId, String documentType, MultipartFile file) throws IOException {
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        WorkerDocument doc= WorkerDocumentMapper.toEntity(file, documentType, worker);
        WorkerDocument saved = documentRepository.save(doc);
        return WorkerDocumentMapper.toDTO(saved);
    }

    public List<WorkerDocumentDTO> getDocumentsForWorkers(Long workerId){
        List<WorkerDocument> documents= documentRepository.findByWorkerId(workerId);
        return documents.stream()
                .map(WorkerDocumentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public WorkerDocumentDTO updateWorkerDocument(Long documentId, MultipartFile file) {
        WorkerDocument document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        try {
            document.setDocumentFile(file.getBytes());
            document.setFileName(file.getOriginalFilename());
            document.setFileType(file.getContentType());
        } catch (IOException e) {
            throw new RuntimeException("Error updating document", e);
        }

        WorkerDocument updated = documentRepository.save(document);
        return WorkerDocumentMapper.toDTO(updated);
    }

}
