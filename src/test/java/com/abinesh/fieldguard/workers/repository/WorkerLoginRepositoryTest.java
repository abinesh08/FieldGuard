package com.abinesh.fieldguard.workers.repository;

import com.abinesh.fieldguard.workers.model.Worker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class WorkerLoginRepositoryTest {
    @Mock
    private WorkerRepository workerRepository;

    Worker sampleWorker;

    @BeforeEach
    void setup() {
        sampleWorker = Worker.builder()
                .id(1L)
                .name("John Doe")
                .subTaluk("SubA")
                .taluk("TalukA")
                .district("DistrictA")
                .state("StateA")
                .build();
    }

    @Test
    void testFindBySubTaluk() {
        Mockito.when(workerRepository.findBySubTaluk("SubA"))
                .thenReturn(List.of(sampleWorker));

        List<Worker> result = workerRepository.findBySubTaluk("SubA");

        assertEquals(1, result.size());
        assertEquals("SubA", result.get(0).getSubTaluk());
    }

    @Test
    void testFindByTaluk() {
        Mockito.when(workerRepository.findByTaluk("TalukA"))
                .thenReturn(List.of(sampleWorker));

        List<Worker> result = workerRepository.findByTaluk("TalukA");

        assertEquals(1, result.size());
        assertEquals("TalukA", result.get(0).getTaluk());
    }

    @Test
    void testFindByDistrict() {
        Mockito.when(workerRepository.findByDistrict("DistrictA"))
                .thenReturn(List.of(sampleWorker));

        List<Worker> result = workerRepository.findByDistrict("DistrictA");

        assertEquals("DistrictA", result.get(0).getDistrict());
    }

    @Test
    void testFindByState() {
        Mockito.when(workerRepository.findByState("StateA"))
                .thenReturn(List.of(sampleWorker));

        List<Worker> result = workerRepository.findByState("StateA");

        assertEquals("StateA", result.get(0).getState());
    }

    @Test
    void testFindById() {
        Mockito.when(workerRepository.findById(1L))
                .thenReturn(Optional.of(sampleWorker));

        Optional<Worker> result = workerRepository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }
}