package com.abinesh.fieldguard.workers.repository;

import com.abinesh.fieldguard.workers.model.WorkerDocument;
import com.abinesh.fieldguard.workers.service.WorkerDocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkerDocumentRepositoryTest {

    @Mock
    private WorkerDocumentRepository documentRepository;

    @InjectMocks
    private WorkerDocumentService workerDocumentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testFindByWorkerId() {
        WorkerDocument doc = new WorkerDocument();
        doc.setId(1L);;
        doc.setDocumentType("ID");
        doc.setFileName("id_card.pdf");

        Mockito.when(documentRepository.findByWorkerId(1L))
                .thenReturn(List.of(doc));

        List<WorkerDocument> result = documentRepository.findByWorkerId(1L);

        assertEquals(1, result.size());
        assertEquals("ID", result.get(0).getDocumentType());
        assertEquals("id_card.pdf", result.get(0).getFileName());
    }

}