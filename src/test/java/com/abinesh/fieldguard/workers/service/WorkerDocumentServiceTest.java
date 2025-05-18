package com.abinesh.fieldguard.workers.service;

import com.abinesh.fieldguard.workers.dto.WorkerDocumentDTO;
import com.abinesh.fieldguard.workers.model.Worker;
import com.abinesh.fieldguard.workers.model.WorkerDocument;
import com.abinesh.fieldguard.workers.repository.WorkerDocumentRepository;
import com.abinesh.fieldguard.workers.repository.WorkerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WorkerDocumentServiceTest {
    @Mock
    private WorkerRepository workerRepository;

    @Mock
    private WorkerDocumentRepository documentRepository;

    @Mock
    private MultipartFile file;

    @InjectMocks
    private WorkerDocumentService service;

    private Worker mockWorker;
    private WorkerDocument mockDocument;
    private byte[] fileBytes;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        fileBytes = "dummy data".getBytes();

        mockWorker = Worker.builder()
                .id(1L)
                .name("Abinesh")
                .build();

        mockDocument = WorkerDocument.builder()
                .id(101L)
                .documentType("ID")
                .fileName("doc.pdf")
                .fileType("application/pdf")
                .documentFile(fileBytes)
                .worker(mockWorker)
                .build();

        when(file.getOriginalFilename()).thenReturn("doc.pdf");
        when(file.getContentType()).thenReturn("application/pdf");
        when(file.getBytes()).thenReturn(fileBytes);
    }

    @Test
    void testSaveWorkerDocument_Success() throws Exception {
        when(workerRepository.findById(1L)).thenReturn(Optional.of(mockWorker));
        when(documentRepository.save(any(WorkerDocument.class))).thenReturn(mockDocument);

        WorkerDocumentDTO dto = service.saveWorkerDocument(1L, "ID", file);

        assertNotNull(dto);
        assertEquals("ID", dto.getDocumentType());
        assertEquals("doc.pdf", dto.getFileName());
        assertEquals("application/pdf", dto.getFileType());
    }

    @Test
    void testSaveWorkerDocument_WorkerNotFound() {
        when(workerRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.saveWorkerDocument(2L, "ID", file);
        });

        assertEquals("Worker not found", ex.getMessage());
    }
    @Test
    void testGetDocumentsForWorkers() {
        when(documentRepository.findByWorkerId(1L)).thenReturn(List.of(mockDocument));

        List<WorkerDocumentDTO> list = service.getDocumentsForWorkers(1L);

        assertEquals(1, list.size());
        assertEquals("doc.pdf", list.get(0).getFileName());
    }
    @Test
    void testUpdateWorkerDocument_Success() throws Exception {
        when(documentRepository.findById(101L)).thenReturn(Optional.of(mockDocument));
        when(documentRepository.save(any(WorkerDocument.class))).thenReturn(mockDocument);

        WorkerDocumentDTO dto = service.updateWorkerDocument(101L, file);

        assertNotNull(dto);
        assertEquals("doc.pdf", dto.getFileName());
    }

    @Test
    void testUpdateWorkerDocument_DocumentNotFound() {
        when(documentRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.updateWorkerDocument(999L, file);
        });

        assertEquals("Document not found", ex.getMessage());
    }

    @Test
    void testUpdateWorkerDocument_IOException() throws IOException {
        MultipartFile badFile = mock(MultipartFile.class);
        when(badFile.getBytes()).thenThrow(new IOException("fail"));
        when(documentRepository.findById(101L)).thenReturn(Optional.of(mockDocument));

        assertThrows(RuntimeException.class, () -> {
            service.updateWorkerDocument(101L, badFile);
        });
    }
}