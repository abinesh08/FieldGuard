package com.abinesh.fieldguard.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("accessChecker")
public class AccessChecker {
    @Autowired
    private UserContextService userContext;

    // only SUBTALUK role allowed
    public boolean canMarkOrUpdate() {
        return userContext.getCurrentUserRole() == OfficerRole.SUBTALUK;
    }

    // Check if user can access subtaluk-level data
    public boolean canAccessSubtaluk(String subtaluk) {
        OfficerRole role = userContext.getCurrentUserRole();

        if (role == OfficerRole.DISTRICT) return true;  // District can access all subtaluks
        if (role == OfficerRole.TALUK && userContext.getCurrentUserTaluk().equals(getTalukForSubtaluk(subtaluk)))
            return true;
        if (role == OfficerRole.SUBTALUK && userContext.getCurrentUserSubtaluk().equals(subtaluk))
            return true;

        return false;
    }

    // Check if user can access taluk-level data
    public boolean canAccessTaluk(String taluk) {
        OfficerRole role = userContext.getCurrentUserRole();
        if (role == OfficerRole.DISTRICT) return true;
        if (role == OfficerRole.TALUK && userContext.getCurrentUserTaluk().equals(taluk)) return true;
        return false;
    }

    // Check if user can access district-level data
    public boolean canAccessDistrict(String district) {
        OfficerRole role = userContext.getCurrentUserRole();
        return role == OfficerRole.DISTRICT && userContext.getCurrentUserDistrict().equals(district);
    }


    private String getTalukForSubtaluk(String subtaluk) {
        if ("SubtalukA".equals(subtaluk)) return "TalukA";
        if ("SubtalukB".equals(subtaluk)) return "TalukB";
        return "";
    }
}
