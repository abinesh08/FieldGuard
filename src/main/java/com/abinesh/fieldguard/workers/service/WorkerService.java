package com.abinesh.fieldguard.workers.service;

import com.abinesh.fieldguard.event.OfficerAccessEvent;
import com.abinesh.fieldguard.event.WorkerFetchRequest;
import com.abinesh.fieldguard.event.WorkerResponseEvent;
import com.abinesh.fieldguard.workers.dto.WorkerDTO;
import com.abinesh.fieldguard.workers.mapper.WorkerMapper;
import com.abinesh.fieldguard.workers.model.Worker;
import com.abinesh.fieldguard.workers.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkerService {
    @Autowired
    private final WorkerRepository workerRepository;

    public WorkerDTO saveWorker(WorkerDTO dto, MultipartFile photo) {
        Worker worker = WorkerMapper.toEntity(dto);
        try {
            worker.setWorkerPhoto(photo.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Error saving photo", e);
        }
        Worker saved = workerRepository.save(worker);
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

    public List<WorkerDTO> getAllWorkers() {
        return workerRepository.findAll().stream().map(WorkerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public WorkerDTO getWorkerById(Long id) {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Worker not found with ID " + id));
        return WorkerMapper.toDTO(worker);
    }

    public void deleteWorker(Long id) {
        if (!workerRepository.existsById(id)) {
            throw new RuntimeException("No worker found with ID: " + id);
        }
        workerRepository.deleteById(id);
    }

    @EventListener
    public void handleWorkerFetch(WorkerFetchRequest event) {
        OfficerAccessEvent info = event.getAccessInfo();
        List<Worker> workers;

        switch (info.getRole()) {
            case "SUBTALUK" -> workers = workerRepository.findBySubTaluk(info.getSubTaluk());
            case "TALUK" -> workers = workerRepository.findByTaluk(info.getTaluk());
            case "DISTRICT" -> workers = workerRepository.findByDistrict(info.getDistrict());
            case "STATE" -> workers = workerRepository.findByState(info.getState());
            default -> workers = List.of();
        }

        List<WorkerResponseEvent> responses = workers.stream()
                .map(worker -> WorkerResponseEvent.builder()
                        .id(worker.getId())
                        .name(worker.getName())
                        .place(worker.getPlace())
                        .subTaluk(worker.getSubTaluk())
                        .taluk(worker.getTaluk())
                        .district(worker.getDistrict())
                        .state(worker.getState())
                        .address(worker.getAddress())
                        .phoneNumber(worker.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());

        event.setWorkerResponses(responses);

    }
}
