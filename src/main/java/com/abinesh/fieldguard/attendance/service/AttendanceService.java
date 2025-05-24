package com.abinesh.fieldguard.attendance.service;

import com.abinesh.fieldguard.attendance.AttendanceStatus;
import com.abinesh.fieldguard.attendance.dto.AttendanceDTO;
import com.abinesh.fieldguard.attendance.mapper.AttendanceMapper;
import com.abinesh.fieldguard.attendance.model.Attendance;
import com.abinesh.fieldguard.attendance.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository repository;

    public AttendanceDTO markAttendance(AttendanceDTO dto) {
        Attendance attendance = AttendanceMapper.toEntity(dto);
        return AttendanceMapper.toDTO(repository.save(attendance));
    }

    public AttendanceDTO updateAttendance(Long id, AttendanceDTO dto) {
        Optional<Attendance> optionalAttendance = repository.findById(id);
        if (optionalAttendance.isPresent()) {
            Attendance existing = optionalAttendance.get();
            existing.setStatus(dto.getStatus());
            existing.setMarkedByOfficerId(dto.getMarkedByOfficerId());
            return AttendanceMapper.toDTO(repository.save(existing));
        } else {
            throw new RuntimeException("Attendance not found with ID: " + id);
        }
    }

    public List<AttendanceDTO> getBySubtaluk(String subtaluk, LocalDate date, AttendanceStatus status) {
        List<Attendance> attendanceList = repository.findByDateAndSubtalukAndStatus(date, subtaluk,status);
        List<AttendanceDTO> result = new ArrayList<>();

        for (Attendance att : attendanceList) {
            if (status == null || att.getStatus() == status) {
                AttendanceDTO dto = AttendanceMapper.toDTO(att);
                result.add(dto);
            }
        }

        return result;
    }

    public List<AttendanceDTO> getByTaluk(String taluk, LocalDate date, AttendanceStatus status) {
        List<Attendance> attendanceList = repository.findByDateAndTalukAndStatus(date, taluk, status);
        List<AttendanceDTO> dtoList = new ArrayList<>();
        for (Attendance a : attendanceList) {
            AttendanceDTO dto = AttendanceMapper.toDTO(a);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<AttendanceDTO> getByDistrict(String district, LocalDate date, AttendanceStatus status) {
        List<Attendance> attendanceList = repository.findByDateAndDistrictAndStatus(date, district, status);
        List<AttendanceDTO> dtoList = new ArrayList<>();
        for (Attendance a : attendanceList) {
            AttendanceDTO dto = AttendanceMapper.toDTO(a);
            dtoList.add(dto);
        }
        return dtoList;
    }




}
