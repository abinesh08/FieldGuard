package com.abinesh.fieldguard.attendance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

@Configuration
@EnableGlobalAuthentication
public class MethodSecurityConfig {
    // just this enables the security annotations in this project
}
