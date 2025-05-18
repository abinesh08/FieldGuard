package com.abinesh.fieldguard.workers.mapper;

import com.abinesh.fieldguard.workers.dto.WorkerDocumentDTO;
import com.abinesh.fieldguard.workers.model.Worker;
import com.abinesh.fieldguard.workers.model.WorkerDocument;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class WorkerDocumentMapperTest {
    @Test
    void testToDTO() {
        byte[] fileData = new byte[]{1, 2, 3, 4};
        WorkerDocument doc = WorkerDocument.builder()
                .id(1L)
                .documentType("Aadhar")
                .fileName("aadhar.jpg")
                .fileType("image/jpeg")
                .documentFile(fileData)
                .build();

        WorkerDocumentDTO dto = WorkerDocumentMapper.toDTO(doc);

        assertEquals(doc.getId(), dto.getId());
        assertEquals(doc.getDocumentType(), dto.getDocumentType());
        assertEquals(doc.getFileName(), dto.getFileName());
        assertEquals(doc.getFileType(), dto.getFileType());
        assertEquals(Base64.getEncoder().encodeToString(fileData), dto.getBase64Data());
    }

    @Test
    void testToEntity_Success() throws IOException {
        byte[] fileData = new byte[]{10, 20, 30};

        MultipartFile multipartFile = Mockito.mock(MultipartFile.class);
        Mockito.when(multipartFile.getOriginalFilename()).thenReturn("passport.pdf");
        Mockito.when(multipartFile.getContentType()).thenReturn("application/pdf");
        Mockito.when(multipartFile.getBytes()).thenReturn(fileData);

        Worker mockWorker = Worker.builder().id(5L).build();

        WorkerDocument doc = WorkerDocumentMapper.toEntity(multipartFile, "Passport", mockWorker);

        assertEquals("Passport", doc.getDocumentType());
        assertEquals("passport.pdf", doc.getFileName());
        assertEquals("application/pdf", doc.getFileType());
        assertArrayEquals(fileData, doc.getDocumentFile());
        assertEquals(mockWorker, doc.getWorker());
    }

    @Test
    void testToEntity_IOException() throws IOException {
        MultipartFile multipartFile = Mockito.mock(MultipartFile.class);
        Mockito.when(multipartFile.getOriginalFilename()).thenReturn("invalid.jpg");
        Mockito.when(multipartFile.getContentType()).thenReturn("image/jpeg");
        Mockito.when(multipartFile.getBytes()).thenThrow(new IOException("Simulated failure"));

        Worker dummyWorker = Worker.builder().id(10L).build();

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                WorkerDocumentMapper.toEntity(multipartFile, "Invalid", dummyWorker)
        );

        assertTrue(ex.getMessage().contains("Error reading file bytes"));
        assertTrue(ex.getCause() instanceof IOException);
    }

}