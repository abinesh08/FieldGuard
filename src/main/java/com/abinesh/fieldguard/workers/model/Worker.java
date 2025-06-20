package com.abinesh.fieldguard.workers.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "workers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dob;

    private String place;
    private String subTaluk;
    private String state;
    private String district;
    private String taluk;

    @Column(unique = true, nullable = false)
    private String aadharNumber;

    private Long phoneNumber;

    private String fatherName;

    private String address;
    @Lob
    @Column(name="worker_photo", columnDefinition = "LONGBLOB")
    private byte[] workerPhoto;
    private Double longitude;
    private Double latitude;
}
