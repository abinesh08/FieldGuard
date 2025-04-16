package com.abinesh.fieldgaurd.workers.controller;

import com.abinesh.fieldgaurd.workers.dto.WorkerDocumentDTO;
import com.abinesh.fieldgaurd.workers.service.WorkerDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/worker-documents")
@RequiredArgsConstructor
public class WorkerDocumentController {
    @Autowired
    private final WorkerDocumentService documentService;

    @PostMapping
    public WorkerDocumentDTO uploadDocument(
            @RequestPart Long workerId,
            @RequestPart String documentType,
            @RequestPart MultipartFile file
    ) {
        try {
            return documentService.saveWorkerDocument(workerId, documentType, file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload document", e);
        }
    }

    @GetMapping("/{workerId}")
    public List<WorkerDocumentDTO> getWorkerDocuments(@PathVariable Long workerId) {
        return documentService.getDocumentsForWorkers(workerId);
    }
    @PutMapping("/{documentId}")
    public WorkerDocumentDTO updateDocument(
            @PathVariable Long documentId,
            @RequestPart("file") MultipartFile file
    ) {
        return documentService.updateWorkerDocument(documentId, file);
    }
}
