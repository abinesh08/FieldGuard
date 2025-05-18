package com.abinesh.fieldguard.workers.mapper;

import com.abinesh.fieldguard.workers.dto.WorkerDTO;
import com.abinesh.fieldguard.workers.model.Worker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkerMapperTest {

    @Test
    void testToDTO(){
        Worker worker= Worker.builder()
                .id(1L)
                .name("Abinesh")
                .dob(LocalDate.of(2000,11,01))
                .place("Chennai")
                .subTaluk("North")
                .state("TamilNadu")
                .district("Chennai District")
                .taluk("Egmore")
                .aadharNumber("123456789012")
                .phoneNumber(9876543210L)
                .fatherName("Ravi")
                .address("123 Street")
                .workerPhoto(new byte[]{1, 2, 3})
                .build();

        WorkerDTO dto=WorkerMapper.toDTO(worker);

        assertEquals(worker.getId(),dto.getId());
        assertEquals(worker.getName(), dto.getName());
        assertEquals(worker.getDob(), dto.getDob());
        assertEquals(worker.getPlace(), dto.getPlace());
        assertEquals(worker.getSubTaluk(), dto.getSubTaluk());
        assertEquals(worker.getState(), dto.getState());
        assertEquals(worker.getDistrict(), dto.getDistrict());
        assertEquals(worker.getTaluk(), dto.getTaluk());
        assertEquals(worker.getAadharNumber(), dto.getAadharNumber());
        assertEquals(worker.getPhoneNumber(), dto.getPhoneNumber());
        assertEquals(worker.getFatherName(), dto.getFatherName());
        assertEquals(worker.getAddress(), dto.getAddress());
        assertArrayEquals(worker.getWorkerPhoto(), dto.getWorkerPhoto());

    }
    @Test
    void testToEntity() {
        WorkerDTO dto = WorkerDTO.builder()
                .id(1L)
                .name("Abinesh")
                .dob(LocalDate.of(2000, 1, 1))
                .place("Chennai")
                .subTaluk("North")
                .state("Tamil Nadu")
                .district("Chennai District")
                .taluk("Egmore")
                .aadharNumber("123456789012")
                .phoneNumber(9876543210L)
                .fatherName("Ravi")
                .address("123 Street")
                .workerPhoto(new byte[]{1, 2, 3})
                .build();

        Worker worker = WorkerMapper.toEntity(dto);

        assertEquals(dto.getId(), worker.getId());
        assertEquals(dto.getName(), worker.getName());
        assertEquals(dto.getDob(), worker.getDob());
        assertEquals(dto.getPlace(), worker.getPlace());
        assertEquals(dto.getSubTaluk(), worker.getSubTaluk());
        assertEquals(dto.getState(), worker.getState());
        assertEquals(dto.getDistrict(), worker.getDistrict());
        assertEquals(dto.getTaluk(), worker.getTaluk());
        assertEquals(dto.getAadharNumber(), worker.getAadharNumber());
        assertEquals(dto.getPhoneNumber(), worker.getPhoneNumber());
        assertEquals(dto.getFatherName(), worker.getFatherName());
        assertEquals(dto.getAddress(), worker.getAddress());
        assertArrayEquals(dto.getWorkerPhoto(), worker.getWorkerPhoto());
    }

}