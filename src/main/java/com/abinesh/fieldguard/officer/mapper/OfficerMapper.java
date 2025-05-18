package com.abinesh.fieldguard.officer.mapper;

import com.abinesh.fieldguard.officer.dto.OfficerDTO;
import com.abinesh.fieldguard.officer.model.Officer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class OfficerMapper {
    public static OfficerDTO toDTO(Officer officer) {
        return OfficerDTO.builder()
                .id(officer.getId())
                .name(officer.getName())
                .email(officer.getEmail())
                .role(officer.getRole())
                .subTaluk(officer.getSubTaluk())
                .taluk(officer.getTaluk())
                .district(officer.getDistrict())
                .state(officer.getState())
                .aadhaarNumber(officer.getAadhaarNumber())
                .phoneNumber(officer.getPhoneNumber())
                .build();
    }

    public static Officer toEntity(MultipartFile aadhaarFile, OfficerDTO dto) throws IOException {
        try {
            return Officer.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .role(dto.getRole())
                    .subTaluk(dto.getSubTaluk())
                    .taluk(dto.getTaluk())
                    .district(dto.getDistrict())
                    .state(dto.getState())
                    .aadhaarNumber(dto.getAadhaarNumber())
                    .aadhaarFile(aadhaarFile != null ? aadhaarFile.getBytes() : null)
                    .phoneNumber(dto.getPhoneNumber())
                    .build();
        }catch (Exception e){
            throw new RuntimeException("Error reading file bytes", e);
        }
    }
}
