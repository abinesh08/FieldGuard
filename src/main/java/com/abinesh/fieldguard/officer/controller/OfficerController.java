package com.abinesh.fieldguard.officer.controller;

import com.abinesh.fieldguard.event.OfficerAccessEvent;
import com.abinesh.fieldguard.event.WorkerResponseEvent;
import com.abinesh.fieldguard.officer.dto.OfficerDTO;
import com.abinesh.fieldguard.officer.service.OfficerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/officer")
@RequiredArgsConstructor
public class OfficerController {
    private final OfficerService officerService;

    @PostMapping
    public OfficerDTO addOfficer(@RequestPart("data") OfficerDTO dto,
                                 @RequestPart(value="aadhaarFile", required = true)MultipartFile file) throws IOException {
        return officerService.addOfficer(dto,file);

    }

    @PutMapping("/{id}")
    public OfficerDTO updateOfficer(@PathVariable Long id, @RequestPart("data") OfficerDTO dto, @RequestPart(value="aadhaarFile", required = false) MultipartFile file) throws IOException{
        return officerService.updateOfficer(id,dto,file);
    }

    @GetMapping("/{id}")
    public OfficerDTO getOfficerById(@PathVariable Long id){
        return officerService.getOfficerById(id);
    }
    @GetMapping
    public List<OfficerDTO> getAll(){
        return officerService.getAllOfficer();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        officerService.deleteOfficer(id);
    }

    @PostMapping("/workers")
    public List<WorkerResponseEvent> getWorkersByRole(@RequestBody OfficerAccessEvent accessInfo) {
        return officerService.fetchWorkersForOfficer(accessInfo);
    }
}
