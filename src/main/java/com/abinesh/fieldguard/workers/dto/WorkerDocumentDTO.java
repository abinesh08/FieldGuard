package com.abinesh.fieldguard.workers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDocumentDTO {
    private Long id;
    private String documentType;
    private String fileName;
    private String fileType;
    private String base64Data;
}
