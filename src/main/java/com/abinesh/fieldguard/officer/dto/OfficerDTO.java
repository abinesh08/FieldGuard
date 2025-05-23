package com.abinesh.fieldguard.officer.dto;

import com.abinesh.fieldguard.officer.OfficerRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfficerDTO {

    private Long id;
    private String name;
    private String email;
    private Long phoneNumber;
    private OfficerRole role;
    private String subTaluk;
    private String taluk;
    private String district;
    private String state;
    private String aadhaarNumber;

}
