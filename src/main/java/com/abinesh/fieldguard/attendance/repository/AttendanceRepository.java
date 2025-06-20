package com.abinesh.fieldguard.attendance.repository;

import com.abinesh.fieldguard.AttendanceStatus;
import com.abinesh.fieldguard.attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByDateAndTalukAndStatus(LocalDate date, String taluk, AttendanceStatus status);
    List<Attendance> findByDateAndSubtalukAndStatus(LocalDate date, String subtaluk, AttendanceStatus status);
    List<Attendance> findByDateAndDistrictAndStatus(LocalDate date, String district, AttendanceStatus status);

}
