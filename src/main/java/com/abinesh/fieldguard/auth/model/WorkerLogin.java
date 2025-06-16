package com.abinesh.fieldguard.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "worker_login")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkerLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private Long phoneNumber;
    private String password;
    private String role;
}
