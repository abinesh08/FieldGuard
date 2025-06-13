package com.abinesh.fieldguard.attendance.service;

import com.abinesh.fieldguard.attendance.dto.AttendanceDTO;
import com.abinesh.fieldguard.event.GpsAttendanceResponseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GpsAttendanceResponseListener {
    @Autowired
    private AttendanceService attendanceService;

    @EventListener
    public void onWorkerReponse(GpsAttendanceResponseEvent event){
        for(Long workerId : event.getWorkerIds()){
            AttendanceDTO dto= AttendanceDTO.builder()
                    .workerId(workerId)
                    .markedByOfficerId(event.getOfficerId())
                    .status(event.getStatus())
                    .date(LocalDate.now())
                    .latitude(event.getLatitude())
                    .longitude(event.getLongitude())
                    .build();
            attendanceService.markAttendance(dto);
        }
    }
}
