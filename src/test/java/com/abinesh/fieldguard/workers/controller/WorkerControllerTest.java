package com.abinesh.fieldguard.workers.controller;

import com.abinesh.fieldguard.workers.dto.WorkerDTO;
import com.abinesh.fieldguard.workers.service.WorkerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WorkerController.class)
class WorkerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkerService workerService;

    @Autowired
    private ObjectMapper objectMapper;

    private WorkerDTO sampleWorker;

    @BeforeEach
    void setUp() {
        sampleWorker = new WorkerDTO();
        sampleWorker.setId(1L);
        sampleWorker.setName("Abinesh");
        sampleWorker.setPhoneNumber(1234567890L);
        sampleWorker.setPlace("Chennai");
    }

//    @Test
//    void testCreateWorker() throws Exception {
//        MockMultipartFile photo = new MockMultipartFile("photo", "photo.jpg", MediaType.IMAGE_JPEG_VALUE, "test".getBytes());
//        MockMultipartFile workerPart = new MockMultipartFile(
//                "worker", "", "application/json", objectMapper.writeValueAsBytes(sampleWorker));
//
//        when(workerService.saveWorker(any(), any())).thenReturn(sampleWorker);
//
//        mockMvc.perform(multipart("/worker")
//                        .file(photo)
//                        .file(workerPart))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Abinesh"));
//    }
//    @Test
//    public void testUpdateWorker() throws Exception {
//        when(workerService.updateWorker(eq(1L), any(WorkerDTO.class))).thenReturn(sampleWorker);
//
//        mockMvc.perform(put("/worker/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(sampleWorker)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Abinesh"));
//    }
//    @Test
//    public void testGetAllWorkers() throws Exception {
//        List<WorkerDTO> workers = Arrays.asList(sampleWorker);
//        when(workerService.getAllWorkers()).thenReturn(workers);
//
//        mockMvc.perform(get("/worker"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].name").value("Abinesh"));
//    }
//    @Test
//    public void testGetById() throws Exception {
//        when(workerService.getWorkerById(1L)).thenReturn(sampleWorker);
//
//        mockMvc.perform(get("/worker/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Abinesh"));
//    }
//    @Test
//    public void testDeleteWorker() throws Exception {
//        Mockito.doNothing().when(workerService).deleteWorker(1L);
//
//        mockMvc.perform(delete("/worker/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Worker deleted successfully"));
//    }


}