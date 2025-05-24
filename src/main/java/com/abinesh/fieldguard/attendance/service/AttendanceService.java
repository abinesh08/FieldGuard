package com.abinesh.fieldguard.attendance.service;

import com.abinesh.fieldguard.attendance.dto.AttendanceDTO;
import com.abinesh.fieldguard.attendance.mapper.AttendanceMapper;
import com.abinesh.fieldguard.attendance.model.Attendance;
import com.abinesh.fieldguard.attendance.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    public AttendanceDTO markAttendance(AttendanceDTO dto) {
        Attendance attendance = AttendanceMapper.toEntity(dto);
        return AttendanceMapper.toDTO(attendanceRepository.save(attendance));
    }

    public List<AttendanceDTO> getBySubtaluk(String subtaluk, LocalDate date) {
        return attendanceRepository.findByDateAndSubtaluk(date, subtaluk)
                .stream().map(AttendanceMapper::toDTO).collect(Collectors.toList());
    }
}
