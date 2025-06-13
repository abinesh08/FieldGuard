package com.abinesh.fieldguard.event;

import com.abinesh.fieldguard.AttendanceStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GpsAttendanceRequestEvent {
    private Long officerId;
    private Double latitude;
    private Double longitude;
    private Double radiusInKm;
    private AttendanceStatus status;
}
