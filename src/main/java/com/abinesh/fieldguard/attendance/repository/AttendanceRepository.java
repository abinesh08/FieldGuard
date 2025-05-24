package com.abinesh.fieldguard.attendance.repository;

import com.abinesh.fieldguard.attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByDateAndTaluk(LocalDate date, String taluk);
    List<Attendance> findByDateAndSubtaluk(LocalDate date, String subtaluk);
    List<Attendance> findByDateAndDistrict(LocalDate date, String district);
    List<Attendance> findByDateAndState(LocalDate date, String state);

}
