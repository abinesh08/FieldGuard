package com.abinesh.fieldguard.workers.mapper;

import com.abinesh.fieldguard.workers.dto.WorkerDocumentDTO;
import com.abinesh.fieldguard.workers.model.Worker;
import com.abinesh.fieldguard.workers.model.WorkerDocument;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

public class WorkerDocumentMapper {
    public static WorkerDocumentDTO toDTO(WorkerDocument doc){
        return WorkerDocumentDTO.builder()
                .id(doc.getId())
                .documentType(doc.getDocumentType())
                .fileName(doc.getFileName())
                .fileType(doc.getFileType())
                .base64Data(Base64.getEncoder().encodeToString(doc.getDocumentFile()))
                .build();
    }

    public static WorkerDocument toEntity(MultipartFile file, String type, Worker worker) throws IOException {
        try {
            return WorkerDocument.builder()
                    .documentType(type)
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .documentFile(file.getBytes())
                    .worker(worker)
                    .build();
        }catch (Exception e){
            throw new RuntimeException("Error reading file bytes", e);
        }
    }

}
