package com.abinesh.fieldgaurd.workers.service;

import com.abinesh.fieldgaurd.workers.dto.WorkerDTO;
import com.abinesh.fieldgaurd.workers.mapper.WorkerMapper;
import com.abinesh.fieldgaurd.workers.model.Worker;
import com.abinesh.fieldgaurd.workers.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkerService {
    @Autowired
    private final WorkerRepository workerRepository;

    public WorkerDTO saveWorker(WorkerDTO dto, MultipartFile photo){
        Worker worker= WorkerMapper.toEntity(dto);
        try {
            worker.setWorkerPhoto(photo.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Error saving photo", e);
        }
        Worker saved= workerRepository.save(worker);
        return WorkerMapper.toDTO(saved);
    }

    public WorkerDTO updateWorker(Long id, WorkerDTO dto) {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Worker not found with ID: " + id));

        worker.setName(dto.getName());
        worker.setDob(dto.getDob());
        worker.setPlace(dto.getPlace());
        worker.setState(dto.getState());
        worker.setAadharNumber(dto.getAadharNumber());
        worker.setPhoneNumber(dto.getPhoneNumber());
        worker.setFatherName(dto.getFatherName());
        worker.setAddress(dto.getAddress());
        worker.setWorkerPhoto(dto.getWorkerPhoto());

        return WorkerMapper.toDTO(workerRepository.save(worker));
    }

    public List<WorkerDTO> getAllWorkers(){
        return workerRepository.findAll().stream().map(WorkerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public WorkerDTO getWorkerById(Long id){
        Worker worker= workerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Worker not found with ID " + id));
        return WorkerMapper.toDTO(worker);
    }

    public void deleteWorker(Long id) {
        if (!workerRepository.existsById(id)) {
            throw new RuntimeException("No worker found with ID: " + id);
        }
        workerRepository.deleteById(id);
    }

}
