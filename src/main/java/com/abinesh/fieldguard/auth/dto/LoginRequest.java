package com.abinesh.fieldguard.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private Long phoneNumber;
    private String password;
}
