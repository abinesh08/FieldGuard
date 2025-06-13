package com.abinesh.fieldguard.attendance.mapper;

import com.abinesh.fieldguard.attendance.dto.AttendanceDTO;
import com.abinesh.fieldguard.attendance.model.Attendance;

public class AttendanceMapper {
    public static Attendance toEntity(AttendanceDTO dto) {
        return Attendance.builder()
                .workerId(dto.getWorkerId())
                .markedByOfficerId(dto.getMarkedByOfficerId())
                .date(dto.getDate())
                .status(dto.getStatus())
                .subtaluk(dto.getSubtaluk())
                .taluk(dto.getTaluk())
                .district(dto.getDistrict())
                .state(dto.getState())
                .longitude(dto.getLongitude())
                .latitude(dto.getLatitude())
                .build();
    }
    public static AttendanceDTO toDTO(Attendance attendance) {
        return AttendanceDTO.builder()
                .id(attendance.getId())
                .workerId(attendance.getWorkerId())
                .markedByOfficerId(attendance.getMarkedByOfficerId())
                .date(attendance.getDate())
                .status(attendance.getStatus())
                .subtaluk(attendance.getSubtaluk())
                .taluk(attendance.getTaluk())
                .district(attendance.getDistrict())
                .state(attendance.getState())
                .longitude(attendance.getLongitude())
                .latitude(attendance.getLatitude())
                .build();
    }
}
