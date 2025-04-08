package com.abinesh.fieldgaurd.workers.model;

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

    private String state;

    @Column(unique = true, nullable = false)
    private String aadharNumber;

    private String phoneNumber;

    private String fatherName;

    private String address;
    @Lob
    @Column(name="worker_photo", columnDefinition = "LONGBLOB")
    private byte[] workerPhoto;
}
