package com.abinesh.fieldguard.workers.service;

import com.abinesh.fieldguard.event.OfficerAccessEvent;
import com.abinesh.fieldguard.event.WorkerFetchRequest;
import com.abinesh.fieldguard.event.WorkerResponseEvent;
import com.abinesh.fieldguard.workers.dto.WorkerDTO;
import com.abinesh.fieldguard.workers.mapper.WorkerMapper;
import com.abinesh.fieldguard.workers.model.Worker;
import com.abinesh.fieldguard.workers.repository.WorkerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class WorkerServiceTest {
    @Mock
    private WorkerRepository workerRepository;

    @InjectMocks
    private WorkerService workerService;

    private Worker worker;
    private WorkerDTO workerDTO;

    @BeforeEach
    void setUp() {
        worker=new Worker();
        worker.setId(1L);
        worker.setName("Abinesh");
        worker.setPlace("SomePlace");
        worker.setSubTaluk("SubT1");
        worker.setTaluk("Taluk1");
        worker.setDistrict("District1");
        worker.setState("Tamil Nadu");
        worker.setAddress("Address1");
        worker.setPhoneNumber(9876543210L);


        workerDTO = WorkerMapper.toDTO(worker);
    }

    @Test
    void testSaveWorker() {
        MockMultipartFile photo = new MockMultipartFile("photo", "photo.jpg", "image/jpeg", new byte[]{1, 2, 3});
        when(workerRepository.save(any(Worker.class))).thenAnswer(i -> {
            Worker saved = i.getArgument(0);
            saved.setId(1L);
            return saved;
        });

        WorkerDTO result = workerService.saveWorker(workerDTO, photo);

        assertNotNull(result);
        assertEquals("Abinesh", result.getName());
        verify(workerRepository, times(1)).save(any(Worker.class));
    }
    @Test
    void testSaveWorkerThrowsExecptionWhenPhotoFails() throws IOException{
        MultipartFile photo= mock(MultipartFile.class);
        when(photo.getBytes()).thenThrow(new IOException("Simulated Exception"));
        RuntimeException exception= assertThrows(RuntimeException.class,()-> {
            workerService.saveWorker(workerDTO,photo);
        });
        assertEquals("Error saving photo", exception.getMessage());
    }
    @Test
    void testUpdateWorkerSuccess(){
        Long id=1L;
        Worker existingWorker = new Worker();
        existingWorker.setId(id);
        existingWorker.setName("Old name");

        WorkerDTO updatedDTO= new WorkerDTO();
        updatedDTO.setName("New Name");
        updatedDTO.setDob(LocalDate.parse("1990-01-01"));
        updatedDTO.setPlace("Chennai");
        updatedDTO.setState("Tamil Nadu");
        updatedDTO.setAadharNumber("123456789012");
        updatedDTO.setPhoneNumber(9876543210L);
        updatedDTO.setFatherName("Father Name");
        updatedDTO.setAddress("New Address");
        updatedDTO.setWorkerPhoto(new byte[]{1, 2, 3});

        when(workerRepository.findById(id)).thenReturn(Optional.of(existingWorker));
        when(workerRepository.save(any(Worker.class))).thenAnswer(i->i.getArgument(0));
        WorkerDTO result=workerService.updateWorker(id,updatedDTO);
        assertNotNull(result);
        assertEquals("New Name", result.getName());
        verify(workerRepository).findById(id);
        verify(workerRepository).save(any(Worker.class));
    }
    @Test
    void testUpdateWorkersNotFound(){
        Long id=1L;
        when(workerRepository.findById(id)).thenReturn(Optional.empty());
        RuntimeException exception= assertThrows(RuntimeException.class, ()-> {
            workerService.updateWorker(id, new WorkerDTO());
        });
        assertEquals("Worker not found with ID: " + id, exception.getMessage());
        verify(workerRepository).findById(id);
        verify(workerRepository,never()).save(any(Worker.class));
    }
    @Test
    void testGetAllWorkers(){
        Worker worker1= new Worker();
        worker1.setId(1L);
        worker1.setName("Abinesh");
        worker1.setPhoneNumber(1234567890L);
        worker1.setState("Tamil nadu");

        Worker worker2= new Worker();
        worker2.setId(2L);
        worker2.setName("Ravi");
        worker2.setPhoneNumber(98765321L);
        worker2.setState("Kerela");

        List<Worker> workers= Arrays.asList(worker1,worker2);
        when(workerRepository.findAll()).thenReturn(workers);
        List<WorkerDTO> result=workerService.getAllWorkers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Abinesh", result.get(0).getName());
        assertEquals("Ravi", result.get(1).getName());
        verify(workerRepository).findAll();
    }
    @Test
    void testGetWorkerByIdSuccess() {
        Long id = 1L;

        Worker worker = new Worker();
        worker.setId(id);
        worker.setName("Abinesh");
        worker.setPhoneNumber(1234567890L);
        worker.setState("Tamil Nadu");

        when(workerRepository.findById(id)).thenReturn(Optional.of(worker));

        WorkerDTO result = workerService.getWorkerById(id);

        assertNotNull(result);
        assertEquals("Abinesh", result.getName());
        assertEquals(1234567890L, result.getPhoneNumber());
        verify(workerRepository).findById(id);
    }

    @Test
    void testGetWorkerByIdNotFound() {
        Long id = 1L;

        when(workerRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            workerService.getWorkerById(id);
        });

        assertEquals("Worker not found with ID " + id, exception.getMessage());
        verify(workerRepository).findById(id);
    }
    @Test
    void testDeleteWorkerSuccess() {
        Long id = 1L;
        when(workerRepository.existsById(id)).thenReturn(true);
        workerService.deleteWorker(id);

        verify(workerRepository).existsById(id);
        verify(workerRepository).deleteById(id);
    }
    @Test
    void testDeleteWorkerNotFound() {
        Long id = 1L;

        when(workerRepository.existsById(id)).thenReturn(false);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            workerService.deleteWorker(id);
        });

        assertEquals("No worker found with ID: " + id, exception.getMessage());
        verify(workerRepository).existsById(id);
        verify(workerRepository, never()).deleteById(any());
    }
    @Test
    void testHandleWorkerFetchForSubTaluk() {
        OfficerAccessEvent accessInfo = new OfficerAccessEvent();
        accessInfo.setRole("SUBTALUK");
        accessInfo.setSubTaluk("SubT1");

        WorkerFetchRequest event = new WorkerFetchRequest(accessInfo);
        event.setAccessInfo(accessInfo);

        when(workerRepository.findBySubTaluk("SubT1")).thenReturn(List.of(worker));

        workerService.handleWorkerFetch(event);

        List<WorkerResponseEvent> responses = event.getWorkerResponses();
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Abinesh", responses.get(0).getName());
        assertEquals("Tamil Nadu", responses.get(0).getState());

        verify(workerRepository).findBySubTaluk("SubT1");
    }
    @Test
    void testFetchByDistrict() {
        OfficerAccessEvent access = new OfficerAccessEvent();
        access.setRole("DISTRICT");
        access.setDistrict("District1");

        WorkerFetchRequest event = new WorkerFetchRequest(access);

        when(workerRepository.findByDistrict("District1")).thenReturn(List.of(worker));

        workerService.handleWorkerFetch(event);

        assertEquals(1, event.getWorkerResponses().size());
        assertEquals("Abinesh", event.getWorkerResponses().get(0).getName());
    }
    @Test
    void testFetchByState() {
        OfficerAccessEvent access = new OfficerAccessEvent();
        access.setRole("STATE");
        access.setState("State1");

        WorkerFetchRequest event = new WorkerFetchRequest(access);

        when(workerRepository.findByState("State1")).thenReturn(List.of(worker));

        workerService.handleWorkerFetch(event);

        assertEquals(1, event.getWorkerResponses().size());
        assertEquals("Abinesh", event.getWorkerResponses().get(0).getName());
    }
    @Test
    void testFetchByTaluk(){
        OfficerAccessEvent access= new OfficerAccessEvent();
        access.setRole("TALUK");
        access.setTaluk("Taluk1");

        WorkerFetchRequest event= new WorkerFetchRequest(access);
        when(workerRepository.findByTaluk("Taluk1")).thenReturn(List.of(worker));
        workerService.handleWorkerFetch(event);

        assertEquals(1, event.getWorkerResponses().size());
        assertEquals("Abinesh", event.getWorkerResponses().get(0).getName());
    }

    @Test
    void testFetchDefault() {
        OfficerAccessEvent access = new OfficerAccessEvent();
        access.setRole("UNKNOWN");

        WorkerFetchRequest event = new WorkerFetchRequest(access);

        workerService.handleWorkerFetch(event);

        assertTrue(event.getWorkerResponses().isEmpty());
    }

}