package com.abinesh.fieldgaurd.workers.dto;

import jakarta.persistence.Lob;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkerDTO {

    private Long id;

    private String name;

    private LocalDate dob;

    private String place;

    private String state;
    private String district;
    private String taluk;
    private String subTaluk;

    private String aadharNumber;

    private String phoneNumber;

    private String fatherName;

    private String address;
    @Lob
    private byte[] workerPhoto;
}
