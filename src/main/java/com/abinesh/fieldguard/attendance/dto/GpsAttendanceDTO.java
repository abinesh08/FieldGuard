package com.abinesh.fieldguard.attendance.dto;

import com.abinesh.fieldguard.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GpsAttendanceDTO {
    private Long officerId;
    private Double longitude;
    private Double latitude;
    private Double radiusInKm;
    private AttendanceStatus status;
}
