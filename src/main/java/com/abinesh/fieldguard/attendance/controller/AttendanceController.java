package com.abinesh.fieldguard.attendance.controller;

import com.abinesh.fieldguard.attendance.AttendanceStatus;
import com.abinesh.fieldguard.attendance.dto.AttendanceDTO;
import com.abinesh.fieldguard.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService service;

    @PostMapping("/mark")
    @PreAuthorize("@accessChecker.canMarkOrUpdate()")
    public AttendanceDTO markAttendance(@RequestBody AttendanceDTO dto) {
        return service.markAttendance(dto);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("@accessChecker.canMarkOrUpdate()")
    public AttendanceDTO updateAttendance(@PathVariable Long id, @RequestBody AttendanceDTO dto) {
        return service.updateAttendance(id, dto);
    }

    @GetMapping("/subtaluk/{subtaluk}/{date}/{status}")
    @PreAuthorize("@accessChecker.canAccessSubtaluk(#subtaluk)")
    public List<AttendanceDTO> getBySubtaluk(@PathVariable String subtaluk,
                                             @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                             @PathVariable AttendanceStatus status) {
        return service.getBySubtaluk(subtaluk, date, status);
    }

    @GetMapping("/taluk/{taluk}/{date}/{status}")
    @PreAuthorize("@accessChecker.canAccessTaluk(#taluk)")
    public List<AttendanceDTO> getByTaluk(@PathVariable String taluk,
                                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                          @PathVariable AttendanceStatus status) {
        return service.getByTaluk(taluk, date,status);
    }

    @GetMapping("/district/{district}/{date}/{status}")
    @PreAuthorize("@accessChecker.canAccessDistrict(#district)")
    public List<AttendanceDTO> getByDistrict(@PathVariable String district,
                                             @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                             @PathVariable AttendanceStatus status) {
        return service.getByDistrict(district, date, status);
    }



}
