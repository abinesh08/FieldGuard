package com.abinesh.fieldgaurd.workers.controller;

import com.abinesh.fieldgaurd.workers.dto.WorkerDTO;
import com.abinesh.fieldgaurd.workers.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/worker")
@RequiredArgsConstructor
public class WorkerController {
    @Autowired
    private final WorkerService workerService;

    @PostMapping(consumes = {"multipart/form-data"})
    public WorkerDTO createWorker(
            @RequestPart("worker") WorkerDTO workerDTO,
            @RequestPart("photo") MultipartFile workerPhoto
    ) {
        return workerService.saveWorker(workerDTO, workerPhoto);
    }

    @PutMapping("/{id}")
    public WorkerDTO updateWorker(@PathVariable Long id, @RequestBody WorkerDTO dto) {
        return workerService.updateWorker(id, dto);
    }

    @GetMapping
    public List<WorkerDTO> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping("/{id}")
    public WorkerDTO getById(@PathVariable Long id) {
        return workerService.getWorkerById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(id);
        return "Worker deleted successfully";
    }
}
