package com.abinesh.fieldgaurd.workers.mapper;

import com.abinesh.fieldgaurd.workers.dto.WorkerDTO;
import com.abinesh.fieldgaurd.workers.model.Worker;

public class WorkerMapper {

    public static WorkerDTO toDTO(Worker worker) {
        return WorkerDTO.builder()
                .id(worker.getId())
                .name(worker.getName())
                .dob(worker.getDob())
                .place(worker.getPlace())
                .state(worker.getState())
                .aadharNumber(worker.getAadharNumber())
                .phoneNumber(worker.getPhoneNumber())
                .fatherName(worker.getFatherName())
                .address(worker.getAddress())
                .workerPhoto(worker.getWorkerPhoto())
                .taluk(worker.getTaluk())
                .district(worker.getDistrict())
                .build();
    }

    public static Worker toEntity(WorkerDTO dto) {
        return Worker.builder()
                .id(dto.getId())
                .name(dto.getName())
                .dob(dto.getDob())
                .place(dto.getPlace())
                .state(dto.getState())
                .aadharNumber(dto.getAadharNumber())
                .phoneNumber(dto.getPhoneNumber())
                .fatherName(dto.getFatherName())
                .address(dto.getAddress())
                .workerPhoto(dto.getWorkerPhoto())
                .taluk(dto.getTaluk())
                .district(dto.getDistrict())
                .build();
    }
}
