package com.abinesh.fieldguard.attendance.model;

import com.abinesh.fieldguard.AttendanceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long workerId;

    private Long markedByOfficerId;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    private String subtaluk;
    private String taluk;
    private String district;
    private String state;
    private Double longitude;
    private Double latitude;
}
