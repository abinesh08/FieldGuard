package com.abinesh.fieldgaurd.officer.service;


import com.abinesh.fieldgaurd.event.OfficerAccessEvent;
import com.abinesh.fieldgaurd.event.WorkerFetchRequest;
import com.abinesh.fieldgaurd.event.WorkerResponseEvent;
import com.abinesh.fieldgaurd.officer.Repository.OfficerRepository;
import com.abinesh.fieldgaurd.officer.dto.OfficerDTO;
import com.abinesh.fieldgaurd.officer.mapper.OfficerMapper;
import com.abinesh.fieldgaurd.officer.model.Officer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfficerService {

    private final OfficerRepository officerRepository;
    private final ApplicationEventPublisher publisher;

    public OfficerDTO addOfficer(OfficerDTO dto, MultipartFile aadharFile) throws IOException{
        Officer officer= OfficerMapper.toEntity(aadharFile, dto);
        return OfficerMapper.toDTO(officerRepository.save(officer));
    }

    public OfficerDTO updateOfficer(Long id, OfficerDTO dto, MultipartFile aadharFile) throws IOException{
        Officer officer=officerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(("Officer not found")));
        officer.setName(dto.getName());
        officer.setEmail(dto.getEmail());
        officer.setRole(dto.getRole());
        officer.setSubTaluk(dto.getSubTaluk());
        officer.setTaluk(dto.getTaluk());
        officer.setDistrict(dto.getDistrict());
        officer.setState(dto.getState());
        officer.setPhoneNumber(dto.getPhoneNumber());
        officer.setRole(dto.getRole());

        if(aadharFile !=null){
            officer.setAadhaarFile(aadharFile.getBytes());
        }
        return OfficerMapper.toDTO(officerRepository.save((officer)));
    }

    public OfficerDTO getOfficerById(Long id){
        Officer officer= officerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Officer not found"));
        return OfficerMapper.toDTO(officer);
    }

    public List<OfficerDTO> getAllOfficer(){
        return officerRepository.findAll().stream().map(OfficerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteOfficer(Long id){
        officerRepository.deleteById(id);
    }

    public List<WorkerResponseEvent> fetchWorkersForOfficer(OfficerAccessEvent info) {
        WorkerFetchRequest request = new WorkerFetchRequest(info);
        publisher.publishEvent(request);
        return request.getWorkerResponses();
    }
}
