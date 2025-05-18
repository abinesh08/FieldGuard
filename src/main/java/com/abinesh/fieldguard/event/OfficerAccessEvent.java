package com.abinesh.fieldguard.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficerAccessEvent {
    private String subTaluk;
    private String taluk;
    private String district;
    private String state;
    private String role;
}
