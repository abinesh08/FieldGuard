package com.abinesh.fieldguard.event;

import com.abinesh.fieldguard.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GpsAttendanceResponseEvent {
    private List<Long> workerIds;
    private Long officerId;
    private Double latitude;
    private Double longitude;
    private AttendanceStatus status;
}
