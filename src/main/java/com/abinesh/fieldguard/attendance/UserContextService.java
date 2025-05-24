package com.abinesh.fieldguard.attendance;

import org.springframework.stereotype.Service;

@Service
public class UserContextService {

    public OfficerRole getCurrentUserRole() {
        return OfficerRole.TALUK;
    }

    public String getCurrentUserSubtaluk() {
        return "SubtalukA";
    }

    public String getCurrentUserTaluk() {
        return "TalukA";
    }

    public String getCurrentUserDistrict() {
        return "DistrictX";
    }
}
