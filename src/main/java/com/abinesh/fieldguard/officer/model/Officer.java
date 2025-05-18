package com.abinesh.fieldguard.officer.model;

import com.abinesh.fieldguard.officer.OfficerRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Officer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Long phoneNumber;

    @Enumerated(EnumType.STRING)
    private OfficerRole role;
    private String subTaluk;
    private String taluk;
    private String district;
    private String state;

    private String aadhaarNumber;

    @Lob
    @Column(name="aadhaar_file", columnDefinition = "LONGBLOB")
    private byte[] aadhaarFile;
}
