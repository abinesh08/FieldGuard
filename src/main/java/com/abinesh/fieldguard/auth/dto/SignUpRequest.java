package com.abinesh.fieldguard.auth.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private Long phoneNumber;
    private String password;
    private String role;
}
