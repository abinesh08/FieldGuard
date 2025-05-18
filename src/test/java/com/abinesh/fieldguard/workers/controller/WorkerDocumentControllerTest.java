package com.abinesh.fieldguard.workers.controller;

import com.abinesh.fieldguard.SecurityConfig;
import com.abinesh.fieldguard.workers.dto.WorkerDocumentDTO;
import com.abinesh.fieldguard.workers.service.WorkerDocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkerDocumentController.class)
@Import(SecurityConfig.class)
class WorkerDocumentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkerDocumentService documentService;

    private MockMultipartFile file;
    private WorkerDocumentDTO sampleDTO;

    @BeforeEach
    void setUp() {
        file = new MockMultipartFile(
                "file",
                "doc.pdf",
                "application/pdf",
                "dummy-data".getBytes()
        );

        sampleDTO = WorkerDocumentDTO.builder()
                .id(1L)
                .documentType("ID")
                .fileName("doc.pdf")
                .fileType("application/pdf")
                .base64Data(Base64.getEncoder().encodeToString("dummy-data".getBytes()))
                .build();
    }

    @Test
    void testUploadDocument() throws Exception {
        when(documentService.saveWorkerDocument(any(), any(), any()))
                .thenReturn(sampleDTO);

        MockMultipartFile file = new MockMultipartFile(
                "file", "doc.pdf", "application/pdf", "dummy-data".getBytes()
        );

        MockMultipartFile workerIdPart = new MockMultipartFile(
                "workerId", "", "application/json", "1".getBytes()
        );

        MockMultipartFile documentTypePart = new MockMultipartFile(
                "documentType", "", "text/plain", "ID".getBytes()
        );

        mockMvc.perform(multipart("/worker-documents")
                        .file(file)
                        .file(workerIdPart)
                        .file(documentTypePart)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fileName").value("doc.pdf"))
                .andExpect(jsonPath("$.documentType").value("ID"));
    }
    @Test
    void testGetWorkerDocuments() throws Exception {
        List<WorkerDocumentDTO> mockDocs = List.of(
                WorkerDocumentDTO.builder()
                        .id(1L)
                        .documentType("ID")
                        .fileName("doc1.pdf")
                        .fileType("application/pdf")
                        .base64Data(Base64.getEncoder().encodeToString("doc1".getBytes()))
                        .build(),
                WorkerDocumentDTO.builder()
                        .id(2L)
                        .documentType("ADDRESS")
                        .fileName("doc2.pdf")
                        .fileType("application/pdf")
                        .base64Data(Base64.getEncoder().encodeToString("doc2".getBytes()))
                        .build()
        );
        when(documentService.getDocumentsForWorkers(1L)).thenReturn(mockDocs);

        mockMvc.perform(get("/worker-documents/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].documentType").value("ID"))
                .andExpect(jsonPath("$[1].documentType").value("ADDRESS"));
    }

    @Test
    void testUpdateDocument() throws Exception {
        WorkerDocumentDTO updatedDTO = WorkerDocumentDTO.builder()
                .id(1L)
                .documentType("ID")
                .fileName("updated.pdf")
                .fileType("application/pdf")
                .base64Data(Base64.getEncoder().encodeToString("updated-content".getBytes()))
                .build();

        MockMultipartFile updatedFile = new MockMultipartFile(
                "file", "updated.pdf", "application/pdf", "updated-content".getBytes()
        );

        when(documentService.updateWorkerDocument(eq(1L), any(MultipartFile.class)))
                .thenReturn(updatedDTO);

        mockMvc.perform(multipart("/worker-documents/1")
                        .file(updatedFile)
                        .with(request -> {
                            request.setMethod("PUT"); // Override to PUT
                            return request;
                        }))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fileName").value("updated.pdf"))
                .andExpect(jsonPath("$.documentType").value("ID"));
    }

//    @Test
//    void testUploadDocumentThrowsIOException() throws Exception {
//        MockMultipartFile mockFile = new MockMultipartFile(
//                "file", "test.pdf", "application/pdf", "file content".getBytes());
//
//        // Simulate service throwing IOException
//        when(documentService.saveWorkerDocument(eq(1L), eq("ID"), any(MultipartFile.class)))
//                .thenThrow(new IOException("Simulated IO error"));
//
//        mockMvc.perform(multipart("/worker-documents")
//                        .file(mockFile)
//                        .param("workerId", "1")
//                        .param("documentType", "ID")
//                        .contentType(MediaType.MULTIPART_FORM_DATA))
//                .andExpect(status().isInternalServerError())
//                .andExpect(content().string("Failed to upload document"));
//    }
}