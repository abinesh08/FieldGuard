package com.abinesh.fieldguard.attendance.dto;

import com.abinesh.fieldguard.attendance.AttendanceStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDTO {
    private Long id;
    private Long workerId;
    private Long markedByOfficerId;
    private LocalDate date;
    private AttendanceStatus status;
    private String subtaluk;
    private String taluk;
    private String district;
    private String state;

}
